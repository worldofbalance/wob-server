package lby.net.request.badge;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

// Other Imports
import lby.core.badge.Badge;
import lby.core.badge.BadgeController;
import lby.db.badge.BadgeDAO;
import lby.net.request.GameRequest;
import lby.net.response.badge.ResponseBadgeList;
import shared.util.DataReader;

public class RequestBadgeList extends GameRequest {

    private int user_id;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        user_id = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        BadgeController badgeManager = new BadgeController(user_id, null);
        badgeManager.initialize(BadgeDAO.getBadgeData(user_id));

        ResponseBadgeList response = new ResponseBadgeList();
        response.setBadgeList(new ArrayList<Badge>(badgeManager.getBadgeList().values()));
        client.add(response);
    }
}
