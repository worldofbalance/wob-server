package cos.db;

import cos.model.Leaderboard;
import cos.model.Player;
import cos.util.Log;

import java.sql.*;
import java.util.*;

/**
 * Created by Abhi on 7/10/16.
 */
public class LeaderboardDAO {


	private static final String FIND_MATCH_RESULT_BY_PLAYERID_QUERY = "SELECT `player_id`, `opponent_id`, count(`match_result`) AS `result_count` FROM `clash_player_history`"
		+ " WHERE `match_result` = ?"
		+ " GROUP BY `player_id`, `opponent_id` "
		+ " ORDER BY count(`match_result`) DESC";

	private static final String FIND_MATCH_RESULT_BY_OPPONENTID_QUERY = "SELECT `opponent_id`, count(`match_result`) AS `result_count` FROM `clash_player_history`"
		+ " WHERE `match_result` = ?"
		+ " GROUP BY `opponent_id`, `match_result` "
		+ " ORDER BY count(`match_result`) DESC";

	private LeaderboardDAO() {}


	public static List<Leaderboard> findMatchesByMatchResult() {
		List<Leaderboard> leaderboards = new ArrayList<>();
		Player player;
		Player opponent;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			con = GameDB.getConnection();
			pstmt = con.prepareStatement(FIND_MATCH_RESULT_BY_PLAYERID_QUERY);
			pstmt.setString(1, "WIN");

			rs = pstmt.executeQuery();
			HashMap<Integer, Integer> playerWins = new HashMap<>();
			HashMap<Integer, Integer> playerLoses = new HashMap<>();

			while (rs.next()) {
				if (playerWins.containsKey(rs.getInt("player_id"))){
					playerWins.put(rs.getInt("player_id"), playerWins.get(rs.getInt("player_id")) + rs.getInt("result_count"));
				}else {
					playerWins.put(rs.getInt("player_id"), rs.getInt("result_count"));
				}
				if (playerLoses.containsKey(rs.getInt("opponent_id"))){
					playerLoses.put(rs.getInt("opponent_id"), playerLoses.get(rs.getInt("opponent_id")) + rs.getInt("result_count"));
				}else {
					playerLoses.put(rs.getInt("opponent_id"), rs.getInt("result_count"));
				}
			}

			pstmt = con.prepareStatement(FIND_MATCH_RESULT_BY_PLAYERID_QUERY);
			pstmt.setString(1, "LOSE");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (playerWins.containsKey(rs.getInt("opponent_id"))) {
					playerWins.put(rs.getInt("opponent_id"), playerWins.get(rs.getInt("opponent_id")) + rs.getInt("result_count"));
				}else {
					playerWins.put(rs.getInt("opponent_id"), rs.getInt("result_count"));
				}

				if (playerLoses.containsKey(rs.getInt("player_id"))) {
					playerLoses.put(rs.getInt("player_id"), playerLoses.get(rs.getInt("player_id")) + rs.getInt("result_count"));
				}else {
					playerLoses.put(rs.getInt("player_id"), rs.getInt("result_count"));
				}
			}


			for (Map.Entry<Integer, Integer> entry : playerWins.entrySet()) {
				Leaderboard leaderboard = new Leaderboard();
				Integer key = entry.getKey();
				Integer value = entry.getValue();
				player = ClashPlayerDAO.findById(key);
				leaderboard.playerName = player.name;
				leaderboard.wins = value;
				if (playerLoses.containsKey(key)){
				leaderboard.loses = playerLoses.get(key);
					leaderboards.add(leaderboard);
				}else {
					leaderboard.loses = 0;
					leaderboards.add(leaderboard);
				}
			}


			for (Map.Entry<Integer, Integer> entry : playerLoses.entrySet()) {
				Leaderboard leaderboard = new Leaderboard();
				Integer key = entry.getKey();
				Integer value = entry.getValue();
				if (!playerWins.containsKey(key)){
					player = ClashPlayerDAO.findById(key);
					leaderboard.playerName = player.name;
					leaderboard.loses = value;
					leaderboard.wins = 0;
					leaderboards.add(leaderboard);
				}
			}
		} catch (SQLException ex) {
			Log.println_e(ex.getMessage());
		} finally {
			GameDB.closeConnection(con, pstmt, rs);
		}
		Collections.sort(leaderboards, new Comparator<Leaderboard>() {
			@Override
			public int compare(Leaderboard wins2, Leaderboard wins1)
			{

				return  wins1.wins.compareTo(wins2.wins);
			}
		});
		return leaderboards;
	}
}
