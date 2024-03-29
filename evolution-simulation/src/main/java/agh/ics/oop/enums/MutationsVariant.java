package agh.ics.oop.enums;

public enum MutationsVariant {
    FULLY_RANDOMIZED;

    public static MutationsVariant toMutationsVariant(String mutationsVariant) {
        return switch (mutationsVariant) {
            case "Fully Randomized" -> FULLY_RANDOMIZED;
            default -> throw new IllegalStateException("Unexpected value: " + mutationsVariant);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case FULLY_RANDOMIZED -> "Fully Randomized";
        };
    }
}
