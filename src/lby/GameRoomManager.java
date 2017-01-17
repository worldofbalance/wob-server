
package lby;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import shared.core.GameClient;
import java.util.Iterator;
import shared.util.Log;

/**
 *
 * @author yanxingwang
 */
public class GameRoomManager {
    
    private static GameRoomManager sInstance = null;
    
    // key is room id
    private final Map<Integer, GameRoom> mRooms = new HashMap<Integer, GameRoom>();
    
    // key is session_id (or client id)
    private final Map<String, GameRoom> mRoomTable = new HashMap<String, GameRoom>();
    
    private int mRoomIDCount = 100;
    
    public GameRoomManager() {
    }
    
    public static GameRoomManager getInstance() {
        if (sInstance == null) {
            sInstance = new GameRoomManager();
        } 
        return sInstance;
    }
    
    public Iterator<GameRoom> getRoomsIter() {
        return mRooms.values().iterator();
    }
    
    public int getNumRooms() {
        return mRooms.size();
    }
    
    public GameRoom createRoomWithClient(GameClient client) {
        GameRoom room = new GameRoom();
        room.setID(++mRoomIDCount);
        room.addClient(client);
        mRooms.put(room.getID(), room);
        mRoomTable.put(client.getID(), room);
        
        Log.println("GameRoomManager: New room created with ID: " + Integer.toString(room.getID()));
        Log.println("Player id, Number of rooms: " + client.getPlayerID() + " " + Integer.toString(mRooms.size()));
        return room;
    }
    
    public GameRoom pairClient(GameClient client, int roomID) {
        if (!mRooms.containsKey(roomID)) {
            Log.println("Can't join room with ID: " + Integer.toString(roomID));
            return null;
        }
        
        GameRoom r = mRooms.get(roomID);
        r.addClient(client);
        mRoomTable.put(client.getID(), r);
        
        Log.println("Joined room: " + Integer.toString(r.getID()));
        return r;
    }
    
    public void clientQuit(GameClient client) {
        if (mRoomTable.containsKey(client.getID())) {
            GameRoom room = mRoomTable.get(client.getID());
            room.removeClient(client);
            Log.println("Removed a client in room " + Integer.toString(room.getID()));
            mRoomTable.remove(client.getID());
            
            if (room.isEmpty()) {
                mRooms.remove(room.getID());
                Log.println("This room is now empty, remove it!");
            }
        }
        Log.println("Number of rooms: " + Integer.toString(mRooms.size()));
    }
    
    public GameRoom getRoom(String id) {
        return mRoomTable.get(id);
    }

    public void onClientQuit(GameClient client) {
        if (!mRoomTable.containsKey(client.getID())) {
            return;
        }
        
        Log.println("Game room is closing...");
        GameRoom room = mRoomTable.get(client.getID());
        mRooms.remove(room.getID());
        mRoomTable.remove(client.getID());
    }
}
