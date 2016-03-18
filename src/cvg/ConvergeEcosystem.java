package cvg;

// Java Imports

// Other Imports

/**
 * The ConvergeEcosystem class is contains data representing the configuration of a 
 single Converge Game Ecosystem.
 */
public class ConvergeEcosystem {

    protected int ecosystemId;
    protected String description;
    protected int timesteps;
    protected String configDefault;
    protected String configTarget;
    protected String csvDefault;
    protected String csvTarget;

    public ConvergeEcosystem() {
    }

    public ConvergeEcosystem(int ecosystemId, String descript, int timesteps, 
            String configDefault, String configTarget, String csvDefault,
            String csvTarget) {
        this.ecosystemId = ecosystemId;
        this.description = descript;
        this.timesteps = timesteps;
        this.configDefault = configDefault;
        this.configTarget = configTarget;
        this.csvDefault = csvDefault;
        this.csvTarget = csvTarget;
    }

    public int getEcosystemId() {
        return ecosystemId;
    }

    public void setEcosystemId(int ecosystemId) {
        this.ecosystemId = ecosystemId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String descript) {
        this.description = descript;
    }

    public int getTimesteps() {
        return timesteps;
    }

    public void setTimesteps(int timesteps) {
        this.timesteps = timesteps;
    }

    public String getConfigDefault() {
        return this.configDefault;
    }

    public void setConfigDefault(String configDefault) {
        this.configDefault = configDefault;
    }

    public String getConfigTarget() {
        return this.configTarget;
    }

    public void setConfigTarget(String configTarget) {
        this.configTarget = configTarget;
    }

    public String getCsvDefault() {
        return this.csvDefault;
    }

    public void setCsvDefault(String csvDefault) {
        this.csvDefault = csvDefault;
    }

    public String getCsvTarget() {
        return this.csvTarget;
    }

    public void setCsvTarget(String csvTarget) {
        this.csvTarget = csvTarget;
    }

}
