package cos.db;

import cos.model.MatchRecord;
import cos.model.Player;
import cos.util.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhi on 7/3/16.
 */
public class MatchRecordDAO {

	private static final String INSERT_QUERY = "INSERT INTO `clash_player_history`"
		+ " (`game_id`, `player_id`, `opponent_id`, `match_result`, `played_on`) "
		+ "VALUES (?, ?, ?, ?, ?)";


	private static final String FIND_BY_PLAYER_QUERY = "SELECT * FROM `clash_player_history`"
		+ " WHERE `player_id` = ?"
		+ " ORDER BY `played_on` DESC";

	private static final String FIND_BY_OPPONENT_PLAYER_QUERY = "SELECT * FROM `clash_player_history`"
		+ " WHERE `opponent_id` = ?"
		+ " ORDER BY `played_on` DESC";

	private static final String FIND_BY_OPPONENT_LAST_LOGOUT = "SELECT * FROM `clash_player_history`"
		+ " WHERE `opponent_id` = ? AND `played_on` > ?"
		+ " ORDER BY `played_on` DESC";

	private MatchRecordDAO() {}

	public static MatchRecord create(MatchRecord matchRecord) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = GameDB.getConnection();
			pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, matchRecord.gameId);
			pstmt.setInt(2, matchRecord.playerId);
			pstmt.setInt(3, matchRecord.opponentId);
			pstmt.setString(4, matchRecord.matchResult);
			pstmt.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				matchRecord.gameId = rs.getInt(1);
			} else {
				throw new SQLException("Failed to store game result.");
			}

		} catch (SQLException ex) {
			Log.println_e(ex.getMessage());
		} finally {
			GameDB.closeConnection(con, pstmt, rs);
		}
		return matchRecord;
	}

	public static List<MatchRecord> findMatchesByLastLogout(int player_id, String last_log_out) {
		List<MatchRecord> matchList = new ArrayList<MatchRecord>();
		Player player;
		Player opponent;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			con = GameDB.getConnection();
			pstmt = con.prepareStatement(FIND_BY_OPPONENT_LAST_LOGOUT);
			pstmt.setInt(1, player_id);
			pstmt.setString(2, last_log_out);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MatchRecord matchRecord = new MatchRecord();
				player = ClashPlayerDAO.findById(rs.getInt("opponent_id"));
				opponent = ClashPlayerDAO.findById(rs.getInt("player_id"));
				Timestamp ts = rs.getTimestamp("played_on");
				matchRecord.playedOn = new java.util.Date(ts.getTime());
				matchRecord.playerId = rs.getInt("opponent_id");
				matchRecord.playerName = player.name;
				matchRecord.opponentName = opponent.name;
				matchRecord.opponentId = rs.getInt("player_id");
				matchRecord.matchResult = rs.getString("match_result");
				matchList.add(matchRecord);
			}
		} catch (SQLException ex) {
			Log.println_e(ex.getMessage());
		} finally {
			GameDB.closeConnection(con, pstmt, rs);
		}
		return matchList;
	}

	public static List<MatchRecord> findMatchesByOpponentId(int opponentId) {
		List<MatchRecord> matchList = new ArrayList<MatchRecord>();
		Player player;
		Player opponent;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			con = GameDB.getConnection();
			pstmt = con.prepareStatement(FIND_BY_OPPONENT_PLAYER_QUERY);
			pstmt.setInt(1, opponentId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MatchRecord matchRecord = new MatchRecord();
				player = ClashPlayerDAO.findById(rs.getInt("opponent_id"));
				opponent = ClashPlayerDAO.findById(rs.getInt("player_id"));
				Timestamp ts = rs.getTimestamp("played_on");
				matchRecord.playedOn = new java.util.Date(ts.getTime());
				matchRecord.playerId = rs.getInt("opponent_id");
				matchRecord.playerName = player.name;
				matchRecord.opponentName = opponent.name;
				matchRecord.opponentId = rs.getInt("player_id");
				matchRecord.matchResult = rs.getString("match_result");
				matchList.add(matchRecord);
			}
		} catch (SQLException ex) {
			Log.println_e(ex.getMessage());
		} finally {
			GameDB.closeConnection(con, pstmt, rs);
		}
		return matchList;
	}

	public static List<MatchRecord> findMatchesByPlayerId(int playerId) {
		List<MatchRecord> matchList = new ArrayList<MatchRecord>();
		Player player;
		Player opponent;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			con = GameDB.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PLAYER_QUERY);
			pstmt.setInt(1, playerId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MatchRecord matchRecord = new MatchRecord();
				player = ClashPlayerDAO.findById(rs.getInt("player_id"));
				opponent = ClashPlayerDAO.findById(rs.getInt("opponent_id"));
				Timestamp ts = rs.getTimestamp("played_on");
				matchRecord.playedOn = new java.util.Date(ts.getTime());
				matchRecord.playerId = rs.getInt("player_id");
				matchRecord.playerName = player.name;
				matchRecord.opponentName = opponent.name;
				matchRecord.opponentId = rs.getInt("opponent_id");
				matchRecord.matchResult = rs.getString("match_result");
				matchList.add(matchRecord);
			}
		} catch (SQLException ex) {
			Log.println_e(ex.getMessage());
		} finally {
			GameDB.closeConnection(con, pstmt, rs);
		}
		return matchList;
	}
}
