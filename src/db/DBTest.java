
package db;

import PlayTime.PlayTimePlayer;


/**
 *  test file for DAO usage. only for running within DAO package
 * @author Karl
 */
public class DBTest {
    public static void main(String[] args)
    {
        try
        {
            GameDB gameDB =  new GameDB();
            PlayTimePlayer r1, r2;
            
            // check connection
            if (gameDB.getConnection() != null)
            {
                System.out.println("Successfully connected to database.\n");
            }
            
            // define two race players
            r1 = new PlayTimePlayer(1, 1);
            r2 = new PlayTimePlayer(2, 1);
            
            // race player 1
            r1.setRunnerSpeciesID(31);
            r1.setMaxHealth(100);
            r1.setHealth(100);
            r1.setMaxStamina(80);
            r1.setStamina(79);
            r1.setLeft(false);
            r1.setPower(50);
            r1.setRight(true);
            r1.setScore(100);
            r1.setSpeed(500);
            //r1.setStatus(1);
            //r1.setTime(60);
            r1.setX(5);
            r1.setY(5);
            
            // race player 2
            r2.setRunnerSpeciesID(88);
            r2.setMaxHealth(100);
            r2.setHealth(75);
            r2.setLeft(true);
            r2.setPower(1.0f);
            r2.setRight(false);
            r2.setScore(200);
            r2.setSpeed(250.5f);
            //r2.setStatus(3);
            //r2.setTime(55);
            r2.setX(7.7f);
            r2.setY(3.3f);
            
            // test species retrieval
//            testSpeciesMap = RunnerSpeciesDAO.getRunnerSpecies();
//            for (int ID : testSpeciesMap.keySet())
//            {
//                rs = testSpeciesMap.get(ID);
//                System.out.println(rs.getName());
//                System.out.println("- Health: " + rs.getHealth());
//                System.out.println("- Speed: " + rs.getSpeed());
//                System.out.println("- Power: " + rs.getPower());
//                System.out.println();
//            }
            
            
            
            // test race creation, joining, finishing, leaving logic
            //player 1 makes race, player 2 joins
            PlayDAO.createPlay(r1.getRaceID());
            System.out.println("created race");
            PlayDAO.createPlayer(r1.getPlayer_id(), r1.getRaceID(),2);
            PlayDAO.createPlayer(r2.getPlayer_id(), r2.getRaceID(),1);
            System.out.println("created players");
            PlayDAO.updatePlay(r1);
            PlayDAO.updatePlay(r2);
            PlayDAO.leavePlay(r1);
            PlayDAO.leavePlay(r2);
            System.out.println("ended first game");
            //changing the race
            r1.setRaceID(101);
            r1.setRunnerSpeciesID(47);
            
            r2.setRaceID(101);
            r2.setRunnerSpeciesID(74);
            
            PlayDAO.createPlay(r2.getRaceID());
            System.out.println("created second game");
            PlayDAO.createPlayer(r2.getPlayer_id(), r2.getRaceID(),2);
            PlayDAO.createPlayer(r1.getPlayer_id(), r1.getRaceID(),1);
            
//            PlayDAO.setPlayerSpecies(r1);
//            PlayDAO.setPlayerSpecies(r2);
            PlayDAO.updatePlay(r1);
            PlayDAO.updatePlay(r2);
            
            
            PlayDAO.leavePlay(r1);
            PlayDAO.leavePlay(r2);
        }
        
        // if any exceptions thrown...
        catch (Exception e)
        {
            System.err.println("Error in database connection.");
        }
    }
}
