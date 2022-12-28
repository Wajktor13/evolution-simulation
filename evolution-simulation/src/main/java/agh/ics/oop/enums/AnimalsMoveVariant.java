package agh.ics.oop.enums;

public enum AnimalsMoveVariant {
    FULL_PREDESTINATION;

    public static AnimalsMoveVariant toAnimalsMoveVariant(String animalsMoveVariant) {
        return switch (animalsMoveVariant) {
            case "Full Predestination" -> FULL_PREDESTINATION;
            default -> throw new IllegalStateException("Unexpected value: " + animalsMoveVariant);
        };
    }
}
