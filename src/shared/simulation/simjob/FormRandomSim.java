/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * FormRandomSim is a JDialog form that allows entry of information to be used
 to generate random simulations in SimJobManager.java. Accessed from
 * SimJobMenu.java.
 *
 * @author Justina
 */
class FormRandomSim extends JDialog {

    private SimJobRandom jobTemplate = null;
    private SimJobManager jobMgr = null;
    private int numJobs = 1;

    // Change if you want, but you'll have to fix some other parts too
    private final int rows = 13, // Change this to match your number of items
            cols = 4;   // Change this at your own risk
    private final int width = cols * 200,
            height = rows * 40;
    // Special font for control buttons
    private final Font bold = new Font("Sans Serif", Font.BOLD, 20);
    
    //default constructor
    public FormRandomSim() {

    }

    //constructor called by SimJobMenu
    public FormRandomSim(java.awt.Frame parent, boolean modal) {
        super(parent, true);

        Container content = getContentPane();

        // Use a grid to layout buttons and add one row for control buttons
        content.setLayout(new GridLayout(rows, cols));
        setSize(width, height);         // Define dialog dimensions
        setLocationRelativeTo(null);    // Center

        //define labels and data entry fields
        final JLabel minSpeciesLabel = new JLabel("Min # Species (int): ", SwingConstants.RIGHT);
        final JTextField minSpeciesField = new JTextField(String.valueOf(SimJobRandom.DFLT_MIN_SPECIES));

        final JLabel maxSpeciesLabel = new JLabel("Max # Species (int): ", SwingConstants.RIGHT);
        final JTextField maxSpeciesField = new JTextField(String.valueOf(SimJobRandom.DFLT_MAX_SPECIES));

        final JLabel maxBiomassLabel = new JLabel("Max Biomass/Species Coefficient (int): ", SwingConstants.RIGHT);
        final JTextField maxBiomassField = new JTextField(String.valueOf(SimJobRandom.DFLT_MAX_BIOMASS));

        final JLabel timestepsLabel = new JLabel("Timesteps (int): ", SwingConstants.RIGHT);
        final JTextField timestepsField = new JTextField(String.valueOf(SimJobRandom.DFLT_TIMESTEPS));

        final JLabel ppTotalLabel = new JLabel("Primary Producer (PP) Total Biomass (double): ", SwingConstants.RIGHT);
        final JTextField ppTotalField = new JTextField(String.valueOf(SimJobRandom.DFLT_PP_TOTAL_BIOMASS));

        final JLabel ppPerUnitLabel = new JLabel("PP Per Unit Biomass (double): ", SwingConstants.RIGHT);
        final JTextField ppPerUnitField = new JTextField(String.valueOf(SimJobRandom.DFLT_PP_PER_UNIT_BIOMASS));

        final JLabel ppParamKLabel = new JLabel("PP Carrying Capacity (k) (double): ", SwingConstants.RIGHT);
        final JTextField ppParamKField = new JTextField(String.valueOf(SimJobRandom.DFLT_PP_PARAMK));

        /*9/29/14, jtc, make it optional to include base ecosystem species.  The functionality
        exists to increase likelihood of viable predator/prey relationships into the i
        initial ecosystem*/
        final JLabel createBaseLabel = new JLabel(
                "Create base ecosystem (decay, herbivore, insect, tree): ",
                SwingConstants.RIGHT);
        final JCheckBox createBaseEcocsysCheckBox = new JCheckBox();
        createBaseEcocsysCheckBox.setSelected(SimJobRandom.DFLT_CREATEBASE);       
        //11/9/14, jtc, add option to use SimTestNode settings or established settings.
        final JLabel useSimTestNodeLabel = new JLabel(
                "Use SimTestNode DB experimental values: ",
                SwingConstants.RIGHT);
        final JCheckBox useSimTestNodeCheckBox = new JCheckBox();
        useSimTestNodeCheckBox.setSelected(SimJob.DFLT_USE_SIMTESTNODE_VALS);
        //11/9/14, jtc, add option to randomly determine grass biomass.
        final JLabel randomGrassLabel = new JLabel(
                "Randomly determine grass biomass (with above value as min): ",
                SwingConstants.RIGHT);
        final JCheckBox randomGrassCheckBox = new JCheckBox();
        randomGrassCheckBox.setSelected(SimJobRandom.DFLT_RANDOMGRASS);
        //11/9/14, jtc, add option to prune species without prey.
        final JLabel pruneLabel = new JLabel(
                "Prune species without prey or links to PP: ",
                SwingConstants.RIGHT);
        final JCheckBox pruneCheckBox = new JCheckBox();
        pruneCheckBox.setSelected(SimJobRandom.DFLT_PRUNE);

        final JLabel numJobsLabel = new JLabel("# of Jobs to Run with these Settings: ", SwingConstants.RIGHT);
        final JTextField numJobsField = new JTextField(String.valueOf(1));

        // add information to ContentPane
        content.add(minSpeciesLabel);
        content.add(minSpeciesField);
        content.add(maxSpeciesLabel);
        content.add(maxSpeciesField);
        content.add(maxBiomassLabel);
        content.add(maxBiomassField);
        content.add(timestepsLabel);
        content.add(timestepsField);
        content.add(ppTotalLabel);
        content.add(ppTotalField);
        content.add(ppPerUnitLabel);
        content.add(ppPerUnitField);
        content.add(ppParamKLabel);
        content.add(ppParamKField);
        content.add(numJobsLabel);
        content.add(numJobsField);
        content.add(createBaseLabel);
        content.add(createBaseEcocsysCheckBox);
        content.add(useSimTestNodeLabel);
        content.add(useSimTestNodeCheckBox);
        content.add(randomGrassLabel);
        content.add(randomGrassCheckBox);
        content.add(pruneLabel);
        content.add(pruneCheckBox);

        // A temporary "Button" variable used to create each button
        JButton button;

        //add action buttons
        button = new JButton("Run Simulation");
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed Run Simulation in Random Simulation");
                SimJobRandom jobTemplate = new SimJobRandom ();
                
                //save user specifications back to job
                if (minSpeciesField.getText().isEmpty()) {
                    jobTemplate.setMinSpecies(SimJobRandom.DFLT_MIN_SPECIES);
                } else {
                    jobTemplate.setMinSpecies(Integer.parseInt(minSpeciesField.getText().trim()));
                }
                //make sure max species is not less than min species.
                if (maxSpeciesField.getText().isEmpty()) {
                    jobTemplate.setMaxSpecies(Math.max(jobTemplate.getMinSpecies(), SimJobRandom.DFLT_MAX_SPECIES));
                } else {
                    jobTemplate.setMaxSpecies(Math.max(jobTemplate.getMinSpecies(),
                            Integer.parseInt(maxSpeciesField.getText().trim())));
                }
                if (maxBiomassField.getText().isEmpty()) {
                    jobTemplate.setMaxBiomass(SimJobRandom.DFLT_MAX_BIOMASS);
                } else {
                    jobTemplate.setMaxBiomass(Double.parseDouble(maxBiomassField.getText().trim()));
                }
                if (timestepsField.getText().isEmpty()) {
                    jobTemplate.setTimesteps(SimJob.DFLT_TIMESTEPS);
                } else {
                    jobTemplate.setTimesteps(Integer.parseInt(timestepsField.getText().trim()));
                }
                if (ppTotalField.getText().isEmpty()) {
                    jobTemplate.setPpTotalBiomass(SimJobRandom.DFLT_PP_TOTAL_BIOMASS);
                } else {
                    jobTemplate.setPpTotalBiomass(Double.parseDouble(ppTotalField.getText().trim()));
                }
                if (ppPerUnitField.getText().isEmpty()) {
                    jobTemplate.setPpPerUnitBiomass(SimJobRandom.DFLT_PP_PER_UNIT_BIOMASS);
                } else {
                    jobTemplate.setPpPerUnitBiomass(Double.parseDouble(ppPerUnitField.getText().trim()));
                }
                if (ppParamKField.getText().isEmpty()) {
                    jobTemplate.setPpParamK(SimJobRandom.DFLT_PP_PARAMK);
                } else {
                    jobTemplate.setPpParamK(Double.parseDouble(ppParamKField.getText().trim()));
                }
                if (numJobsField.getText().isEmpty()) {
                    numJobs = 1;
                } else {
                    numJobs = Integer.parseInt(numJobsField.getText().trim());
                }
                jobTemplate.setCreateBaseEcosys(createBaseEcocsysCheckBox.isSelected());
                jobTemplate.setUseSimTestNodeVals(useSimTestNodeCheckBox.isSelected());
                jobTemplate.setRandomGrassBiomass(randomGrassCheckBox.isSelected());
                jobTemplate.setPrune(pruneCheckBox.isSelected());

                if (jobTemplate.getMaxSpecies() != 0) {
                    setVisible(false);
                    int jobId = SimJob.NO_ID;
                    int jobsCreated = 0;
                    String jobIds = "";
                    
                    int i = 0;
                    while (i < numJobs) {
                        SimJobRandom job = new SimJobRandom (jobTemplate);
                        try {
                            //generate random components (species, biomass, etc)
                            job.configRandomSimJob();
                            job.setJob_Descript("Random simulation" + "-" + System.currentTimeMillis() % 100000);
                        } catch (SQLException ex) {
                            Logger.getLogger(FormRandomSim.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //configuration may have pruned too many nodes.  if so, try again
                        if (job.getSpeciesNodeList().length < job.getMinSpecies()) {
                            continue;
                        }
                        jobMgr = new SimJobManager();
                        jobMgr.setSimJob(job);
    
                        try {
                            jobId = jobMgr.runSimJob();
                        } catch (Exception ex) {
                            Logger.getLogger(FormRandomSim.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (jobId != SimJob.NO_ID) {
                            jobsCreated++;
                            jobIds = jobIds.concat(jobsCreated > 1 ? ", " : "").
                                    concat(Integer.toString(jobId));
                        }

                        System.out.printf("Random Simulation Job %d of %d\n",
                                i+1, numJobs);
                        i++;
                    }  //end while

                    if (jobsCreated > 0) {
                        JOptionPane.showMessageDialog(null, "Created "
                                + jobsCreated + " Random Simulation jobs.  " + jobIds,
                                "Random Simulation Generated", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Random Simulation failed.",
                                "Random Simulation Generated", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                dispose();
            }

        });
        content.add(button);

        button = new JButton("Cancel"); // Cancel
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed Cancel in Random Simulation");
                dispose();
            }
        });
        content.add(button);

    }

    public void setNumJobs(int numJobs) {
        this.numJobs = numJobs;
    }

    public int getNumJobs() {
        return numJobs;
    }

}
