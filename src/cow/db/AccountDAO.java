/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cow.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cow.model.Account;
import shared.db.GameDB;
import shared.util.Functions;
import shared.util.Log;

public final class AccountDAO {

    private AccountDAO() {
    }

    /**
     * Creates an account profile using the information as provided to the
     * method into the database.
     *
     * @param email contains the email used for the account
     * @param password contains the MD5-hashed password
     * @param username is the display name describing the user
     * @param first_name is the user's first name
     * @param last_name is the user's last name
     * @param last_ip contains the location by IP used to create the account
     * @return the player ID generated from the database
     */
    public static Account createAccount(String email, String password, String username, String first_name, String last_name, String last_ip) {
        Account account = null;

        String query = "INSERT INTO `account` (`email`, `password`, `salt`, `username`, `first_name`, `last_name`, `last_ip`) VALUES (?, MD5(CONCAT(?, ?)), ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            String salt = Functions.getMD5(Long.toString(System.currentTimeMillis()).getBytes());
            pstmt.setString(3, salt);
            pstmt.setString(4, salt);

            pstmt.setString(5, username);
            pstmt.setString(6, first_name);
            pstmt.setString(7, last_name);
            pstmt.setString(8, last_ip);
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                int account_id = rs.getInt(1);
                account = new Account(account_id, email, password, salt, username, first_name, last_name);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return account;
    }

    public static Account getAccount(int accountid) {
        Account account = null;

        String query = "SELECT * FROM `account` WHERE (`account_id` = ?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, accountid);
            

            rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new Account(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setPlayTime(rs.getLong("play_time"));
                account.setActiveTime(rs.getLong("active_time"));
                account.setLastLogout(rs.getString("last_logout"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return account;
    }

    public static Account getAccountByPlayer(int playerId) {
        Account account = null;

        String query = "SELECT * FROM `account` LEFT JOIN `player` ON `account`.`account_id`=`player`.`account_id` WHERE (`player_id` = ?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, playerId);


            rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new Account(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setPlayTime(rs.getLong("play_time"));
                account.setActiveTime(rs.getLong("active_time"));
                account.setLastLogout(rs.getString("last_logout"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return account;
    }
    /**
     * Retrieve account information from the database using both email and
     * password.
     *
     * @param user_id is used in combination with password to identify the
     * account
     * @param password is used in combination with user_id to identify the
     * account
     * @return a Player instance holding player information
     */
    public static Account getAccount(String user_id, String password) {
        Account account = null;

        String query = "SELECT * FROM `account` WHERE (`email` = ? OR `username` = ?) AND `password` = MD5(CONCAT(?, `salt`))";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, user_id);
            pstmt.setString(2, user_id);
            pstmt.setString(3, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new Account(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setPlayTime(rs.getLong("play_time"));
                account.setActiveTime(rs.getLong("active_time"));
                account.setLastLogout(rs.getString("last_logout"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return account;
    }

    /**
     * Confirms if the email already exists in the "player" table.
     *
     * @param email is a string containing the email
     * @return true if the email already exists
     */
    public static boolean containsEmail(String email) {
        boolean status = false;

        String query = "SELECT * FROM `account` WHERE `email` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);

            rs = pstmt.executeQuery();
            status = rs.next();
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return status;
    }

    /**
     * Confirms if the username already exists in the "player" table.
     *
     * @param username is a string containing the username
     * @return true if the username already exists
     */
    public static boolean containsUsername(String username) {
        boolean status = false;

        String query = "SELECT * FROM `account` WHERE `username` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);

            rs = pstmt.executeQuery();
            status = rs.next();
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return status;
    }

    /**
     * Updates the database by marking the account active and recording at which
     * time, including location, the account was accessed.
     *
     * @param account_id used to identify the specific account
     * @param address holds the IP address in current use
     * @return
     */
    public static boolean updateLogin(int account_id, String address) {
        boolean status = false;

        String query = "UPDATE `account` SET `online` = 1, `last_login` = NOW(), `last_ip` = ?  WHERE `account_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, address);
            pstmt.setInt(2, account_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    /**
     * Updates the database by marking the account inactive, recording the total
     * time the account has been in use, and the time when the user logged off.
     *
     * @param account_id used to identify the specific account
     * @return
     */
    public static boolean updateLogout(int account_id) {
        boolean status = false;

        String query = "UPDATE `account` SET `online` = 0, `last_logout` = NOW() WHERE `account_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, account_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    /**
     * Updates the database by recording the total time the account has been in
     * use.
     *
     * @param account_id used to identify the specific account
     * @param play_time holds the total time the account has been logged in
     * @param active_time holds the total time being active
     * @return
     */
    public static boolean updatePlayTime(int account_id, long play_time, long active_time) {
        boolean status = false;

        String query = "UPDATE `account` SET `play_time` = ?, `active_time` = ? WHERE `account_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, play_time);
            pstmt.setLong(2, active_time);
            pstmt.setInt(3, account_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }
}
