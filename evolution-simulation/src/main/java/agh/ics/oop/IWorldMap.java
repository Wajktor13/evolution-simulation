package agh.ics.oop;

import java.util.TreeSet;


/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */

public interface IWorldMap {

//
//    probably unnecessary (for now)
//
//    /**
//     * Indicate if any object can move to the given position.
//     *
//     * @param position
//     *            The position checked for the movement possibility.
//     * @return True if the object can move to that position.
//     */
//    boolean canMoveTo(Vector2d position);
//
//    /**
//     * Return true if given position on the map is occupied. Should not be
//     * confused with canMove since there might be empty positions where the animal
//     * cannot move.
//     *
//     * @param position
//     *             position to check.
//     * @return True if the position is occupied.
//     */
//    boolean isOccupied(Vector2d position);

    /**
     * Places an animal on the map.
     *
     * @param animal
     *            The animal to be placed on the map.
     */
    void placeAnimal(Animal animal);



    /**
     * Returns animals at the given position.
     *
     * @param position
     *            The position of the animals.
     * @return TreeSet of the animals occupying the given position.
     */
    TreeSet<Animal> animalsAt(Vector2d position);

    /**
     * Normalizes energy, position and direction of the animal
     *
     * @param animal
     *            Animal to be normalized
     */
    void normalizeAnimalState(Animal animal, Vector2d newPosition);

    /**
     * Removes the animal from the given positiob
     *
     * @param animal
     *            Animal to be removed
     */
    void removeAnimal(Animal animal);
}