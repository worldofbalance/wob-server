package shared.db;

import static org.junit.Assert.assertEquals;

import common.TestUtils;
import org.junit.*;

import conf.Configuration;
import shared.core.GameEngine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * For these tests to work, a local MySQL database called wob_test is required,
 * and it must contain the species table from the main WoB database,
 * as well as an empty score_history table (created using sql/create_score_history.sql).
 *
 * @author Ben Saylor
 */
public class ScoreHistoryDAOTest {

    @BeforeClass
    public static void setUpClass() throws SQLException {
        TestUtils.useTestDatabase();
    }

    @Before
    public void setUp() throws SQLException {
        TestUtils.clearTable("score_history");
    }

    @Test
    public void testEmptyTable() {
        List<Integer> scores = ScoreHistoryDAO.getRawScoreHistory(0, 0, 100);
        assertEquals(0, scores.size());
    }

    @Test
    public void testThreeDays() {
        ScoreHistoryDAO.setRawScore(0, 1, 11);
        ScoreHistoryDAO.setRawScore(0, 2, 22);
        ScoreHistoryDAO.setRawScore(0, 3, 33);
        List<Integer> scores = ScoreHistoryDAO.getRawScoreHistory(0, 1, 3);
        assertEquals(3, scores.size());
        assertEquals(11, scores.get(0).intValue());
        assertEquals(22, scores.get(1).intValue());
        assertEquals(33, scores.get(2).intValue());
    }

    @Test
    public void testEcosystemFiltering() {
        ScoreHistoryDAO.setRawScore(0, 1, 11);
        ScoreHistoryDAO.setRawScore(0, 2, 22);
        ScoreHistoryDAO.setRawScore(0, 3, 33);
        ScoreHistoryDAO.setRawScore(1, 1, 111);
        ScoreHistoryDAO.setRawScore(1, 2, 222);
        ScoreHistoryDAO.setRawScore(1, 3, 333);
        List<Integer> scores = ScoreHistoryDAO.getRawScoreHistory(0, 1, 3);
        assertEquals(3, scores.size());
        assertEquals(11, scores.get(0).intValue());
        assertEquals(22, scores.get(1).intValue());
        assertEquals(33, scores.get(2).intValue());
    }

    @Test
    public void testExcludeEarlierHistory() {
        ScoreHistoryDAO.setRawScore(0, 1, 11);
        ScoreHistoryDAO.setRawScore(0, 2, 22);
        ScoreHistoryDAO.setRawScore(0, 3, 33);
        List<Integer> scores = ScoreHistoryDAO.getRawScoreHistory(0, 2, 3);
        assertEquals(2, scores.size());
        assertEquals(22, scores.get(0).intValue());
        assertEquals(33, scores.get(1).intValue());
    }

    @Test
    public void testExcludeLaterHistory() {
        ScoreHistoryDAO.setRawScore(0, 1, 11);
        ScoreHistoryDAO.setRawScore(0, 2, 22);
        ScoreHistoryDAO.setRawScore(0, 3, 33);
        List<Integer> scores = ScoreHistoryDAO.getRawScoreHistory(0, 1, 2);
        assertEquals(2, scores.size());
        assertEquals(11, scores.get(0).intValue());
        assertEquals(22, scores.get(1).intValue());
    }

    @Test
    public void testInsertOutOfOrder() {
        ScoreHistoryDAO.setRawScore(0, 1, 11);
        ScoreHistoryDAO.setRawScore(0, 3, 33);
        ScoreHistoryDAO.setRawScore(0, 2, 22);
        List<Integer> scores = ScoreHistoryDAO.getRawScoreHistory(0, 1, 3);
        assertEquals(3, scores.size());
        assertEquals(11, scores.get(0).intValue());
        assertEquals(22, scores.get(1).intValue());
        assertEquals(33, scores.get(2).intValue());
    }
}
