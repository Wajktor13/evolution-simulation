package agh.ics.oop.enums;

public enum MapVariant {
    SPHERICAL_EARTH;

    public static MapVariant toMapVariant(String mapVariant) {
        return switch (mapVariant) {
            case "Spherical Earth" -> SPHERICAL_EARTH;
            default -> throw new IllegalStateException("Unexpected value: " + mapVariant);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case SPHERICAL_EARTH -> "Spherical Earth";
        };
    }
}
