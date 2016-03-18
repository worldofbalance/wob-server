package shared.util;

// Java Imports
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventHandler {

    Map<EventType, List<EventListener>> listeners = new EnumMap<EventType, List<EventListener>>(EventType.class);

    public void add(EventType event_type, EventListener listener) {
        List<EventListener> tempList;

        if (listeners.containsKey(event_type)) {
            tempList = listeners.get(event_type);
        } else {
            tempList = new ArrayList<EventListener>();
            listeners.put(event_type, tempList);
        }

        tempList.add(listener);
    }

    public void remove(EventType event_type, EventListener listener) {
        if (listeners.containsKey(event_type)) {
            List<EventListener> tempList = listeners.get(event_type);
            tempList.remove(listener);
        }
    }

    public void execute(EventType event_type, Object... args) {
        if (listeners.containsKey(event_type)) {
            for (EventListener listener : new ArrayList<EventListener>(listeners.get(event_type))) {
                try {
                    listener.run(args);
                } catch (Exception ex) {
                    Log.println_e(ex.getMessage());
                }
            }
        }
    }
}
