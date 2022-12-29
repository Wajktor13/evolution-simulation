package agh.ics.oop.interfaces;


import agh.ics.oop.classes.Animal;
import agh.ics.oop.classes.Vector2d;

public interface IPositionChangeObserver {

    /**
     * Changes the position of the given animal
     *
     * @param animal
     *            Animal that position has changed
     */
    void animalPositionChanged(Animal animal, Vector2d oldPosition);
}
