package cos.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Represents a player's attack configuration in Clash of Species, which is
 * a set of 5 species (model.clashgame.Species). Unlike DefenseConfig,
 * species positions are not stored here, because they are not specified
 * until the attacking player places them on the ground during battle. A player
 * may change their attack configuration in between battles. The previous
 * configuration is kept in the database so that battle history can be
 * displayed. The AttackConfig with the most recent creation date is used for
 * new battles.
 */
public class AttackConfig {
    public int id;
    public List<Integer> speciesIds = Arrays.asList(new Integer[5]);
    public int playerId;
    public Date createdAt;
}