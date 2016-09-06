package shared.model;

public class Daily {

    private int species_id;
    private String speciesName;
    private int month;
    private String type;
    private int amount;

    public int getSpeciesID() {
        return species_id;
    }

    public void setSpeciesID(int species_id) {
        this.species_id = species_id;
    }

    public void setSpeciesName(String name) {
        this.speciesName = name;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
