package cos.model;

import java.util.Date;

/**
 * Created by Abhi on 7/3/16.
 */
public class MatchRecord {
	//integer ID of every player's individual history
	public int gameId;
	//integer ID of the player
	public int playerId;
	//integer ID of player's opponent
	// player name
	public String playerName;
	// opponent name
	public String opponentName;
	public int opponentId;
	//result of the match
	public String matchResult;
	//date that the player played the match
	public Date playedOn = new Date();
}
