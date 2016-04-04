package cow.model;

/**
 * Created by hduan on 4/3/2016.
 */
public enum CardType {
    CARNIVORE("c"),
    HERBIVORE("h"),
    OMNIVORE("o"),
    FOOD("f"),
    WEATHER("w");

    private final String type;
    CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static CardType convertCardType(String typeString) {
        CardType type = null;
        switch (typeString) {
            case "c":
                type = CARNIVORE;
                break;
            case "h":
                type = HERBIVORE;
                break;
            case "o":
                type = OMNIVORE;
                break;
            case "f":
                type = FOOD;
                break;
            case "w":
                type = WEATHER;
                break;
        }

        return type;
    }
}
