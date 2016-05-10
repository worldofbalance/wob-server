/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import shared.atn.ATNEngine;
import shared.atn.Functions;
import shared.metadata.Constants;

/**
 * Class made to convert 400 timesteps to read only the last 200 timesteps
 *
 * @author Harjit
 */
public class ATNConverter {

    public static String ConvertCSV(String csv, int startIndex) {
        String converted = "";
        String[] lines = csv.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String[] lineElements = lines[i].split(",");

            //store name or blank start
            converted = converted.concat(lineElements[0] + ",");
            for (int j = startIndex; j < lineElements.length; j++) {
                converted = converted.concat(lineElements[j] + ",");
            }
            converted = converted.substring(0, converted.length() - 1);
            converted = converted.concat("\n");

        }
        return converted;

    }

    public static void main(String[] args) {
        try {
            //ATNEngine.main(args);
            BufferedReader reader = new BufferedReader(new FileReader(Constants.ATN_CSV_SAVE_PATH + "ATN_1.csv"));

            String csv = "";
            String line;
            while ((line = reader.readLine()) != null) {
                csv = csv.concat(line + "\n");
            }
            csv = ATNConverter.ConvertCSV(csv, 200);
            System.out.println("Got here. File saved: " + Constants.ATN_CSV_SAVE_PATH + "test_conversion.csv");
            PrintStream ps = new PrintStream(new FileOutputStream(Constants.ATN_CSV_SAVE_PATH + "test_conversion.csv"));
            ps.printf(csv);
            ps.close();
     
        } catch (Exception e) {

        }

    }

}
