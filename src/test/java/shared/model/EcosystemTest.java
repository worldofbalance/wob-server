package shared.model;

import org.junit.Before;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import shared.util.Vector3;

import java.util.HashSet;
import java.util.Set;

public class EcosystemTest {

    private int nextSpeciesId = 0;
    private int nextSpeciesGroupId = 0;
    private Ecosystem ecosystem;

    @Before
    public void setUp() {
        ecosystem = makeEmptyEcosystem();
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
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(200, 2f));
        assertEquals(300, ecosystem.totalBiomass());
    }

    @Test
    public void testShannonIndexNoSpecies() {
        assertEquals(0.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexOneSpecies() {
        ecosystem.addSpecies(makeSpecies(100, 1f));
        assertEquals(0.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexOneSpeciesZeroBiomass() {
        ecosystem.addSpecies(makeSpecies(0, 1f));
        assertEquals(0.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexTwoSpeciesEqual() {
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(100, 1f));
        assertEquals(1.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexTwoSpeciesEqualAndThirdZero() {
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(0, 1f));
        assertEquals(1.0, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testShannonIndexThreeSpecies() {
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(50, 1f));
        ecosystem.addSpecies(makeSpecies(50, 1f));
        assertEquals(1.5, ecosystem.shannonIndex(), 1e-20);
    }

    @Test
    public void testTrophicLevelWeightedTotalBiomassNoSpecies() {
        assertEquals(0.0, ecosystem.trophicLevelWeightedTotalBiomass(), 1e-20);
    }

    @Test
    public void testTrophicLevelWeightedTotalBiomassTwoSpecies() {
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(200, 2f));
        assertEquals(500.0, ecosystem.trophicLevelWeightedTotalBiomass(), 1e-20);
    }

    @Test
    public void testRawEnvironmentScoreNoSpecies() {
        assertEquals(0, ecosystem.rawEnvironmentScore());
    }

    @Test
    public void testRawEnvironmentScoreOneSpeciesZeroBiomass() {
        ecosystem.addSpecies(makeSpecies(0, 1f));
        assertEquals(0, ecosystem.rawEnvironmentScore());
    }

    @Test
    public void testRawEnvironmentScoreThreeSpecies() {
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(50, 2f));
        ecosystem.addSpecies(makeSpecies(50, 3f));
        int expectedBiomassScore = 100 + 50 * 2 + 50 * 3;
        int expectedDiversityScore = (int) Math.round(1.5 * expectedBiomassScore);
        int expectedTotalScore = expectedBiomassScore + expectedDiversityScore;
        assertEquals(expectedTotalScore, ecosystem.rawEnvironmentScore());
    }

    @Test
    public void testSmoothedEnvironmentScoreNoSpecies() {
        assertEquals(0, ecosystem.smoothedEnvironmentScore());
    }

    @Test
    public void testSmoothedEnvironmentScoreNoHistory() {
        ecosystem.addSpecies(makeSpecies(100, 1f));
        ecosystem.addSpecies(makeSpecies(50, 2f));
        ecosystem.addSpecies(makeSpecies(50, 3f));
        int rawScore = ecosystem.rawEnvironmentScore();
        assertEquals(rawScore, ecosystem.smoothedEnvironmentScore());
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
        int smoothedScore = -1;
        ecosystem.setScoreSmoothingWindowSize(windowSize);
        for (int day = 0; day < numDays; day++) {
            ecosystem.setCurrentDay(day);
            int iterations = daysToRepeat.contains(day) ? 2 : 1;
            for (int i = 0; i < iterations; i++) {
                ecosystem.addSpecies(makeSpecies(day * 100, (float) day / 2));
                rawScores[day] = ecosystem.rawEnvironmentScore();
                smoothedScore = ecosystem.smoothedEnvironmentScore();
            }
        }
        assertEquals(arrayWindowMean(rawScores, numDays - windowSize, windowSize), smoothedScore);
    }

    private int arrayWindowMean(int[] data, int start, int windowSize) {
        double sum = 0;
        for (int i = start; i < start + windowSize; i++) {
            sum += data[i];
        }
        double mean = sum / windowSize;
        return (int) (Math.round(mean));
    }
}