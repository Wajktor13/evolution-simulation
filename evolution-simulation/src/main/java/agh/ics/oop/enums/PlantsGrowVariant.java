package agh.ics.oop.enums;

public enum PlantsGrowVariant {
    PREFER_EQUATOR;

    public static PlantsGrowVariant toPlantsGrowVariant(String plantsGrowVariant) {
        return switch (plantsGrowVariant) {
            case "Prefer Equator" -> PREFER_EQUATOR;
            default -> throw new IllegalStateException("Unexpected value: " + plantsGrowVariant);
        };
    }

}
