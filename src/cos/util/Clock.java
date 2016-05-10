package cos.util;

// Other Imports
import cos.metadata.Constants;

public class Clock {

    private long time;
    private long lastRunTime;
    private int prevDay;
    private int day;
    private float rate;
    private final EventHandler eventHandler = new EventHandler();

    public Clock(int day, float rate) {
        this.day = day;
        this.rate = rate;

        prevDay = day;
    }

    public void run() {
        if (lastRunTime == 0) {
            lastRunTime = System.currentTimeMillis();
        }

        long currentRunTime = System.currentTimeMillis();
        double time_diff = Math.floor((currentRunTime - lastRunTime) / 1000 * rate);

        if (time_diff > 0) {
            time += time_diff;

            // Convert Game Time to Day, Month, Year
            day += Math.floor(time / Constants.DAY_DURATION);

            while (prevDay < day) {
                prevDay++;
                eventHandler.execute(EventType.NEW_DAY, prevDay);

                if (prevDay % 30 == 0) { // New Month
                    eventHandler.execute(EventType.NEW_MONTH, prevDay / 30 + 1);
                }

                if (prevDay % 360 == 0) { // New Year
                    eventHandler.execute(EventType.NEW_YEAR, prevDay / 360 + 1);
                }
            }

            lastRunTime = currentRunTime;
        }
    }

    public long getTime() {
        return time;
    }

    public float setRate(float rate) {
        return this.rate = rate;
    }

    public void createEvent(EventType event_type, EventListener listener) {
        eventHandler.add(event_type, listener);
    }
    
    public void removeEvent(EventType event_type, EventListener listener) {
        eventHandler.remove(event_type, listener);
    }
}
