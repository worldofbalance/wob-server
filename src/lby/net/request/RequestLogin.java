package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.core.GameServer;
import shared.db.AccountDAO;
import shared.db.PlayerDAO;
import shared.model.Player;
import shared.model.Account;
import lby.net.response.ResponseLogin;
import shared.util.DataReader;
import shared.util.Log;

/**
 * The RequestLogin class authenticates the user information to log in. Other
 * tasks as part of the login process lies here as well.
 */
public class RequestLogin extends GameRequest {

    private String user_id;
    private String password;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        user_id = DataReader.readString(dataInput).trim();
        password = DataReader.readString(dataInput).trim();

        if (user_id.isEmpty() || password.isEmpty()) {
            throw new IOException();
        }
    }

    @Override
    public void process() throws Exception {
        Log.printf("User '%s' is connecting...", user_id);
        ResponseLogin response = new ResponseLogin();

        if (client.getAccount() == null) {
            Account account = AccountDAO.getAccount(user_id, password);

            if (account != null) {
                // If account is already in use, remove and disconnect the client
                if (GameServer.getInstance().hasAccount(account.getID())) {
                    account = GameServer.getInstance().getActiveAccount(account.getID());
                    account.getClient().logout();

                    response.setStatus(ResponseLogin.IN_USE);
                    Log.printf("User '%s' account was already in use.  User has now been logged out.", user_id);
                } else {
                    Player player = PlayerDAO.getPlayerByAccount(account.getID());

                    if (player != null) {
                        client.login(account);

                        response.setStatus(ResponseLogin.SUCCESS);
                        response.setAccount(account.getID(), account.getUsername(), account.getLastLogout());
                        Log.printf("User '%s' has successfully logged in.", account.getUsername());
                    } else {
                        Log.println_e("Player not found");
                    }
                }
            } else {
                response.setStatus(ResponseLogin.FAILED);
                Log.printf("User '%s' not found. Login attempt failed.", user_id);
            }
        } else {
            response.setStatus(ResponseLogin.LOGGED_IN);
        }

        client.add(response);
    }
}
