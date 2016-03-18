
package lby;

import shared.core.GameClient;
import lby.net.response.GameResponse;

/**
 *
 * @author yanxing wang
 */
public class GameRoom {
    
    private final GameClient mClients[];
    private final int CAPACITY = 2;
    
    private int mNumClients = 0;
    private int mID = 0;
    private int mGameID = 0;
    private String mHostName = "";
    
    public GameRoom () {
        mClients = new GameClient[CAPACITY];
    }
    
    public int getNumClients() {
        return mNumClients;
    }
    
    public GameClient getClient(int id) {
        return mClients[id];
    }
    
    public boolean addClient(GameClient player) {
        if(mNumClients == CAPACITY) {
            return false;
        }
        if (mNumClients == 0) {
            mHostName = player.getPlayer().getName();
        }
        mClients[mNumClients++] = player;
        return true;
    }
    
    public boolean removeClient(GameClient player) {
        if (mNumClients == 0) {
            return false;
        } else if (mNumClients == 1) {
            if (mClients[0].getID().equals(player.getID())) {
                mClients[0] = null;
            } else {
                return false;
            }
        } else {
            if (mClients[0].getID().equals(player.getID())) {
                mClients[0] = mClients[1];
                mClients[1] = null;
            } else if (mClients[1].getID().equals(player.getID())) {
                mClients[1] = null;
            } else {
                return false;
            }
        }
        --mNumClients;
        return true;
    }
    
    public void sendResponse(GameResponse resp) {
        for(int i = 0; i < mNumClients; ++i) {
            mClients[i].add(resp);
        }
    }
    
    public boolean isFull() {
        return mNumClients == CAPACITY;
    }
    
    public boolean isEmpty() {
        return mNumClients == 0;
    }
    
    public void setID(int id) {
        this.mID = id;
    }
    
    public int getID() {
        return this.mID;
    }

    /**
     * @return the mGameID
     */
    public int getGameID() {
        return mGameID;
    }
    
    public String getHost() {
        return mHostName;
    }

    /**
     * @param id the mGameID to set
     */
    public void setGameID(int id) {
        this.mGameID = id;
    }
}