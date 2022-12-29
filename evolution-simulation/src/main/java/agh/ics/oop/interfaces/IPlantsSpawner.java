package agh.ics.oop.interfaces;

import agh.ics.oop.classes.Vector2d;

import java.util.ArrayList;

public interface IPlantsSpawner {

    /**
     * Generates a position for a new plant
     *
     * @param freePositions
     *            Currently available positions
     * @return position for a new plant or null
     * if there are no free positions.
     */

    Vector2d generatePlantPosition(ArrayList<Vector2d> freePositions);
}
