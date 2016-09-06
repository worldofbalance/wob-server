package cos.model;

// Other Imports

import cos.core.GameClient;
import cos.core.lobby.Lobby;
import cos.core.world.World;
import cos.util.Color;

public class Player {

    public int player_id;
    private int account_id;
    public String name;
    public short level = 1;
    private int experience;
    private int credits;
    private Color color;
    private String last_played;
    // Other
    private GameClient client;
    private World world;
    private Ecosystem ecosystem;
    private Lobby lobby;
    
    private CardDeck deck = null;
    public Player(){}
    public Player(int player_id) {
        this.player_id = player_id;
    }

    public Player(int player_id, int account_id, String name, int credits, Color color) {
        this.player_id = player_id;
        this.account_id = account_id;
        this.name = name;
        this.credits = credits;
        this.color = color;
    }

    public int getID() {
        return player_id;
    }

    public int getAccountID() {
        return account_id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public short getLevel() {
        return level;
    }

    public short setLevel(short level) {
        return this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public int setExperience(int experience) {
        return this.experience = experience;
    }

    public int getCredits() {
        return credits;
    }

    public int setCredits(int credits) {
        return this.credits = credits;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getLastPlayed() {
        return last_played;
    }

    public String setLastPlayed(String last_played) {
        return this.last_played = last_played;
    }

    public GameClient getClient() {
        return client;
    }

    public GameClient setClient(GameClient client) {
        return this.client = client;
    }

    public World getWorld() {
        return world;
    }

    public World setWorld(World world) {
        return this.world = world;
    }

    public Ecosystem getEcosystem() {
        return ecosystem;
    }

    public Ecosystem setEcosystem(Ecosystem ecosystem) {
        return this.ecosystem = ecosystem;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public Lobby setLobby(Lobby lobby) {
        return this.lobby = lobby;
    }
    
    public CardDeck getPlayerDeck() {
		return this.deck;
	}
	
	public void givePlayerADeck(CardDeck deck) {
		this.deck = deck;
	}
}
