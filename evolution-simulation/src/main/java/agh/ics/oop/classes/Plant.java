package agh.ics.oop.classes;

import agh.ics.oop.abstract_classes.AbstractWorldMapElement;

public class Plant extends AbstractWorldMapElement {

    public Plant(Vector2d position, int energy) {
        super(position, energy);
    }

    @Override
    public String toString() {
        return " p";
    }

    @Override
    public String getImageUrl() {
        return "evolution-simulation/src/main/resources/plant.png";
    }
}
