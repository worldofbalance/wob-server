package cos.core;

// Java Imports

import cos.util.EventListener;
import cos.util.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Objective {

    private int objective_id;
    private String name;
    private int amount;
    private int target;
    private String unit;
    private boolean isDone;
    private Map<Short, Integer> rewards;
    private EventListener eventListener;
    private List<EventType> events;
    private Map<String, Object> values;

    public Objective(int objective_id, String name, int target, String unit) {
        this.objective_id = objective_id;
        this.name = name;
        this.target = target;
        this.unit = unit;

        rewards = new HashMap<Short, Integer>();
        events = new ArrayList<EventType>();
        values = new HashMap<String, Object>();
    }

    public Map<Short, Integer> getRewards() {
        return rewards;
    }

    public void setReward(short reward_type, int amount) {
        rewards.put(reward_type, amount);
    }
    
    public List<EventType> getEvents() {
        return events;
    }
    
    public void setEvent(EventType event) {
        events.add(event);
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public Integer getID() {
        return objective_id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        isDone = this.amount == target;
    }

    public int getTarget() {
        return target;
    }

    public int setTarget(int target) {
        return target;
    }
    
    public String getUnit() {
        return unit;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setValue(String name, Object value) {
        values.put(name, value);
    }

    public <T> T getValue(String name, Class<T> cls) {
        if (values.containsKey(name)) {
            return cls.cast(values.get(name));
        }

        return null;
    }
}
