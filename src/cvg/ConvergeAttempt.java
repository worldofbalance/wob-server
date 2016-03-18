package cvg;

// Java Imports

// Other Imports

/**
 * The ConvergeAttempt class is contains data representing the configuration of a 
 * single Converge Attempt attempt.
 */
public class ConvergeAttempt {

    protected int playerId;
    protected int ecosystemId;
    protected int attemptId;
    protected boolean allowHints;
    protected int hintId;
    protected java.sql.Timestamp time;
    protected int score;
    protected String config;
    protected String csv;

    public ConvergeAttempt() {
    }

    public ConvergeAttempt(int playerId, 
            int ecosystemId, 
            int attemptId, 
            boolean allowHints,
            int hintId,
            java.sql.Timestamp time, 
            String config, 
            String csv
    ) {
        this.ecosystemId = ecosystemId;
        this.allowHints = allowHints;
        this.hintId = hintId;
        this.time = time;
        this.config = config;
        this.csv = csv;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getEcosystemId() {
        return ecosystemId;
    }

    public void setEcosystemId(int ecosystemId) {
        this.ecosystemId = ecosystemId;
    }

    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    public java.sql.Timestamp getTime() {
        return this.time;
    }

    public void setTime(java.sql.Timestamp time) {
        this.time = time;
    }

    public String getConfig() {
        return this.config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getCsv() {
        return this.csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public Boolean getAllowHints() {
        return this.allowHints;
    }

    public void setAllowHints(boolean allow) {
        this.allowHints = allow;
    }

    public int getHintId() {
        return this.hintId;
    }

    public void setHintId(int id) {
        this.hintId = id;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
