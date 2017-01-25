package shared.model;

import shared.core.GameClient;

/**
 * The Account class holds important information about the player including, most
 * importantly, the account. Such information includes the username, password,
 * email, and the player ID.
 */
public class Account {
    
    private final int account_id;
    private String email;
    private String password;
    private String salt;
    private String username;
    private String first_name;
    private String last_name;
    private long play_time; // Total time the account has ever been active
    private long active_time;
    private String last_logout; // Last time the account has been logged in
    private String game_name; //Name of mini game playing
    private int in_game; //Whether player is playing mini game
    // Other
    private GameClient client;
    
    public Account(int account_id) {
        this.account_id = account_id;
    }
    
    public Account(int account_id, String email, String password, String salt, String username, String first_name, String last_name) {
        this.account_id = account_id;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    
    public int getID() {
        return account_id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String setEmail(String email) {
        return this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String setPassword(String password) {
        return this.password = password;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public String setSalt(String salt) {
        return this.salt = salt;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String setUsername(String username) {
        return this.username = username;
    }
    
    public String getFirstName() {
        return first_name;
    }
    
    public String setFirstName(String first_name) {
        return this.first_name = first_name;
    }
    
    public String getLastName() {
        return last_name;
    }
    
    public String setLastName(String last_name) {
        return this.last_name = last_name;
    }
    
    public long getPlayTime() {
        return play_time;
    }
    
    public long setPlayTime(long play_time) {
        return this.play_time = play_time;
    }
    
    public long getActiveTime() {
        return active_time;
    }
    
    public long setActiveTime(long active_time) {
        return this.active_time = active_time;
    }
    
    public String getLastLogout() {
        return last_logout;
    }
    
    public String setLastLogout(String last_logout) {
        return this.last_logout = last_logout;
    }
    
    public String getGameName() {
        return game_name;
    }
    
    public String setGameName(String game_name) {
        return this.game_name = game_name;
    }
    
    public int getInGame() {
        return in_game;
    }
    
    public int setInGame(int in_game) {
        return this.in_game = in_game;
    }
    
    public GameClient getClient() {
        return client;
    }
    
    public GameClient setClient(GameClient client) {
        return this.client = client;
    }
}
