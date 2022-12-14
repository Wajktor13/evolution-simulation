package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;



public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    private final int width;
    private final int height;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final Map<Vector2d, TreeSet<Animal>> animalsHashMap = new HashMap<>();
    private final Map<Vector2d, Plant> plantsHashMap = new HashMap<>();
    private IPlantsSpawner plantsSpawner;


    protected AbstractWorldMap(int width, int height, IPlantsSpawner plantsSpawner) {
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(width, height);
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

            setAtPosition = new TreeSet<Animal>(this::animalsComparator);
            this.animalsHashMap.put(position, setAtPosition);
        }

        setAtPosition.add(animal);
    }

    @Override
    public void placePlant(Plant plant) {
        this.plantsHashMap.put(plant.getPosition(), plant);
    }

    private int animalsComparator(Animal animal1, Animal animal2){
        int energy1 = animal1.getEnergy();
        int energy2 = animal2.getEnergy();
        int age1 = animal1.getAge();
        int age2 = animal2.getAge();
        int childCounter1 = animal1.getChildCounter();
        int childCounter2 = animal2.getChildCounter();
        
        if (energy1 > energy2){
            return -1;
        } else if (energy1 < energy2) {
            return 1;
        } else if (age1 > age2) {
            return -1;
        } else if (age1 < age2) {
            return 1;
        } else if (childCounter1 > childCounter2){
            return -1;
        } else if (childCounter1 < childCounter2) {
            return 1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    @Override
    public TreeSet<Animal> animalsAt(Vector2d position){
        return this.animalsHashMap.get(position);
    }

    @Override
    public Plant plantAt(Vector2d position) {
        return this.plantsHashMap.get(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        TreeSet<Animal> animalsAtPosition = this.animalsAt(position);

        if (animalsAtPosition != null && animalsAtPosition.first() != null){
            return animalsAtPosition.first();
        } else {
            return this.plantAt(position);
        }
    }

    @Override
    public void removeAnimal(Animal animal) {
        Vector2d position = animal.getPosition();
        this.animalsHashMap.get(position).remove(animal);

        /*
         * if there is no animal in the set - delete the set?
         */
    }

    @Override
    public void positionChanged(Animal animal) {

    }

    public Vector2d getLowerLeft(){
        return this.lowerLeft;
    }

    public Vector2d getUpperRight(){
        return this.upperRight;
    }

    public Map<Vector2d, TreeSet<Animal>> getAnimalsHashMap(){
        return this.animalsHashMap;
    }

    public Map<Vector2d, Plant> getPlantsHashMap(){
        return this.plantsHashMap;
    }
}
