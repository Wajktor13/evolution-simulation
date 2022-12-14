package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    private final int width;
    private final int height;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final Map<Vector2d, PriorityQueue<Animal>> animalsHashMap = new HashMap<>();
    private final Map<Vector2d, Plant> plantsHashMap = new HashMap<>();
    private IPlantsSpawner plantsSpawner;


    protected AbstractWorldMap(int width, int height, IPlantsSpawner plantsSpawner) {
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(width, height);
        this.plantsSpawner = plantsSpawner;
    }

    @Override
    public abstract void normalizeAnimalState(Animal animal, Vector2d oldPosition);

    @Override
    public void placeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        PriorityQueue<Animal> setAtPosition = this.animalsAt(position);

        if (setAtPosition == null){

            /*
             * if there is no set at the position create a new one
             */

            setAtPosition = new PriorityQueue<Animal>(Animal::animalsComparator);
            this.animalsHashMap.put(position, setAtPosition);
        }

        setAtPosition.add(animal);
    }

    @Override
    public void placePlant(Plant plant) {
        this.plantsHashMap.put(plant.getPosition(), plant);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    @Override
    public PriorityQueue<Animal> animalsAt(Vector2d position){
        return this.animalsHashMap.get(position);
    }

    @Override
    public Plant plantAt(Vector2d position) {
        return this.plantsHashMap.get(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        PriorityQueue<Animal> animalsAtPosition = this.animalsAt(position);

        if (animalsAtPosition != null && animalsAtPosition.peek() != null){
            return animalsAtPosition.peek();
        } else {
            return this.plantAt(position);
        }
    }

    @Override
    public void removeAnimalFromPosition(Animal animal, Vector2d oldPosition) {
        this.animalsHashMap.get(oldPosition).remove(animal);

        /*
         * if there is no animal in the set - delete the set?
         */
    }

    @Override
    public void animalPositionChanged(Animal animal, Vector2d oldPosition) {
        this.removeAnimalFromPosition(animal, oldPosition);
        this.placeAnimal(animal);
    }

    public Vector2d getLowerLeft(){
        return this.lowerLeft;
    }

    public Vector2d getUpperRight(){
        return this.upperRight;
    }

    public Map<Vector2d, PriorityQueue<Animal>> getAnimalsHashMap(){
        return this.animalsHashMap;
    }

    public Map<Vector2d, Plant> getPlantsHashMap(){
        return this.plantsHashMap;
    }
}
