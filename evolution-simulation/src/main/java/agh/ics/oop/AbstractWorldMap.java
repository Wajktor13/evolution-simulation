package agh.ics.oop;

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

    protected AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.UpperRight = new Vector2d(width, height);
    }

    @Override
    public abstract void place(Animal animal);

    @Override
    public abstract void normalizeAnimalState(Animal animal, Vector2d newPosition);

    @Override
    public TreeSet<Animal> animalsAt(Vector2d position){
        return this.animalsHashMap.get(position);
    }

    @Override
    public void positionChanged(Animal animal) {

    }
}
