package lby.core.badge;

// Java Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

// Other Imports

import shared.util.EventHandler;
import shared.util.EventListener;
import shared.util.EventType;
import shared.core.Objective;
import lby.db.badge.BadgeDAO;
import shared.db.ScoreDAO;
import shared.model.Ecosystem;
import lby.net.response.badge.ResponseBadgeList;
import lby.net.response.badge.ResponseBadgeUpdate;
import shared.util.GameTimer;
import shared.util.NetworkFunctions;

public class BadgeController {

    // Environment Score Max Threshold
    public static int maxScoreThreshold;
    public static GameTimer badgeScoreTimer = new GameTimer();
    // Other
    private int player_id;
    private EventHandler eventHandler;
    private Map<Integer, Badge> badgeList = new HashMap<Integer, Badge>();
    private int currentScoreThreshold;

    public BadgeController(int player_id, EventHandler eventHandler) {
        this.player_id = player_id;
        this.eventHandler = eventHandler;
        this.currentScoreThreshold = maxScoreThreshold;

        load();
    }

    public static void setBadgeScores() {
        int threshold = 0;

        List<Integer> scoreList = ScoreDAO.getEnvironmentScores();

        if (scoreList.size() >= 10) {
            // Create 10 Bins
            int numBins = 10;
            List<Integer[]> binList = new ArrayList<Integer[]>(numBins);
            int interval = scoreList.get(0) / numBins;
            // Initialize Bins
            for (int i = 0; i < numBins; i++) {
                binList.add(new Integer[]{i * interval, 0});
            }
            // Determine Frequencies
            for (int score : scoreList) {
                binList.get(score / (interval + 1))[1]++;
            }
            // Sort Frequecies by Descending Order
            Collections.sort(binList, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] t, Integer[] t1) {
                    int value = t1[1].compareTo(t[1]);
                    return value == 0 ? t1[0].compareTo(t[0]) : value;
                }
            });
            // Get Most Frequent Score
            threshold = binList.get(0)[0] + interval;
            // Update Every 30 Minutes
            badgeScoreTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    setBadgeScores();
                }
            }, 1800000, 1800000);
        }

        maxScoreThreshold = Math.max(50000, threshold);
    }

    public final void load() {
        float threshold = currentScoreThreshold;
        int months;

        Badge badge;
        int badge_id = 1;
        Objective objective;

        badge = new Badge(badge_id++, "Eagle");
        threshold *= 0.95f;
        months = 1;
        objective = create(badge, badge.getID(), "Maintain at least " + (int) Math.round(threshold) + " Env Score for " + months + " months in a row", (int) Math.round(threshold), months);
        badge.setObjective(objective);
        this.add(badge);

        badge = new Badge(badge_id++, "Elephant");
        threshold *= 0.85f;
        months = 4;
        objective = create(badge, badge.getID(), "Maintain at least " + (int) Math.round(threshold) + " Env Score for " + months + " months in a row", (int) Math.round(threshold), months);
        badge.setObjective(objective);
        this.add(badge);
    }

    public final void initialize(List<Badge> badgeList) {
        for (Badge badge : badgeList) {
            Badge temp = this.badgeList.get(badge.getID());
            temp.setAmount(badge.getAmount());
            temp.setProgress(badge.getProgess());
        }
    }

    public Map<Integer, Badge> getBadgeList() {
        return badgeList;
    }

    public void add(Badge badge) {
        if (eventHandler != null) {
            eventHandler.add(EventType.NEW_MONTH, badge.getObjective().getEventListener());
        }

        badgeList.put(badge.getID(), badge);
    }

    public Objective create(final Badge badge, int objective_id, String name, int threshold, int target) {
        final Objective objective = new Objective(objective_id, name, target, "");
        objective.setValue("Threshold", threshold);

        EventListener eventListener = new EventListener() {
            @Override
            public void run(Object... args) {
                Ecosystem ecosystem = (Ecosystem) args[0];

                if (ecosystem.getScore() >= objective.getValue("Threshold", Integer.class)) {
                    objective.setAmount(objective.getAmount() + 1);
                } else {
                    objective.setAmount(0);
                }

                update(badge, objective);
            }
        };

        objective.setEventListener(eventListener);

        return objective;
    }

    public void update(Badge badge, Objective objective) {
        // Sync Badge Progress
        ResponseBadgeUpdate response = new ResponseBadgeUpdate();
        response.setBadgeID(badge.getID());
        response.setProgress(objective.getAmount());
        response.setDone(objective.isDone());
        // Badge Complete
        if (objective.getAmount() == objective.getTarget()) {
            badge.setAmount(badge.getAmount() + 1);
            objective.setAmount(0);
            // Update Threshold
            currentScoreThreshold = maxScoreThreshold;

            float threshold = 0;

            if (badge.getName().equals("Eagle")) {
                threshold = currentScoreThreshold * 0.95f;
            } else if (badge.getName().equals("Elephant")) {
                threshold = currentScoreThreshold * 0.95f * 0.85f;
            }

            if ((int) Math.round(threshold) != objective.getValue("Threshold", Integer.class)) {
                objective.setName("Maintain at least " + (int) Math.round(threshold) + " Env Score for " + objective.getTarget() + " months in a row");
                objective.setValue("Threshold", (int) Math.round(threshold));

                ResponseBadgeList badgeListResponse = new ResponseBadgeList();
                badgeListResponse.setBadgeList(new ArrayList<Badge>(Arrays.asList(new Badge[]{badge})));
                NetworkFunctions.sendToPlayer(badgeListResponse, player_id);
            }
        }

        response.setAmount(badge.getAmount());
        NetworkFunctions.sendToPlayer(response, player_id);
        // Save Badge Progress
        BadgeDAO.createEntry(player_id, badge.getID(), badge.getAmount(), badge.getProgess());
    }
}
