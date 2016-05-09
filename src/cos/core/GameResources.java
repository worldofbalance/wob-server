package cos.core;

// Other Imports
import cos.db.PlayerDAO;
import cos.metadata.Constants;
import cos.model.Player;
import cos.net.response.ResponseUpdateLevel;
import cos.net.response.ResponseUpdateResources;
import cos.util.ExpTable;
import cos.util.NetworkFunctions;

public class GameResources {

    private GameResources() {
    }

    public static boolean useCredits(Player player, int amount) {
        if (amount <= player.getCredits()) {
            player.setCredits(player.getCredits() - amount);
            GameResources.updateCredits(player, 0);
            return true;
        }

        return false;
    }

    /**
     * Adjusts a player's money by the given amount.
     *
     * @param player references instance of the player
     * @param amount contains the amount of money given
     */
    public static void updateCredits(Player player, int amount) {
        player.setCredits(Math.min(Constants.MAX_CREDITS, player.getCredits() + amount));

        PlayerDAO.updateCredits(player.getID(), player.getCredits());
        NetworkFunctions.sendToPlayer(
                new ResponseUpdateResources(
                        Constants.RESOURCE_COINS,
                        amount,
                        player.getCredits()
                ), player.getID()
        );
    }

    /**
     * Adjusts a player's experience by the given amount. If the experience
     * reaches a certain amount, their level is adjusted as well.
     *
     * @param player references instance of the player
     * @param amount contains the amount of experience given
     */
    public static void updateExperience(Player player, int amount) {
        amount *= Constants.MULTIPLIER_EXP;

        player.setExperience(Math.min(ExpTable.getExp(Constants.MAX_LEVEL - 1), player.getExperience() + amount));

        PlayerDAO.updateExperience(player.getID(), player.getExperience());

        short oldLevel = player.getLevel(), newLevel = ExpTable.getLevel(player.getExperience());

        if (newLevel > oldLevel) {
            player.setLevel(newLevel);

            PlayerDAO.updateLevel(player.getID(), player.getLevel());

            ResponseUpdateLevel response = new ResponseUpdateLevel();
            response.setAmount(newLevel - oldLevel);
            response.setLevel(newLevel);

            String range = String.valueOf(ExpTable.getExpToAdvance(oldLevel + 1));
            for (int i = oldLevel + 2; i <= newLevel; i++) {
                range += "," + ExpTable.getExpToAdvance(i);
            }
            response.setRange(range);

            NetworkFunctions.sendToPlayer(response, player.getID());

            updateCredits(player, amount);
        }

        NetworkFunctions.sendToPlayer(
                new ResponseUpdateResources(
                        Constants.RESOURCE_XP,
                        amount,
                        player.getExperience()
                ), player.getID()
        );
    }
}
