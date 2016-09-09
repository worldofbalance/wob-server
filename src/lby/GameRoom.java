
package lby;

import shared.core.GameClient;
import lby.net.response.GameResponse;

/**
 *
 * @author yanxing wang
 */
public class GameRoom {
    
    private final GameClient mClients[];
    private int CAPACITY = 2;     // DH removed "final". Need to change for Multiplayer Convergence   
    // DH
    private final int MAX_CAPACITY = 5;
    
    private int mNumClients = 0;
    private int mID = 0;
    private int mGameID = 0;
    private String mHostName = "";
    // DH Used only for Multiplayer Convergence. Otherwise default of -1
    private short totalPlayers = -1;
    private short numRounds = -1;
    private short secPerRound = -1;
    private short betAmt = -1;
    private short ecoNum = -1;
    private short helps = -1;
    
    public GameRoom () {
        mClients = new GameClient[MAX_CAPACITY];
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
    
    // DH   for Multiplayer Convergence     
    public void setTotalPlayers(short totalPlayers) {
        this.totalPlayers = totalPlayers;
        this.CAPACITY = totalPlayers;
    }
    public short getTotalPlayers() {
        return totalPlayers;
    }
    
    public void setNumRounds(short numRounds) {
        this.numRounds = numRounds;
    }
    public short getNumRounds() {
        return numRounds;
    }
    
    public void setSecPerRound(short secPerRound) {
        this.secPerRound = secPerRound;
    }
    public short getSecPerRound() {
        return secPerRound;
    }
    
    public void setBetAmt(short betAmt) {
        this.betAmt = betAmt;
    }
    public short getBetAmt() {
        return betAmt;
    }
    
    public void setEcoNum(short ecoNum) {
        this.ecoNum = ecoNum;
    }
    public short getEcoNum() {
        return ecoNum;
    }

    public void setHelps(short helps) {
        this.helps = helps;
    }
    public short getHelps() {
        return helps;
    }
}