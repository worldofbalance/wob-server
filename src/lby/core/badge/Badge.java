package lby.core.badge;

// Java Imports
import shared.core.Objective;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Badge {

    private int badge_id;
    private String name;
    private Map<Short, Integer> rewards = new HashMap<Short, Integer>();
    private Objective objective;
    private int amount;
    private int progress;

    public Badge(int badge_id, String name) {
        this.badge_id = badge_id;
        this.name = name;
    }

    public Badge(int badge_id, int amount, int progress) {
        this.badge_id = badge_id;
        this.amount = amount;
        this.progress = progress;
    }

    public Map<Short, Integer> getRewards() {
        return rewards;
    }

    public void setReward(short reward_type, int amount) {
        rewards.put(reward_type, amount);
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Integer getID() {
        return badge_id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProgess() {
        if (objective != null) {
            return objective.getAmount();
        }

        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        objective.setAmount(progress);
    }
}
