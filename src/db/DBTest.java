
package db;

import PlayTime.PlayTimePlayer;
import java.util.HashMap;
import java.util.Map;
import model.Prey;
import model.SDSpecies;
import utility.Log;


/**
 *  test file for DAO usage. only for running within DAO package
 * @author Karl
 */
public class DBTest {
    public static void main(String[] args)
    {
        Map<Integer, SDSpecies> playSpecies;
        PlayTimePlayer r1, r2, rx;
        int i;
        Prey prey;
        Map<Integer, Prey> preyMap = new HashMap<Integer,Prey>();
        try
        {
            GameDB gameDB =  new GameDB();
            
            
            // check connection
            if (gameDB.getConnection() != null)
            {
                System.out.println("Successfully connected to database.\n");
            }
            playSpecies = SDSpeciesDAO.getSDSpecies();
            Log.println("max health of id 90 (mackerel):"+playSpecies.get(90).getHealthMax());
            // define two race players
            r1 = new PlayTimePlayer(1, 1);
            r2 = new PlayTimePlayer(2, 1);
            rx = new PlayTimePlayer(3);
            for(i=2; i<20; ){
                prey = new Prey(i,0.0F,0.0F);
                preyMap.put(i, prey);
                i+=1;
            }
            prey = new Prey(42,0.0F,0.0F);
            NpcFishDAO.CreateNpcRecordsForPlay(preyMap, 2);
            Log.println("recorded map of npcfish.");
            NpcFishDAO.createNpcRecord(prey, 2);
            Log.println("added final fish to game 2");
            prey.setX(1.0F);
            NpcFishDAO.updateNpcRecord(prey, 2);
            Log.println("updated single fish record");
            NpcFishDAO.updateAllNpcRecordsForPlay(preyMap, 2);
            Log.println("updated map of prey objects.");
            
            NpcFishDAO.removeNpcRecord(prey.getPrey_id(), 2);
            Log.println("removed indiv prey from game");
            NpcFishDAO.removeNpcRecordsForPlay(2);
            Log.println("npcrecords for play 2 removed from db");
            // race player 1
//            r1.setRunnerSpeciesID(31);
//            r1.setMaxHealth(100);
//            r1.setHealth(100);
//            r1.setMaxStamina(80);
//            r1.setStamina(79);
//            r1.setLeft(false);
//            r1.setPower(50);
//            r1.setRight(true);
//            r1.setScore(100);
//            r1.setSpeed(500);
//            //r1.setStatus(1);
//            //r1.setTime(60);
//            r1.setX(5);
//            r1.setY(5);
//            
//            // race player 2
//            r2.setRunnerSpeciesID(88);
//            r2.setMaxHealth(100);
//            r2.setHealth(75);
//            r2.setLeft(true);
//            r2.setPower(1.0f);
//            r2.setRight(false);
//            r2.setScore(200);
//            r2.setSpeed(250.5f);
//            //r2.setStatus(3);
//            //r2.setTime(55);
//            r2.setX(7.7f);
//            r2.setY(3.3f);
//            
//            // test species retrieval
////            testSpeciesMap = RunnerSpeciesDAO.getRunnerSpecies();
////            for (int ID : testSpeciesMap.keySet())
////            {
////                rs = testSpeciesMap.get(ID);
////                System.out.println(rs.getName());
////                System.out.println("- Health: " + rs.getHealth());
////                System.out.println("- Speed: " + rs.getSpeed());
////                System.out.println("- Power: " + rs.getPower());
////                System.out.println();
////            }
//            
//            
//            
//            // test race creation, joining, finishing, leaving logic
//            //player 1 makes race, player 2 joins
//            PlayDAO.createPlay(r1.getRaceID());
//            PlayDAO.createPlayer(r1.getPlayer_id(), r1.getRaceID(),2);
//            PlayDAO.createPlayer(r2.getPlayer_id(), r2.getRaceID(),1);
//            PlayDAO.updatePlay(r1);
//            PlayDAO.updatePlay(r2);
//            
//            PlayDAO.leavePlay(r1);
//            PlayDAO.leavePlay(r2);
//            //changing the race
//            r1.setRaceID(101);
//            r1.setRunnerSpeciesID(47);
//            
//            r2.setRaceID(101);
//            r2.setRunnerSpeciesID(74);
//            
//            PlayDAO.createPlay(r2.getRaceID());
//            PlayDAO.createPlayer(r2.getPlayer_id(), r2.getRaceID(),2);
//            PlayDAO.createPlayer(r1.getPlayer_id(), r1.getRaceID(),1);
//            
//            
////            PlayDAO.setPlayerSpecies(r1);
////            PlayDAO.setPlayerSpecies(r2);
//            PlayDAO.updatePlay(r1);
//            PlayDAO.updatePlay(r2);
//            rx = PlayInfoDAO.getPlayerInfo(r1.getPlayer_id());
//            Log.println("retrieved max_health"+rx.getMaxHealth());
//            
//            
//            PlayDAO.leavePlay(r1);
//            PlayDAO.leavePlay(r2);
        }
        
        // if any exceptions thrown...
        catch (Exception e)
        {
            System.err.println("Error in database connection.");
        }
    }
}
