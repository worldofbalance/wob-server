package shared.model;

import common.TestUtils;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import shared.db.ScoreHistoryDAO;
import shared.metadata.Constants;
import shared.util.Vector3;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Test
    public void testSmoothedEnvironmentScoreSingleWindow() {
        testSmoothedEnvironmentScore(3, 3);
    }

    @Test
    public void testSmoothedEnvironmentScoreRollingWindow() {
        testSmoothedEnvironmentScore(5, 3);
    }

    @Test
    public void testSmoothedEnvironmentScoreRepeatingDays() {
        Set<Integer> daysToRepeat = new HashSet<>();
        daysToRepeat.add(2);
        daysToRepeat.add(4);
        testSmoothedEnvironmentScore(5, 3, daysToRepeat);
    }

    private void testSmoothedEnvironmentScore(int numDays, int windowSize) {
        testSmoothedEnvironmentScore(numDays, windowSize, new HashSet<>());
    }

    private void testSmoothedEnvironmentScore(int numDays, int windowSize, Set<Integer> daysToRepeat) {
        int[] rawScores = new int[numDays];
        double smoothedScore = -1;
        ecosystem.setScoreSmoothingWindowSize(windowSize);
        for (int day = 0; day < numDays; day++) {
            ecosystem.setCurrentDay(day);
            int iterations = daysToRepeat.contains(day) ? 2 : 1;
            for (int i = 0; i < iterations; i++) {
                ecosystem.setSpecies(makeSpecies(day * 100, (float) day / 2));
                rawScores[day] = ecosystem.rawEnvironmentScore();
                smoothedScore = ecosystem.smoothedEnvironmentScore();
            }
        }
        assertEquals(arrayWindowMean(rawScores, numDays - windowSize, windowSize), smoothedScore, 1e-20);
    }

    private double arrayWindowMean(int[] data, int start, int windowSize) {
        double sum = 0;
        for (int i = start; i < start + windowSize; i++) {
            sum += data[i];
        }
        return sum / windowSize;
    }

    @Test
    public void testLoadRawScoreHistory() {
        ScoreHistoryDAO.setRawScore(0, 1, 10);
        ScoreHistoryDAO.setRawScore(0, 2, 20);
        ecosystem.setScoreSmoothingWindowSize(2);
        ecosystem.setCurrentDay(3);
        ecosystem.loadRawScoreHistory();
        double smoothedScore = ecosystem.smoothedEnvironmentScore();
        assertEquals(10, smoothedScore, 1e-20);  // (10 + 20 + 0) / 3 = 10
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
        int expectedScore = (int) Math.round(Math.sqrt(10) * Constants.SCORE_MULTIPLIER);
        assertEquals(expectedScore, ecosystem.getScore());
    }
}