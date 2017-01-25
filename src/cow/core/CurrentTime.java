/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cow.core;

/**
 *
 * @author redyoonnk1
 */
public class CurrentTime {
    private int seasonID;
    private int month;
    private int day;
    private int seconds;
    private int randTime;
    private int randOffset;
    private boolean eventsActive;

    public int getRandOffset() {
        return randOffset;
    }

    public void setRandOffset(int randOffset) {
        this.randOffset = randOffset;
    }

    public int getRandTime() {
        return randTime;
    }

    public void setRandTime(int randTime) {
        this.randTime = randTime;
    }
    
    public boolean isEventsActive() {
        return eventsActive;
    }

    public void setEventsActive(boolean eventsActive) {
        this.eventsActive = eventsActive;
    }
    
    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
