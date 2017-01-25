package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.db.AccountDAO;
import shared.db.PlayerDAO;
import java.util.Random;
import shared.metadata.Constants;
import shared.model.Account;
import lby.net.response.ResponseRegister;
import shared.util.Color;
import shared.util.DataReader;

/**
 * The RequestRegister class handles the registration process to create new
 * accounts for users.
 */
public class RequestRegister extends GameRequest {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String display_name;
    private Color color;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        first_name = DataReader.readString(dataInput).trim();
        last_name = DataReader.readString(dataInput).trim();
        email = DataReader.readString(dataInput).trim();
        password = DataReader.readString(dataInput).trim();
        display_name = DataReader.readString(dataInput).trim();

//        int r = DataReader.readInt(dataInput);
//        int g = DataReader.readInt(dataInput);
//        int b = DataReader.readInt(dataInput);
        Random random = new Random(System.currentTimeMillis());
        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

        if (first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || email.split("@").length != 2 || password.isEmpty() || display_name.isEmpty()) {
            throw new IOException();
        }
    }

    @Override
    public void process() throws Exception {
        ResponseRegister response = new ResponseRegister();

        if (AccountDAO.containsEmail(email)) {
            response.setStatus(ResponseRegister.EMAIL_IN_USE);
        } else if (AccountDAO.containsUsername(display_name)) {
            response.setStatus(ResponseRegister.HANDLE_IN_USE);
        } else {
            Account account = AccountDAO.createAccount(email, password, display_name, first_name, last_name, client.getIP());
            PlayerDAO.createPlayer(account.getID(), display_name, Constants.INITIAL_CREDITS, color);

            response.setStatus(ResponseRegister.SUCCESS);
        }

        client.add(response);
    }
}
