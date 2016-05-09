package shared.simulation.simjob;

import shared.core.GameServer;
import shared.core.ServerResources;
import shared.db.GameDB;
import shared.db.SimJobDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.metadata.Constants;
import shared.model.SpeciesType;
import shared.simulation.SimulationEngine;

/**
 * SetMetabolicRate was used a single time to update the metabolic rates in the
 * species table based on the formula used by ecologist Rich Williams.  Distinct 
 * from that formula, these calculations were normalized to 0-1.0 by dividing 
 * them by the largest metabolic rate because I found that the simulations did 
 * not work as expected if the rate was not normalized.  This has not been 
 * validated by the ecologists.
 * This class also contains the code that was used to update the per-unit-biomasses
 * in the species table from their temporary location in table simtest_node_params.
 * 
 * @author Justina
 */
public class SetMetabolicRate {

    private static final int TARGET_ECOSYSTEM_TYPE = 1;  //Serengetti ecosystem type
    //percent of overlap between shared predators and prey in order to
    //be included
    protected static final int SHARED_PCNT_OVERLAP = 50;
    protected static final double NORM_MAX = 1.0;
    protected static final double NORM_MIN = 0.0;
    protected SimulationEngine se;
    protected Properties propertiesConfig;

    public SetMetabolicRate() {
        //se = new SimulationEngine();  
        //extracted the following from simengine:
        propertiesConfig = new Properties();
        try {
            propertiesConfig.load(new FileInputStream("src/simulation/config/SimulationEngineConfig.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* read in experimental variables only used for running simulation jobs*/
        GameServer.getInstance();
        SpeciesType.loadSimTestNodeParams(TARGET_ECOSYSTEM_TYPE);
        SpeciesType.loadSimTestLinkParams(TARGET_ECOSYSTEM_TYPE);
    }

    //open printstream
    public static PrintStream getPrintStream(String filename) {
        final String name = filename, extension = ".csv";
        PrintStream ps = null;

        // Determine filename
        String[] files = new File(Constants.ATN_CSV_SAVE_PATH).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(name) && name.endsWith(extension);
            }
        });

        String csvFilename = name;

        if (files.length > 0) {
            int[] temp = new int[files.length];

            for (int i = 0; i < temp.length; i++) {
                String lastFilename = files[i].replaceFirst(name, "").replaceFirst("_", "");

                try {
                    temp[i] = Integer.parseInt(lastFilename.substring(0, lastFilename.indexOf(extension)));
                } catch (NumberFormatException ex) {
                    temp[i] = 0;
                }
            }

            Arrays.sort(temp);

            csvFilename += "_" + (temp[temp.length - 1] + 1);
        }

        csvFilename += extension;
        try {
            ps = new PrintStream(new FileOutputStream(Constants.ATN_CSV_SAVE_PATH + csvFilename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ps;
    }

    public static void setBiomassMetRate(float biomass, float met_rate, int species_id)
            throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            String query = "UPDATE species SET max_biomass = ?, metabolism = ? WHERE species_id = ?";

            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, biomass);
            pstmt.setFloat(2, met_rate);
            pstmt.setFloat(3, species_id);
            pstmt.execute();
            pstmt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    //evalMetRate() was originally used to evaluate met rates with varying M_k (primary producer
    //per-unit-biomass) and eventually used to push revised per-unit-biomass (M_i) and metabolic
    //rates out to species table.  Met rate is normalized here based on largest met rate (0-1 scale).
    //This is not necessary ligit scientifically
    public static void evalMetRate() throws FileNotFoundException {
        double a_Ti, x_i, M_i;
        double f_rk, a_rk, M_k;
        double normFactor = 0;
        double[] nonNormX = new double[ServerResources.getSpeciesTable().getAnimals().size()];
        double[] normX = new double[ServerResources.getSpeciesTable().getAnimals().size()];
        int sp;

        final String name = "met-rate", extension = ".csv";
        String fileName = Constants.ATN_CSV_SAVE_PATH.concat(name).concat(extension);
        PrintWriter dest = new PrintWriter(new File(fileName));

        System.out.printf("%30s,%4s,%10s,%10s,%10s,%10s", "Animal(i)", "ID", "M_i",
                "a_Ti", "f_rk", "a_rk");
        dest.printf("%30s,%10s,%10s,%10s,%10s", "Animal(i)", "M_i",
                "a_Ti", "f_rk", "a_rk");
        M_k = 1.0;
        System.out.println();
        dest.println();
        sp = 0;
        for (SpeciesType animalType : ServerResources.getSpeciesTable().getAnimals()) {
            System.out.printf("%30s,", animalType.getName());
            dest.printf("%30s,", animalType.getName());
            for (int nodeIdx : animalType.getNodeList()) {
                a_Ti = Constants.A_T[animalType.getSimTestNode(nodeIdx).getMetType()];
                M_i = animalType.getSimTestNode(nodeIdx).getPerUnitBiomass();
                f_rk = Constants.F_R;

                a_rk = 1.0;
                M_k = 1.0;
                System.out.printf("[%4d],%10.4f,%10.4f,%10.4f,%10.4f",
                        animalType.getID(), M_i, a_Ti, f_rk, a_rk);
                dest.printf("[%4d],10.4f,%10.4f,%10.4f,%10.4f",
                        animalType.getID(), M_i, a_Ti, f_rk, a_rk);

                x_i = a_Ti / (f_rk * a_rk) * Math.pow(M_k / M_i, 0.25);
                System.out.printf(",%10.4f", x_i);
                dest.printf(",%10.4f", x_i);

                nonNormX[sp++] = x_i;
                normFactor = Math.max(normFactor, x_i);

                System.out.println();
                dest.println();
            }
        }
        if (normFactor > 0) {
            sp = 0;
            for (SpeciesType animalType : ServerResources.getSpeciesTable().getAnimals()) {
                System.out.printf("%30s,", animalType.getName());
                dest.printf("%30s,", animalType.getName());
                for (int nodeIdx : animalType.getNodeList()) {
                    a_Ti = Constants.A_T[animalType.getSimTestNode(nodeIdx).getMetType()];
                    M_i = animalType.getSimTestNode(nodeIdx).getPerUnitBiomass();
                    f_rk = Constants.F_R;

                    a_rk = 1.0;
                    M_k = 1.0;
                    System.out.printf("[%4d],%10.4f,%10.4f,%10.4f,%10.4f",
                            animalType.getID(), M_i, a_Ti, f_rk, a_rk);
                    dest.printf("[%4d],%10.4f,%10.4f,%10.4f,%10.4f",
                            animalType.getID(), M_i, a_Ti, f_rk, a_rk);

                    normX[sp] = nonNormX[sp] / normFactor;
                    System.out.printf(",%10.4f", normX[sp]);
                    dest.printf(",%10.4f", normX[sp]);
                    System.out.println();
                    dest.println();
                    try {
                        SetMetabolicRate.setBiomassMetRate((float) M_i, (float) normX[sp], animalType.getID());
                    } catch (SQLException ex) {
                        Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    sp++;
                }
            }
        }
        dest.close();
    }

    //one-time run to redefine SimJob.node_config to include perUnitBiomass and
    //insert correction in table
    public void addPerUnitBiomassToNodeConfig() {
        List<Integer> simJobs = null;
        SimJob job = null;
        try {
            simJobs = SimJobDAO.getJobIdsToInclude("");
        } catch (SQLException ex) {
            Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Integer jobId : simJobs) {
            try {
                job = SimJobDAO.loadCompletedJob(jobId);
            } catch (SQLException ex) {
                Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                job.buildNodeConfig();
            } catch (Exception ex) {
                Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                job.saveJob();
            } catch (SQLException ex) {
                Logger.getLogger(SetMetabolicRate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    public static void main(String args[]) throws FileNotFoundException {
        SetMetabolicRate anal = new SetMetabolicRate();

        //addPerUnitBiomassToNodeConfig();
        evalMetRate();

    }
}
