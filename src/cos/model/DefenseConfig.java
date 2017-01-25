package cos.model;

import cos.util.Vector2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * The DefenseConfig class holds all the pertinent information of defense
 * setup variables in the database.
*/

public class DefenseConfig {
    //integer ID of every player's individual defense configuration
    public int id;
    //integer ID of the player
    public int playerId;
    //integer ID of player's chosen terrain
    public String terrain;
    //stores the ID of each species and it's position on the terrain
    public HashMap<Integer, ArrayList<Vector2<Float>>> layout = new HashMap<Integer, ArrayList<Vector2<Float>>>();
    //date that the player created or modified their defense
    public Date createdAt = new Date();
}
