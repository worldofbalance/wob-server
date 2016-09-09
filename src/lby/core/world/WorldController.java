package lby.core.world;

// Java Imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

// Other Imports
import shared.core.EcosystemController;
import shared.db.NewsDAO;
import lby.db.world.WorldDAO;
import lby.db.world.WorldZoneDAO;
import shared.model.Player;
import lby.net.response.ResponseMessage;
import lby.net.response.world.ResponseWorld;
import shared.util.ConfigureException;
import shared.util.Log;
import shared.util.NetworkFunctions;

public class WorldController {

    // Singleton Instance
    private static WorldController controller;
    // Reference Tables
    private Map<Integer, World> worlds = new HashMap<Integer, World>(); // World ID -> World

    public WorldController() {
    }

    public static WorldController getInstance() {
        return controller == null ? controller = new WorldController() : controller;
    }

    public void init() throws ConfigureException {
        Log.console("Loading Worlds...");

        worlds = WorldDAO.getWorlds();
        if (worlds.isEmpty()) {
            throw new ConfigureException("Worlds retrieval failure");
        }

        for (Entry<Integer, World> entry : worlds.entrySet()) {
            entry.getValue().setZones(WorldZoneDAO.getZoneList(entry.getKey().intValue()));
        }

        Log.println("Done!");
    }

    public World first() {
        return new ArrayList<World>(worlds.values()).get(0);
    }

    public World get(int world_id) {
        return worlds.get(world_id);
    }

    public static boolean enterWorld(Player player, int world_id) {
        World world = WorldController.getInstance().get(world_id);
        
        // Verify World ID
        if (world == null) {
            Log.println("Invalid world id.");
            return false;
        }
        
        // Enter World
        if (!world.hasPlayer(player.getID())) {
            world.add(player);
        }
        player.setWorld(world);
        
        // Send World Information to User
        ResponseWorld response = new ResponseWorld();
        response.setStatus(ResponseWorld.SUCCESS);
        response.setWorld(world.getID(), world.getName(), world.getType(), 
                world.getTimeRate(), world.getDay());
        NetworkFunctions.sendToPlayer(response, player.getID());
        
        // Send Server Announcement, if any
        getServerAnnouncement(player.getID());

        // Retrieve Ecosystem, if haven't already
        if (player.getEcosystem() == null) {
            EcosystemController.startEcosystem(player);
        }

        return true;
    }

    public static void getServerAnnouncement(int player_id) {
        String msg = NewsDAO.getLatestNews().getText();

        if (msg != null) {
            ResponseMessage response = new ResponseMessage();
            response.setMessage(""
                    + "Server Announcement" + "\n"
                    + "- - - - - - - - - - - - - - - - -" + "\n"
                    + msg + "\n"
                    + "- - - - - - - - - - - - - - - - -");
            response.setType(1);

            NetworkFunctions.sendToPlayer(response, player_id);
        }
    }

    public static void makeZoneLayout() {
//        Map<Integer, Zone> tileList = new HashMap<Integer, Zone>();
//
//        int tile_id = 1;
//        int temp = (int) (Math.sqrt(Constants.TOTAL_TILE_NUM) / 2);
//        for (int x = 1 - temp; x < temp; x++) {
//            for (int y = 1 - temp; y < temp; y++) {
//                tileList.put(tile_id, new Zone(tile_id, new Vector3<Integer>(x, y, 0 - (x + y))));
//                tile_id++;
//            }
//        }
//
//        WorldZoneDAO.setTileMap(1, tileList);
//        Log.println("Tile Saved");
        int default_terrain = 3;
        int t = (int) (Math.sqrt(1600));
        int[][] map = new int[t][t];

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                map[i][j] = default_terrain; // Default Terrain
            }
        }

        Random random = new Random();

        int[] terrains = new int[]{4, 1, 2, 3};
        for (int terrain : terrains) {
            for (int i = 0; i < 20; i++) { // # of Locations
                int x = random.nextInt(t), y = random.nextInt(t);
                if (map[x][y] != default_terrain) { // Equals Default
                    continue;
                }

                map[x][y] = terrain;

                int[] directions = new int[]{-1, 0, 1};

                for (int j = 0; j < 30; j++) { // # of Spreads
                    int x_next = x, y_next = y;

                    for (int k = 0; k < 10 + random.nextInt(5); k++) { // Radius Till Hits
                        x_next += directions[random.nextInt(3)];
                        y_next += directions[random.nextInt(3)];

                        if (x_next < 0 || x_next >= t || y_next < 0 || y_next >= t) {
                            continue;
                        }
                        map[x_next][y_next] = terrain;
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                WorldZoneDAO.updateTerrainType(i * t + j, (short) map[i][j]);
                System.out.print("[" + map[i][j] + "]");
            }
            System.out.println("");
        }
    }

    public boolean isOwned(int zone_id) {
        return WorldZoneDAO.getOwner(zone_id) > 0;
    }

    public void ownZone(int player_id, int zone_id) {
        WorldZoneDAO.updateOwner(player_id, zone_id);
    }
}
