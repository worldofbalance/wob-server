/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg;

import shared.db.ConvergeAttemptDAO;
import shared.db.ConvergeEcosystemDAO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import shared.metadata.Constants;
import shared.simulation.simjob.EcosystemTimesteps;
import shared.simulation.simjob.ExtractCSVData;
import shared.util.ConfigureException;

/**
 *
 * @author justinacotter
 */
public class ConvergeAttemptAnal {

    public static void printAttempts(
            PrintStream ps,
            String str,
            int currMax,
            int maxCnt) {
        //prepend # of attempts made
        str = String.format(",%d%s", currMax + 1, str);

        //pad line if necessary so that each entry in csv has
        //same number of elements
        for (int i = currMax + 1; i < maxCnt; i++) {
            str += ",,";
        }
        //moving println to main body
        ps.println(str);
    }

    public static void main(String args[])
            throws FileNotFoundException, ConfigureException {
        List<ConvergeAttempt> attempts = ConvergeAttemptDAO.getConvergeAttempts();
        if (attempts.isEmpty()) {
            throw new ConfigureException("Attempt retrieval failed.");
        }
        PrintStream ps = null;
        ps = new PrintStream(new FileOutputStream(
                "/users/justinacotter/documents/sfsuilmi/convergedata.csv"));

        //first need to count max number of attempts
        int playerId = Constants.ID_NOT_SET;
        int ecoId = Constants.ID_NOT_SET;
        int maxCnt = 0;
        int currMax = 0;
        for (ConvergeAttempt attempt : attempts) {
            if (attempt.getPlayerId() != playerId
                    || attempt.getEcosystemId() != ecoId) {
                if (currMax > maxCnt) {
                    maxCnt = currMax;
                }
                currMax = 1;
                playerId = attempt.getPlayerId();
                ecoId = attempt.getEcosystemId();
            } else {
                currMax++;
            }
        }
        if (currMax > maxCnt) {
            maxCnt = currMax;
        }

        //print header
        ps.print("player,allow-hints,ecosys,attempts,");
        for (int i = 0; i < maxCnt; i++) {
            int attemptNo = i + 1;
            ps.printf("hint-%d,score-%d", attemptNo, attemptNo);
            if (i < (maxCnt - 1)) {
                ps.print(",");
            }
        }
        ps.println();
        //ps.close();
        //ps = new PrintStream(new FileOutputStream(
        //        "/users/justinacotter/documents/sfsuilmi/convergedata2.csv"));        

        playerId = Constants.ID_NOT_SET;
        ecoId = Constants.ID_NOT_SET;
        currMax = Constants.ID_NOT_SET;
        String header = "";
        String attemptInfo = "";
        for (ConvergeAttempt attempt : attempts) {
            if (attempt.getPlayerId() != playerId
                    || attempt.getEcosystemId() != ecoId) {
                //print prior record
                if (currMax != Constants.ID_NOT_SET) {
                    printAttempts(ps, attemptInfo, currMax, maxCnt);
                }
                currMax = 0;
                attemptInfo = "";
                playerId = attempt.getPlayerId();
                ecoId = attempt.getEcosystemId();
                ps.printf(
                        "%d,%d,%d",
                        playerId,
                        attempt.getAllowHints() ? 1 : 0,
                        ecoId);
            } else {
                currMax++;
            }

            int attemptId = attempt.getAttemptId();
            int hintId = attempt.getHintId();
            int score = attempt.getScore();
            int tempScore = 0;

            //get csv info (etc) about the current attempt
            ConvergeAttempt fullInfo = ConvergeAttemptDAO.getConvergeAttempt(
                    playerId, ecoId, attemptId);
            EcosystemTimesteps attemptData = ExtractCSVData.extractCSVData(
                    fullInfo.getCsv());
            //get csv info (etc) about the ecosystem target
            ConvergeEcosystem ecosys = ConvergeEcosystemDAO.
                    getConvergeEcosystem(ecoId);
            EcosystemTimesteps targetData = ExtractCSVData.extractCSVData(
                    ecosys.getCsvTarget());

            //calculate the score (only doing this for ALL to make sure that
            //scoring is consistent as I reimplemented on server (orig on client)
            if (score == Constants.ID_NOT_SET) {
                score = attemptData.calculateConvergeScore(targetData);
            } else {
                tempScore = attemptData.calculateConvergeScore(targetData);
            }
            attemptInfo += String.format(",%s,%d",
                    hintId == Constants.ID_NOT_SET ? "" : String.valueOf(hintId),
                    score);
        }
        //print final records
        printAttempts(ps, attemptInfo, currMax, maxCnt);

        ps.close();

    }
}
