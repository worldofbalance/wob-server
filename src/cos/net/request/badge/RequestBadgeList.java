package cos.net.request.badge;

// Java Imports

import cos.core.badge.Badge;
import cos.core.badge.BadgeController;
import cos.db.badge.BadgeDAO;
import cos.net.request.GameRequest;
import cos.net.response.badge.ResponseBadgeList;
import cos.util.DataReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

// Other Imports

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
