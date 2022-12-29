package agh.ics.oop.interfaces;

import agh.ics.oop.classes.Animal;
import agh.ics.oop.classes.Plant;
import agh.ics.oop.classes.Vector2d;

import java.util.ArrayList;
import java.util.PriorityQueue;


public interface IWorldMap {

    /**
     * Indicate if animal can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean animalCanMoveTo(Vector2d position);

    /**
     * Return true if given position on the map is occupied
     *
     * @param position
     *             position to check.
     * @return True if the position is occupied.
     */

    boolean isOccupied(Vector2d position);

    /**
     * Places an animal on the map.
     *
     * @param animal
     *            The animal to be placed on the map.
     */
    void placeAnimal(Animal animal);

    /**
     * Places a plant on the map.
     *
     * @param plant
     *            The plant to be placed on the map.
     */
    void placePlant(Plant plant);

    /**
     * Remove plant from the map.
     *
     * @param plant
     *            Plant to be removed.
     */
     void removePlant(Plant plant);

    /**
     * Place numberOfPlantsToGrow plants one the map.
     *
     * @param numberOfPlantsToGrow
     *            ...
     * @param plantEnergy
     *          ...
     */
    void growPlants(int numberOfPlantsToGrow, int plantEnergy);

    /**
     * Returns object at the given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     * Priority: animal with the highest energy etc.
     */
    Object objectAt(Vector2d position);


    /**
     * Returns animals at the given position.
     *
     * @param position
     *            The position of the animals.
     * @return TreeSet of the animals occupying the given position.
     */
    PriorityQueue<Animal> animalsAt(Vector2d position);

    /**
     * Returns plant at the given position.
     *
     * @param position
     *            The position to be checked.
     * @return plant if a plant is at the give position, null otherwise.
     */
    Plant plantAt(Vector2d position);

    /**
     * Normalizes energy, position and direction of the animal
     *
     * @param animal
     *            Animal to be normalized
     */
    void normalizeAnimalState(Animal animal, Vector2d oldPosition);

    /**
     * Removes the animal from the given positiob
     *
     * @param animal
     *            Animal to be removed
     */
    void removeAnimalFromPosition(Animal animal, Vector2d oldPosition);

    /**
     * For each position: eat plants, then reproduce
     */
    ArrayList<Animal> updateFields();

    Vector2d getLowerLeft();

    Vector2d getUpperRight();

    int getPlantsDailyGrow();

    int getPlantsGrowEnergy();

    public ArrayList<Vector2d> generateFreePositions();
}