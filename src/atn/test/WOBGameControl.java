package atn.test;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import atn.ATNEngine;
import metadata.Constants;
import model.Account;
import model.Ecosystem;
import model.Player;
import model.Species;
import model.SpeciesType;
import util.ConfigureException;
import util.ExpTable;
import util.GameTimer;
import util.Log;
import core.EcosystemController;
import core.GameEngine;
import core.GameServer;
import core.ServerResources;
import core.lobby.EcosystemLobby;
import core.world.World;
import core.world.WorldController;
import db.AccountDAO;
import db.EcoSpeciesDAO;
import db.EcosystemDAO;
import db.PlayerDAO;
import db.UserLogDAO;


class WOBGameControl extends JFrame {
    final static Logger logger = Logger.getLogger(WOBGameControl.class.getName());
    private static Player player;
    private static EcosystemLobby lobby;
    private static World world;
    private static Ecosystem ecosystem;
	private static Account account;
    private static GameEngine gameEngine;
    private Map<Integer, Integer> speciesList;
    private static long lastSave = System.currentTimeMillis(); // Last time saved to the database
    private static long lastActivity = System.currentTimeMillis();
    private final int rows = 4,
            cols = 1,
            width = cols * 1000,
            height = rows * 100;
    // Special font for control buttons
    private final Font bold = new Font("Sans Serif", Font.BOLD, 20);
    
    // Menu options
    private final String[] functions = {
        "Login, Enter World/Lobby",
        "Create/Start Ecosystem",
//        "Start Ecosystem",
        "Increase Biomass of existing species",
        "Decreasing biomass of existing species",
        "Add New Species",
        "Delete Species",
        "Run Simulation",
        "Log Out"
    };

	private JPanel content;
	private Properties propertiesConfig;
	private boolean LOAD_SIM_TEST_PARAMS = true;
    
//    "Increasing biomass of existing species",
//    "Decreasing biomass of existing species",
//    "Add New Species",
//    "Delete Species"
    //Step1 constructor
    public WOBGameControl() {
        super("World Of Balance Game");

        setSize(width, height);         // Define dialog dimensions
        setLocationRelativeTo(null);    // Center
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    
    private void addContents(final JFrame frame) {

    	content = new JPanel();
        add(content);
        content.setLayout(new GridLayout(rows, cols));
        // A temporary "Button" variable used to create each button
        JButton button;

        // Control function buttons        
        button = new JButton(functions[0]); // Login, Enter World/Lobby
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[0]);
				loginWithCreds();
            	init();
				enterWorld();
            }
        });
        content.add(button,0);

        button = new JButton(functions[1]); // Create/Start Ecosystem
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[1]);
                createEcosystem();
            }
        });
        content.add(button,1);
        
        button = new JButton(functions[2]); // Increase Biomass
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("You pressed " + functions[2]);
            	//increaseBiomass();
            }
        });
        content.add(button,2);
        
        button = new JButton(functions[3]); // Decrease Biomass
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("You pressed " + functions[3]);
            	//decreaseBiomass();
            }
        });
        content.add(button,3);
        
        button = new JButton(functions[4]); // Add new species
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("You pressed " + functions[4]);
            	//addNewSpecies();
            }
        });
        content.add(button,4);
        
        button = new JButton(functions[5]); // Delete species
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("You pressed " + functions[5]);
            	//deleteSpecies();
            }
        });
        content.add(button,5);
        

        button = new JButton(functions[6]); // Run Simulation
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[6]);
                runSimulations();
            }
        });
        content.add(button,6);
        
        button = new JButton(functions[7]); // Log Out
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[7]);
                logout();
            }
        });
        content.add(button,7);
        
        disableAll();
        enable(0);
    }
    
    public void init(){
        //load properties file containing ATN model parameter values
        propertiesConfig = new Properties();
        try {
            propertiesConfig.load(new FileInputStream(
                    "src/atn/SimJobConfig.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ATNEngine.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ATNEngine.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        /* 
        Read in non-std variables used for running sim jobs
        */
       if(LOAD_SIM_TEST_PARAMS ){	//False by default, set to true only in main of this class
	       GameServer.getInstance();
	       SpeciesType.loadSimTestNodeParams(Constants.ECOSYSTEM_TYPE);
	       SpeciesType.loadSimTestLinkParams(Constants.ECOSYSTEM_TYPE);
       }
    }
    
    public void createEcosystem(){
        List<Species> currentSpecies = EcoSpeciesDAO.getSpecies(ecosystem.getID());
        if(currentSpecies != null && currentSpecies.size() == 0){
	        HashMap<Integer, Integer> speciesList = new HashMap<Integer, Integer>();
            logger.info("Adding nodes to ecosystem for first time");
	        speciesList.put(1005, 2000);	//To start we start with 1000 and add another 1000 to keep it consistent
	        speciesList.put(2, 2494);		//African Clawless Otter
	        speciesList.put(42, 240);		//African Grey Hornbill
	        speciesList.put(31, 1415);		//Tree Mouse 	
	        speciesList.put(14, 1752);		//Crickets
	        EcosystemController.createEcosystem(ecosystem, speciesList);       	
        }else{
        	startEcosystem();
        }
        
        disableAll();
        enableEcosystemFunctions(2,6); 	//enable simulation functions ( 2- 6)
    	enable(7);	//enable logout button
    }
    
    public void runSimulations(){
        //We need to map the speciesList in the ecosystem to the zoneNodes in the ecosystem
        lobby.getGameEngine().forceSimulation();   	
    }
    
    public void startEcosystem(){
        logger.info("EcosystemController.startEcosystem() functionality");
        EcosystemController.startEcosystem(player);
		lobby = EcosystemController.getLobby();
		ecosystem = EcosystemController.getEcosystem();
        
        //We need to map the speciesList in the ecosystem to the zoneNodes in the ecosystem
        lobby.getGameEngine().forceSimulation();
        logger.info("Hjr's ecosystem started");
        
    }
    
    public void enterWorld(){
        //Populate world, player, zone and ecosystem info
        logger.info("Getting world");
        world = WorldController.getInstance().get(1);
        
        logger.info("Verify World ID");
        if (world == null) {
            Log.println("Invalid world id.");
            return;
        }
        
        logger.info("Enter World");
        if (!world.hasPlayer(player.getID())) {
            world.add(player);
        }
        player.setWorld(world);
        
        //RequestZone by player = zone_id + player_id
        //RequestZoneUpdate
        //SELECT `player_id` FROM `world_zone` WHERE `zone_id` = 1 , player_id = 1  - Player assigns himself some zone_id one at a time
        //UPDATE `world_zone` SET `player_id` = 162 WHERE `zone_id` = 1
        logger.info("Let us assign zone_id = 1 to player_id = 1");
        int zone_id = 1;
        if (WorldController.getInstance().isOwned(zone_id) == true) {
        	 logger.info("Player id already owns zone_id " + zone_id);
        } else {
            WorldController.getInstance().ownZone(player.getID(), zone_id);
        }
        
        //EcosystemController.startEcosystem(Player player) does the job
        // Retrieve Ecosystem, if haven't already
        if (player.getEcosystem() == null) {
	        //RequestPlayerSelect where a check is made if ecosystem is created based on the last played time of the player
	        logger.info("------RequestPlayerSelect------");
	        int type = 3;
	        PlayerDAO.updateLastPlayed(player.getID());
	        if (player.getLastPlayed() == null) {
	            int world_id = WorldController.getInstance().first().getID();
	            EcosystemController.createEcosystem(world_id, player.getID(), player.getName() + "'s Ecosystem", (short) type);
	        }
	        
	        logger.info("------RequestWorld------");
	        // Get Player Ecosystem
	        logger.info("Getting Player ecosystem");
	        ecosystem = EcosystemDAO.getEcosystem(player.getWorld().getID(), player.getID());
	        if (ecosystem == null) {
	            return;
	        }
        } 
        
        disableAll();
        enable(1);	//enable create/start ecosystem
    	enable(7);	//enable logout button
    	
    }
    
    public void loginWithCreds(){
	    //Server intitializationa and startup
        logger.info("Initialize tables for global use");
        try {
        	// Initialize tables for global use
            ServerResources.init();			//Commented out the temporary override of biomass value in initialize method
			WorldController.getInstance().init();
	        ExpTable.init(); // Contains experience required per level
		} catch (ConfigureException e) {
			e.printStackTrace();
		}
        
        //Client login
	    logger.info("------RequestLogin------");
	    logger.info("Initialize player and login simulation");
	    account = AccountDAO.getAccount("hjr", "hjr");
	    if(account == null){
	    	logger.info("Player not registered");
	    	return;
	    }
	    player = PlayerDAO.getPlayerByAccount(account.getID());
	   
        AccountDAO.updateLogin(account.getID(), "127.0.0.1");
        startSaveTimer();
        
    }
    
    public void logout(){
    	if(account != null && player != null){
	        if (player.getWorld() != null) {
	            player.getWorld().remove(player.getID());
	        }
	
	        //GameServer.getInstance().removeActivePlayer(player.getID());
	        PlayerDAO.updateLastPlayed(player.getID());
	        player = null;
	    	logger.info("Player logged out");
    	}else{
    		logger.info("You need to log in");
    	}
    	disableAll();	//disable logout button
    	enable(0);	//enable login button
    }
    
    public void enable(int index){
    	content.getComponent(index).setEnabled(true);
    }
    
    public void disable(int index){
    	content.getComponent(index).setEnabled(false);
    }
    
    public void disableAll(){
    	for(int i = 0; i< functions.length; i++){
    		content.getComponent(i).setEnabled(false);
    	}
    }
    
    public void enableEcosystemFunctions(int start, int end){
    	for(int i = start; i<= end; i++){
    		content.getComponent(i).setEnabled(true);
    	}
    }
    
    public static void startSaveTimer() {
        GameTimer saveTimer = new GameTimer();
        saveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                long current = System.currentTimeMillis();
                long seconds = (current - lastSave) / 1000;

                account.setPlayTime(account.getPlayTime() + seconds);
                lastSave = current;

                AccountDAO.updatePlayTime(account.getID(), account.getPlayTime(), account.getActiveTime());
                UserLogDAO.updateTimeLog(account.getID(), (int) seconds);
            }
        }, Constants.SAVE_INTERVAL, Constants.SAVE_INTERVAL);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(WOBGameControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	WOBGameControl menu = new WOBGameControl();
                //9/25/14, jtc, add close functionality
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //9/25/14, jtc, pass frame for use by actionlisteners
                menu.addContents(menu);
                menu.setVisible(true);
            }
        });
    }
}
    
