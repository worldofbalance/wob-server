package cos.model;

import java.util.Date;

/**
 * A record of a battle between two players. The attacking player is the one
 * associated with the AttackConfig identified by attackConfigId, and the
 * defending player is the one associated with the DefenseConfig identified
 * by defenseConfigId.
 */
public class Battle {

    /**
     * The outcome of the battle from the perspective of the attacking player.
     */
    public static enum Outcome {

        WIN(0),
        LOSE(1),
        DRAW(2);

        private final int value;

        private Outcome(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public Integer id;
    public Integer attackConfigId;
    public Integer defenseConfigId;
    public Date timeStarted;
    public Date timeEnded;
    public Outcome outcome;
}
