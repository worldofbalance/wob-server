package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Other Imports
import shared.model.NewsArticle;
import shared.util.Log;

/**
 * Table(s) Required: news
 * 
 * @author Gary
 */
public final class NewsDAO {

    private NewsDAO() {
    }

    public static Map<Integer, NewsArticle> getNews() {
        Map<Integer, NewsArticle> newsList = new HashMap<Integer, NewsArticle>();

        String query = "SELECT * FROM `news`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                NewsArticle news = new NewsArticle(rs.getInt("news_id"));
                news.setText(rs.getString("text"));
                news.setCreateTime(rs.getString("create_time"));

                newsList.put(news.getID(), news);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return newsList;
    }

    public static NewsArticle getNews(int news_id) {
        NewsArticle news = null;

        String query = "SELECT * FROM `news` WHERE `news_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, news_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                news = new NewsArticle(rs.getInt("news_id"));
                news.setText(rs.getString("text"));
                news.setCreateTime(rs.getString("create_time"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return news;
    }

    public static NewsArticle getLatestNews() {
        NewsArticle news = null;

        String query = "SELECT * FROM `news` ORDER BY `create_time` DESC LIMIT 1";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                news = new NewsArticle(rs.getInt("news_id"));
                news.setText(rs.getString("text"));
                news.setCreateTime(rs.getString("create_time"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return news;
    }
}
