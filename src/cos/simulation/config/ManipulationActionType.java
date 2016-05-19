package cos.simulation.config;

/**
 *
 * @author Sonal
 */
public enum ManipulationActionType {

    SPECIES_REMOVAL(0, "Removing "),
    SPECIES_INVASION(1, "Adding "),
    SPECIES_EXPLOIT(2, "Reducing "),
    SPECIES_PROLIFERATION(3, "Increasing "),
    MULTIPLE_BIOMASS_UPDATE(4, "Increasing ");

    private final int typeIndex;
    private final String descript;

    private ManipulationActionType(int typeIndex, String descript) {
        this.typeIndex = typeIndex;
        this.descript = descript;
    }

    public int getManipulationActionType() {
        return typeIndex;
    }

    public String getManipulationActionDescript() {
        return descript;
    }

    public boolean equals(int type) {
        return (this.typeIndex == type);
    }
}
