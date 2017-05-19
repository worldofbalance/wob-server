package shared.model;

import common.TestUtils;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import shared.db.ScoreHistoryDAO;
import shared.metadata.Constants;
import shared.util.Vector3;

import java.sql.SQLException;
import java.util.*;

public class EcosystemTest {

    private int nextSpeciesId = 0;
    private int nextSpeciesGroupId = 0;
    private Ecosystem ecosystem;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        TestUtils.useTestDatabase();
    }

    @Before
    public void setUp() throws SQLException {
        ecosystem = makeEmptyEcosystem();
        TestUtils.clearTable("score_history");
    }

    private Ecosystem makeEmptyEcosystem() {
        return new Ecosystem(0, 0, 0, "test", (short) 0);
    }

    private Species makeSpecies(int biomass, float trophicLevel) {
        SpeciesType speciesType = new SpeciesType();
        speciesType.setTrophicLevel(trophicLevel);
        Species species = new Species(nextSpeciesId++, speciesType);
        SpeciesGroup speciesGroup = new SpeciesGroup(species, nextSpeciesGroupId++, biomass, new Vector3<>(0, 0, 0));
        species.add(speciesGroup);
        return species;
    }

    @Test
    public void testTotalBiomassNoSpecies() {
        assertEquals(0, ecosystem.totalBiomass());
    }

    @Test
    public void testTotalBiomassTwoSpecies() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(200, 2f));
        assertEquals(300, ecosystem.totalBiomass());
    }

    @Test
    public void testShannonIndexNoSpecies() {
        assertEquals(0.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexOneSpecies() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        assertEquals(0.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexOneSpeciesZeroBiomass() {
        ecosystem.setSpecies(makeSpecies(0, 1f));
        assertEquals(0.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexTwoSpeciesEqual() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(100, 1f));
        assertEquals(1.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexTwoSpeciesEqualAndThirdZero() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(0, 1f));
        assertEquals(1.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexThreeSpecies() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(50, 1f));
        ecosystem.setSpecies(makeSpecies(50, 1f));
        assertEquals(1.5, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testTrophicLevelWeightedTotalBiomassNoSpecies() {
        assertEquals(0.0, ecosystem.trophicLevelWeightedTotalBiomass(), 1e-20);
    }

    @Test
    public void testTrophicLevelWeightedTotalBiomassTwoSpecies() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(200, 2f));
        assertEquals(500.0, ecosystem.trophicLevelWeightedTotalBiomass(), 1e-20);
    }

    @Test
    public void testRawEnvironmentScoreNoSpecies() {
        assertEquals(0, ecosystem.rawEnvironmentScore());
    }

    @Test
    public void testRawEnvironmentScoreOneSpeciesZeroBiomass() {
        ecosystem.setSpecies(makeSpecies(0, 1f));
        assertEquals(0, ecosystem.rawEnvironmentScore());
    }

    @Test
    public void testRawEnvironmentScoreThreeSpecies() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(50, 2f));
        ecosystem.setSpecies(makeSpecies(50, 3f));
        int expectedBiomassScore = 100 + 50 * 2 + 50 * 3;
        int expectedDiversityScore = (int) Math.round(1.5 * expectedBiomassScore);
        int expectedTotalScore = expectedBiomassScore + expectedDiversityScore;
        assertEquals(expectedTotalScore, ecosystem.rawEnvironmentScore());
    }

    @Test
    public void testSmoothedEnvironmentScoreNoSpecies() {
        assertEquals(0, ecosystem.smoothedEnvironmentScore(), 1e-20);
    }

    @Test
    public void testSmoothedEnvironmentScoreNoHistory() {
        ecosystem.setSpecies(makeSpecies(100, 1f));
        ecosystem.setSpecies(makeSpecies(50, 2f));
        ecosystem.setSpecies(makeSpecies(50, 3f));
        int rawScore = ecosystem.rawEnvironmentScore();
        assertEquals(rawScore, ecosystem.smoothedEnvironmentScore(), 1e-20);
    }

    // Test score smoothing with a rolling history window and no repeating days.
    // Assumes scores are smoothed using rightTriangularWeightedAverage().
    @Test
    public void testSmoothedEnvironmentScore() {
        Deque<Integer> rawScores = new ArrayDeque<>();

        ecosystem.setScoreSmoothingWindowSize(3);

        ecosystem.setCurrentDay(1);
        ecosystem.setSpecies(makeSpecies(100, 1.1f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(rawScores.getLast(), ecosystem.smoothedEnvironmentScore(), 1e-20);

        ecosystem.setCurrentDay(2);
        ecosystem.setSpecies(makeSpecies(200, 2.2f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(Ecosystem.rightTriangularWeightedAverage(rawScores),
                ecosystem.smoothedEnvironmentScore(), 1e-20);

        ecosystem.setCurrentDay(3);
        ecosystem.setSpecies(makeSpecies(300, 3.3f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(Ecosystem.rightTriangularWeightedAverage(rawScores),
                ecosystem.smoothedEnvironmentScore(), 1e-20);

        // The first day of history should now be removed from the smoothing window
        rawScores.removeFirst();

        ecosystem.setCurrentDay(4);
        ecosystem.setSpecies(makeSpecies(400, 4.4f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(Ecosystem.rightTriangularWeightedAverage(rawScores),
                ecosystem.smoothedEnvironmentScore(), 1e-20);
    }

    // Similar to testSmoothedEnvironmentScore(),
    // but updates species biomass and score twice in a day
    // to verify that the the second calculated score replaces the first.
    @Test
    public void testSmoothedEnvironmentScoreRepeatingDays() {
        Deque<Integer> rawScores = new ArrayDeque<>();

        ecosystem.setScoreSmoothingWindowSize(2);

        ecosystem.setCurrentDay(1);
        ecosystem.setSpecies(makeSpecies(100, 1.1f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(rawScores.getLast(), ecosystem.smoothedEnvironmentScore(), 1e-20);

        ecosystem.setCurrentDay(2);
        ecosystem.setSpecies(makeSpecies(200, 2.2f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(Ecosystem.rightTriangularWeightedAverage(rawScores),
                ecosystem.smoothedEnvironmentScore(), 1e-20);

        // Repeat day 2 with different numbers
        rawScores.removeLast();
        ecosystem.setCurrentDay(2);
        ecosystem.setSpecies(makeSpecies(220, 2.8f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(Ecosystem.rightTriangularWeightedAverage(rawScores),
                ecosystem.smoothedEnvironmentScore(), 1e-20);

        // The first day of history should now be removed from the smoothing window
        rawScores.removeFirst();

        ecosystem.setCurrentDay(3);
        ecosystem.setSpecies(makeSpecies(300, 3.3f));
        rawScores.addLast(ecosystem.rawEnvironmentScore());
        assertEquals(Ecosystem.rightTriangularWeightedAverage(rawScores),
                ecosystem.smoothedEnvironmentScore(), 1e-20);
    }

    @Test
    public void testLoadRawScoreHistory() {
        ScoreHistoryDAO.setRawScore(0, 1, 10);
        ScoreHistoryDAO.setRawScore(0, 2, 20);
        ecosystem.setScoreSmoothingWindowSize(2);
        ecosystem.setCurrentDay(3);
        ecosystem.loadRawScoreHistory();

        assertEquals(true, ecosystem.rawScoreHistoryLoaded);

        Integer[] expectedHistory = {10, 20};
        assertArrayEquals(expectedHistory, ecosystem.rawScoreHistory.toArray());
    }

    @Test
    public void testRightTriangularWeightedAverageEmpty() {
        assertEquals(0, Ecosystem.rightTriangularWeightedAverage(new ArrayDeque<>()), 1e-20);
    }

    @Test
    public void testRightTriangularWeightedAverageSingleElement() {
        Deque<Integer> values = new ArrayDeque<>();
        values.add(12345);
        assertEquals(12345, Ecosystem.rightTriangularWeightedAverage(values), 1e-20);
    }

    @Test
    public void testRightTriangularWeightedAverageTwoElements() {
        Deque<Integer> values = new ArrayDeque<>();
        values.addLast(10);  // weight = 1
        values.addLast(20);  // weight = 2
        // sum of weights = 3
        assertEquals((10 * 1 + 20 * 2) / 3.0, Ecosystem.rightTriangularWeightedAverage(values), 1e-20);
    }

    @Test
    public void testRightTriangularWeightedAverageThreeElements() {
        Deque<Integer> values = new ArrayDeque<>();
        values.addLast(10);  // weight = 1
        values.addLast(20);  // weight = 2
        values.addLast(30);  // weight = 3
        // sum of weights = 6
        assertEquals((10 * 1 + 20 * 2 + 30 * 3) / 6.0, Ecosystem.rightTriangularWeightedAverage(values), 1e-20);
    }

    /**
     * Note: testing of updateEnvironmentScore is currently limited to testing the
     * changes related to the smoothedEnvironmentScore() and scaledSmoothedEnvironmentScore() methods.
     */
    @Test
    public void testUpdateEnvironmentScore() {
        ScoreHistoryDAO.setRawScore(0, 1, 10);
        ScoreHistoryDAO.setRawScore(0, 2, 20);
        ecosystem.setScoreSmoothingWindowSize(2);
        ecosystem.setCurrentDay(3);
        try {
            ecosystem.updateEnvironmentScore();
        } catch (NullPointerException e) {
            // Ignore irrelevant NullPointerException caused by NetworkFunctions.sendToPlayer()
        }

        Deque<Integer> expectedRawScores = new ArrayDeque<>();
        expectedRawScores.addLast(20);
        expectedRawScores.addLast(0);
        double expectedSmoothedScore = Ecosystem.rightTriangularWeightedAverage(expectedRawScores);
        int expectedScaledScore = (int) Math.round(
                Math.sqrt(expectedSmoothedScore) * Constants.SCORE_MULTIPLIER);

        assertEquals(expectedScaledScore, ecosystem.getScore());
    }
}