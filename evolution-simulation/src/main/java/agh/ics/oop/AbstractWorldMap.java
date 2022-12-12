package agh.ics.oop;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    private final int width;
    private final int height;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d UpperRight;
    protected final Map<Vector2d, TreeSet<Animal>> animalsHashMap = new HashMap<>();
    private final Map<Vector2d, Plant> grassHashMap = new HashMap<>();
    private IPlantsSpawner plantsSpawner;


    protected AbstractWorldMap(int width, int height, IPlantsSpawner plantsSpawner) {
        this.width = width;
        this.height = height;
        this.UpperRight = new Vector2d(width, height);
        this.plantsSpawner = plantsSpawner;
    }

    @Override
    public abstract void normalizeAnimalState(Animal animal, Vector2d newPosition);

    @Override
    public void placeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        TreeSet<Animal> setAtPosition = this.animalsAt(position);

        if (setAtPosition == null){

            /*
             * if there is no set at the position create a new one
             *
             *
             * comparator needs to be changed
             */

            setAtPosition = new TreeSet<Animal>(Comparator.comparingInt(Animal::getEnergy).reversed());
        }

        setAtPosition.add(animal);
    }

    @Override
    public TreeSet<Animal> animalsAt(Vector2d position){
        return this.animalsHashMap.get(position);
    }

    @Override
    public void removeAnimal(Animal animal) {
        Vector2d position = animal.getPosition();
        this.animalsHashMap.get(position).remove(animal);
    }

    @Override
    public void positionChanged(Animal animal) {

    }
}
