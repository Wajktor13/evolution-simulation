package agh.ics.oop;

import java.util.ArrayList;

public interface IPlantsSpawner {

    /**
     * Generates a position for a new plant
     *
     * @param freePositions
     *            Currently available positions
     * @return position for a new plant or null if
     * if there are no free positions.
     */

    Vector2d generatePlantPosition(ArrayList<Vector2d> freePositions);
}
