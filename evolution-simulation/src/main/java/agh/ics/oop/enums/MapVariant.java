package agh.ics.oop.enums;

public enum MapVariant {
    SPHERICAL_EARTH,
    INFERNAL_PORTAL;

    public static MapVariant toMapVariant(String mapVariant) {
        return switch (mapVariant) {
            case "Spherical Earth" -> SPHERICAL_EARTH;
            case "Infernal Portal" -> INFERNAL_PORTAL;
            default -> throw new IllegalStateException("Unexpected value: " + mapVariant);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case SPHERICAL_EARTH -> "Spherical Earth";
            case INFERNAL_PORTAL -> "Infernal Portal";
        };
    }
}
