/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.targetgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import shared.metadata.Constants;

/**
 *
 * @author Harjit
 */
public class AtnAnalyzer {

    ArrayList<String> accepted;

    public AtnAnalyzer(String directory) {
        accepted = new ArrayList<String>();
        // Determine filename
        File dir = new File(directory);
        String[] files = dir.list();
        BufferedReader br = null;
        String line = "";
        for (int i = 0; i < files.length; i++) {
            if (files[i].endsWith(".csv")) {
                String csvFile = directory + "/" + files[i];
                int lineno = 0, maxLowSpecies = 1;

                try {
                    br = new BufferedReader(new FileReader(csvFile));
                    while ((line = br.readLine()) != null) {
                        lineno++;
                        if (validLineNo(lineno)) {
                            String[] info = line.split(",");
                            for (int j = info.length - 10; j < info.length; j++) {
                                int num = Integer.parseInt(info[j].trim());
                                if (num <= 3) {
                                    maxLowSpecies--;
                                }
                            }
                        }
                    }
                    //if we went through every species and no species dies, add it to the accepted
                    if (maxLowSpecies > 0) {
                        accepted.add(files[i]);
                    }
                    br.close();
                } catch (Exception e) {
                    if (e instanceof NumberFormatException) {
                        System.out.println("NumberFormat Exception on file: " + files[i] + ". Probably not a valid file because a species dominated the ecosystem."
                                + "\n Check file manually: " + files[i]);
                        
                    } else {
                        e.printStackTrace();
                    }
                } 
            }
        }

    }

    void moveFiles(String directory, String targetDirectory, int numOfSpecies) {
        File initialDirectory = new File(directory);
        String files[] = initialDirectory.list();
        int addIndex;
        String logFileName = "LastLog";
        String logfileLocation = directory + logFileName;
        String line = "";
        targetDirectory = targetDirectory+numOfSpecies+"/";
        File target = new File(targetDirectory);
        if(!target.exists()){
            target.mkdirs();
        }
        addIndex = target.list().length;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(logfileLocation));
            while ((line = br.readLine()) != null) {
                String acceptedFile = directory + line;
                String acceptedParamFile = acceptedFile.substring(0,acceptedFile.indexOf(".csv")) + "_node_params";
                String targetFile = targetDirectory+line;
                targetFile = targetFile.substring(0, targetFile.indexOf("N")+1) + addIndex + ".csv";
                String targetParamFile = targetFile.substring(0, targetFile.indexOf(".csv")) + "_node_params";
                addIndex++;
                Files.move(Paths.get(acceptedFile), Paths.get(targetFile), StandardCopyOption.REPLACE_EXISTING);
                Files.move(Paths.get(acceptedParamFile), Paths.get(targetParamFile), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File: " + acceptedFile + " moved to " + targetFile);
                
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        
        //clear generated files
        /*for(int i = 0; i < files.length; i++){
            try{
            Files.deleteIfExists(Paths.get(directory + files[i]));
            }catch(Exception e){
                e.printStackTrace();
            }
        }*/
    }

    boolean validLineNo(int lineno) {
        return lineno != 1;
    }

    public void writeLog() {
        String fileName = Constants.ATN_GENERATED_CSV_SAVE_PATH + "LastLog";

        try {
            PrintStream ps = new PrintStream(new FileOutputStream(fileName));
            for (int i = 0; i < accepted.size(); i++) {
                ps.printf(accepted.get(i) + "\n");
            }
            ps.close();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many species do you want in the ecosystem?");
        int n = scanner.nextInt();
        System.out.println("How many attempts do you want?");
        int attempts = scanner.nextInt();
        //EcosystemGenerator.GenerateEcosystems(n, attempts);
        AtnAnalyzer analyzer = new AtnAnalyzer(Constants.ATN_GENERATED_CSV_SAVE_PATH);
        analyzer.writeLog();
        analyzer.moveFiles(Constants.ATN_GENERATED_CSV_SAVE_PATH, Constants.ATN_ACCEPTED_CSV_SAVE_PATH, n);
        System.out.println("Accepted: ");
        for (int i = 0; i < analyzer.accepted.size(); i++) {
            System.out.println(analyzer.accepted.get(i));
        }
    }
}
