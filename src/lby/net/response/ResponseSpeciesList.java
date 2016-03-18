package lby.net.response;

// Java Imports
import java.util.List;

// Other Imports
import shared.metadata.NetworkCode;
import shared.model.ShopItem;
import shared.util.GamePacket;

public class ResponseSpeciesList extends GameResponse {

    private List<ShopItem> speciesList;

    public ResponseSpeciesList() {
        response_id = NetworkCode.SPECIES_LIST;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16((short) speciesList.size());

        for (ShopItem species : speciesList) {
            packet.addInt32(species.getID());
            packet.addShort16((short) species.getLevel());
            packet.addString(species.getName());
            packet.addString(species.getDescription());

            packet.addShort16((short) species.getExtraArgs().size());
            for (String s : species.getExtraArgs()) {
                packet.addString(s);
            }

            packet.addString(species.getCategoryListAsString());
        }

        return packet.getBytes();
    }

    public void setSpeciesList(List<ShopItem> speciesList) {
        this.speciesList = speciesList;
    }
}
