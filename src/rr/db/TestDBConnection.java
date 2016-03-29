// Test connection to TheCity DB.
// @author: Joseph Fernandez

package rr.db;

// Java imports
import java.util.Map;

// Other imports
import rr.model.RunnerSpecies;
import rr.model.RunnerItems;
import rr.race.RacePlayer;

public class TestDBConnection
{
    public static void main(String[] args)
    {
        try
        {
            Map<Integer, RunnerSpecies> testSpeciesMap;
            Map<Integer, RunnerItems> testItemsMap;
            RunnerSpecies rs;
            RunnerItems ri;
            RacePlayer r1, r2;
            
            // check connection
            if (DAO.getInstance() != null)
            {
                System.out.println("Successfully connected to database.\n");
            }
            
            // define two race players
            r1 = new RacePlayer(1, 1);
            r2 = new RacePlayer(2, 1);
            
            // race player 1
            r1.setRunnerSpeciesID(31);
            r1.setHealth(100);
            r1.setItemID(1);
            r1.setJump(false);
            r1.setLeft(false);
            r1.setNumItems(5);
            r1.setPower(50);
            r1.setRight(true);
            r1.setScore(100);
            r1.setSpeed(500);
            r1.setStatus(1);
            r1.setTime(60);
            r1.setX(5);
            r1.setY(5);
            
            // race player 2
            r2.setRunnerSpeciesID(88);
            r2.setHealth(75);
            r2.setItemID(2);
            r2.setJump(true);
            r2.setLeft(true);
            r2.setNumItems(3);
            r2.setPower(75.5f);
            r2.setRight(false);
            r2.setScore(200);
            r2.setSpeed(250.5f);
            r2.setStatus(3);
            r2.setTime(55);
            r2.setX(7.7f);
            r2.setY(3.3f);
            
            // test species retrieval
            testSpeciesMap = RunnerSpeciesDAO.getRunnerSpecies();
            for (int ID : testSpeciesMap.keySet())
            {
                rs = testSpeciesMap.get(ID);
                System.out.println(rs.getName());
                System.out.println("- Health: " + rs.getHealth());
                System.out.println("- Speed: " + rs.getSpeed());
                System.out.println("- Power: " + rs.getPower());
                System.out.println();
            }
            
            // test item retrieval
            testItemsMap = RunnerItemsDAO.getRunnerItems();
            for (int ID : testItemsMap.keySet())
            {
                ri = testItemsMap.get(ID);
                System.out.println(ri.getItemName());
                System.out.println("- Health Bonus: " + ri.getHealthBonus());
                System.out.println("- Speed Bonus: " + ri.getSpeedBonus());
                System.out.println("- Power Bonus: " + ri.getPowerBonus());
                System.out.println();
            }
            
            // test race creation, joining, finishing, leaving logic
            RaceDAO.createRace(r1.getRaceID());
            RaceDAO.createPlayerRecord(r1.getID(), r1.getRaceID());
            RaceDAO.createPlayerRecord(r2.getID(), r2.getRaceID());
            RaceDAO.leaveRace(r1);
            RaceDAO.leaveRace(r2);
            
            r1.setRaceID(101);
            r1.setRunnerSpeciesID(47);
            
            r2.setRaceID(101);
            r2.setRunnerSpeciesID(74);
            
            RaceDAO.createRace(r2.getRaceID());
            RaceDAO.createPlayerRecord(r2.getID(), r2.getRaceID());
            RaceDAO.createPlayerRecord(r1.getID(), r1.getRaceID());
            
            RaceDAO.setPlayerSpecies(r1);
            RaceDAO.setPlayerSpecies(r2);
            RaceDAO.updateRace(r1);
            RaceDAO.updateRace(r2);
            
            // RaceDAO.finishRace(r1, 1, 50, 500);
            // RaceDAO.finishRace(r2, 2, 100, 400);
            RaceDAO.leaveRace(r1);
            RaceDAO.leaveRace(r2);
        }
        
        // if any exceptions thrown...
        catch (Exception e)
        {
            System.err.println("Error in database connection.");
        }
    }
}
