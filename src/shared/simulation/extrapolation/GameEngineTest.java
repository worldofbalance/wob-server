package shared.simulation.extrapolation;

import shared.core.GameEngine;
import lby.core.Lobby;
import lby.core.world.World;
import shared.db.EcosystemDAO;
import shared.db.PlayerDAO;
import lby.db.world.WorldDAO;
import shared.model.Ecosystem;
import shared.model.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test class for GameEngine
 *
 * @author Ben Saylor (brsaylor@gmail.com)
 */
public class GameEngineTest {
    
    final static Logger logger = Logger.getLogger(GameEngineTest.class.getName());
    
    private Player player;
    private Lobby lobby;
    private World world;
    private Ecosystem ecosystem;
    private GameEngine gameEngine;
    
    public void run() {

        logger.info("Getting world");
        // When instantiated, World schedules NEW_DAY events that call
        // WorldDAO.updateDay(), which updates world.day in the database
        world = WorldDAO.getWorld(1);
        
        logger.info("Getting player");
        // Player is a passive object that does not instantiate anything else
        player = PlayerDAO.getPlayer(162);

        logger.info("Creating lobby");
        // Lobby is self-contained; does not instantiate anything other than
        // standard collections; does not call methods of any other objects
        lobby = new Lobby(0, player);

        logger.info("Getting ecosystem");
        /*
        ecosystem = EcosystemDAO.createEcosystem(
                world.getID(),
                player.getID(),
                "GameEngineTest ecosystem",
                (short) 0); // type - guessing 0
                */
        ecosystem = EcosystemDAO.getEcosystem(world.getID(), player.getID());

        logger.info("Adding nodes to ecosystem");
        ecosystem.setNewSpeciesNode(5, 2000); // grass
        ecosystem.setNewSpeciesNode(95, 2000); // elephants

        logger.info("Creating gameEngine");
        gameEngine = new GameEngine(lobby, world, ecosystem);
        logger.info("Done creating gameEngine");

        logger.info("Running simulation");
        gameEngine.forceSimulation();
    }
    
    public static void main(String[] args) {
        logger.info("Starting GameEngineTest");
        
        GameEngineTest gameEngineTest = new GameEngineTest();
        gameEngineTest.run();
    }
}