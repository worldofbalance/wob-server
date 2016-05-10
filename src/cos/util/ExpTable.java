package cos.util;

// Java Imports

import cos.metadata.Constants;

import java.util.ArrayList;
import java.util.List;

// Other Imports

/**
 * The ExpTable class calculates the amount of experience that should be
 * accumulated at every level. The amount needed to advance from one level to
 * the next is simply the result of experience at the current level subtracted
 * from the amount at the next.
 */
public abstract class ExpTable {

    private static List<Integer> expTable; // Stores amount of experience at each level (index)

    /**
     * Initializes the experience table by using the given formula and fill in
     * the amount of experience that should be accumulated at every level.
     */
    public static void init() {
        Log.console("Loading Experience Table...");

        expTable = new ArrayList<Integer>(Constants.MAX_LEVEL + 1);
        expTable.add(0);

        for (int i = 1; i <= Constants.MAX_LEVEL; i++) {
            expTable.add(expTable.get(i - 1) + (int) (Constants.STARTING_NEEDED_EXP * Math.pow(1.25f, (i - 1))));
        }

        Log.println("Done!");
    }

    /**
     * Retrieve the amount of experience at any given level.
     * 
     * @param level contains the level of the player
     * @return amount of accumulated experience at the level
     */
    public static int getExp(int level) {
        return expTable.get(level);
    }

    /**
     * Calculates the amount of experience required to advance from one to the
     * next by taking the difference of two levels.
     * 
     * @param level contains the level of the player
     * @return amount of experience in between two levels
     */
    public static int getExpToAdvance(int level) {
        return expTable.get(level) - expTable.get(level - 1);
    }

    /**
     * Using the experience value, it will determine what is its equivalent
     * level at the given amount of experience.
     * 
     * @param experience is used to calculate its equivalent level
     * @return the level determined by the amount of experience
     */
    public static short getLevel(int experience) {
        short level = 0;

        for (int i = 0; i < expTable.size(); i++) {
            if (experience >= expTable.get(i)) {
                level++;
            }
        }

        return level;
    }
}
