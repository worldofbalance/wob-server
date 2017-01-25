package shared.db;

//java imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.util.Log;

import shared.model.Tile;

/**
 * The TileDAO class hold methods that can execute a variety of different
 * queries for very specific purposes. For use with queries utilizing the "tile"
 * table.
 */

public final class TileDAO {

	private TileDAO() {

	}

	/**
	 * 
	 * @return a list of tiles in the database
	 * @throws SQLException
	 */
	public static List<Tile> getTileList() throws SQLException {

		List<Tile> tileList = new ArrayList<Tile>();
		String query = "SELECT * FROM tile ORDER BY tile_id ASC ";
		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;
		try {
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			int tile_null = -1;
			Tile tile = null;

			while (rs.next()) {
				int tile_id = rs.getInt("tile_id");
				// Log.println("tile id is " + tile_id);
				if (tile_id != tile_null) {
					// tile_id = rs.getInt(id_temp);
					tile = new Tile(tile_id);
					tile.setOwner(rs.getInt("tile_owner"));
					tile.setVegetationCapacity(rs.getInt("vegetation_capacity"));
					tile.setTerrainType(rs.getInt("terrain_type"));
					tile.setXPosition(rs.getInt("x_position"));
					tile.setYPosition(rs.getInt("y_position"));
					tile.setZPosition(rs.getInt("z_position"));
					tileList.add(tile);
				}
			}
			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return tileList;
	}

	/**
	 * 
	 * @return a hashmap of tiles in the database
	 * @throws SQLException
	 */
	public static Map<Integer, Tile> getTileMap() throws SQLException {
		Map<Integer, Tile> mapList = new HashMap<Integer, Tile>();

		String query = "SELECT * FROM tile ORDER BY tile_id ASC ";

		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;

		try {
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();

			int tile_id = -1;
			Tile tile = null;

			while (rs.next()) {
				if (rs.getInt("tile_id") != tile_id) {
					tile_id = rs.getInt("tile_id");
					tile = new Tile(tile_id);
					tile.setOwner(rs.getInt("tile_owner"));
					tile.setVegetationCapacity(rs.getInt("vegetation_capacity"));
					tile.setTerrainType(rs.getInt("terrain_type"));
					tile.setXPosition(rs.getInt("x_position"));
					tile.setYPosition(rs.getInt("y_position"));
					tile.setZPosition(rs.getInt("z_position"));
					mapList.put(tile_id, tile);
				}
			}

			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return mapList;
	}

	/**
	 * 
	 * @param tile_id
	 *            that is associated with the specific tile to be retrieved
	 * @return tile object
	 * @throws SQLException
	 */
	public static Tile getTile(int tile_id) throws SQLException {
		Tile tile = null;
		String query = "SELECT * FROM tile WHERE tile_id = ?";

		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;

		try {
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, tile_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				tile_id = rs.getInt("tile_id");
				tile = new Tile(tile_id);
				tile.setOwner(rs.getInt("tile_owner"));
				tile.setVegetationCapacity(rs.getInt("vegetation_capacity"));
				tile.setTerrainType(rs.getInt("terrain_type"));
				tile.setXPosition(rs.getInt("x_position"));
				tile.setYPosition(rs.getInt("y_position"));
				tile.setZPosition(rs.getInt("z_position"));
			}

			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return tile;
	}

	/**
	 * 
	 * @param tile_owner
	 *            is use to update tile with a new owner
	 * @param tile_id
	 *            that is to be updated
	 * @throws SQLException
	 */
	public static void updateTileOwner(int tile_owner, int tile_id)
			throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE tile SET tile_owner = ? WHERE tile_id = ?";

			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, tile_owner);
			pstmt.setInt(2, tile_id);
			pstmt.execute();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * 
	 * @param vegetation_capacity
	 *            used to update the vegetation_capacity of the specific tile
	 * @param tile_id
	 *            that is to be updated
	 * @throws SQLException
	 */
	public static void updateVegetationCapacity(int vegetation_capacity,
			int tile_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE tile SET vegetation_capacity = ? WHERE tile_id = ?";

			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, vegetation_capacity);
			pstmt.setInt(2, tile_id);
			pstmt.execute();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static int numberOfTilesOwned(int tile_owner) throws SQLException {
		int numTiles = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;

		try {
			String query = "SELECT COUNT(tile_owner) AS total FROM tile WHERE tile_owner = ?";
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, tile_owner);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				numTiles = rs.getInt("total");
			}
			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return numTiles;
	}

	public static void setTileMap(Map<Integer, Tile> tileMap)
			throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		connection = GameDB.getConnection();

		try {
			for (int i = 1; i < tileMap.size() + 1; i++) {
				String query = "INSERT INTO tile VALUES (?,?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, tileMap.get(i).getTileId());
				Log.println(tileMap.get(i).getTileId() + "");
				pstmt.setNull(2, Types.NULL);
				// pstmt.setInt(2,0);
				Log.println(tileMap.get(i).getOwner() + "");
				pstmt.setInt(3, tileMap.get(i).getVegetationCapacity());
				pstmt.setInt(4, tileMap.get(i).getTerrainType());
				pstmt.setInt(5, tileMap.get(i).getXPosition());
				pstmt.setInt(6, tileMap.get(i).getYPosition());
				pstmt.setInt(7, tileMap.get(i).getZPosition());
				pstmt.execute();

			}
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static String getTileUsername(int tile_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;
		String username = "";

		try {
			String query = "SELECT player.username AS username FROM player JOIN tile ON player.player_id=tile.tile_owner WHERE tile.tile_id = ?";
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, tile_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				username = rs.getString("username");
			}
			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return username;
	}

	public static int getTileTerrainType(int tile_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;
		int terrain_type = -1;

		try {
			String query = "SELECT terrain_type FROM tile WHERE tile_id = ?";
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, tile_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				terrain_type = rs.getInt("terrain_type");
			}
			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return terrain_type;
	}

	public static void updateNaturalEvent(int event_id, int tile_id)
			throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE tile SET event_id = ? WHERE tile_id = ?";
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, event_id);
			pstmt.setInt(2, tile_id);
			pstmt.execute();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static void removeAllEventsByEventID(int event_id)
			throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE tile SET event_id = 0 WHERE event_id = ?";
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, event_id);
			pstmt.execute();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static void removeAllEventsByOwnerID(int tile_owner)
			throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE tile SET event_id = 0 WHERE tile_owner = ?";
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, tile_owner);
			pstmt.execute();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static List<Integer> getTileIDByEventID(int event_id)
			throws SQLException {
		List<Integer> tileIDList = new ArrayList<Integer>();

		String query = "SELECT tile_id FROM tile where event_id = ?";
		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;
		try {
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, event_id);
			rs = pstmt.executeQuery();
			Integer id = null;
			while (rs.next()) {
				id = rs.getInt("tile_id");
				tileIDList.add(id);
			}
			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return tileIDList;
	}
	
	public static void setTerrainType() throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		connection = GameDB.getConnection();
		int size = TileDAO.getTileList().size();
		try {
			for (int i = 1; i < size + 1; i++) {
				String query = "UPDATE tile SET terrain_type = ? WHERE tile_id = ?";
				int rand = 1 + (int)(Math.random() * ((4 - 1) + 1));
				pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, rand);
				pstmt.setInt(2, i);
				pstmt.execute();

			}
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static int getTileOwner(int tile_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
                ResultSet rs = null;
		int tile_owner = -1;

		try {
			String query = "SELECT tile_owner AS tile_owner FROM tile WHERE tile_id = ?";
			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, tile_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tile_owner = rs.getInt("tile_owner");
			}
			rs.close();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return tile_owner;
	}

	/**
	 * update the zone_id in the tile table
	 * 
	 * @param tile_id
	 * @param zone_id
	 * @throws SQLException
	 */
	public static void updateZone(int tile_id, int zone_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE tile SET zone_id = ? WHERE tile_id = ?";

			connection = GameDB.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, zone_id);
			pstmt.setInt(2, tile_id);
			pstmt.execute();
			pstmt.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}
