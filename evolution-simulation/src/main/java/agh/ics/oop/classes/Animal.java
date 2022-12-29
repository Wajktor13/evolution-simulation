package agh.ics.oop.classes;

import agh.ics.oop.abstract_classes.AbstractWorldMapElement;
import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.interfaces.IGene;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import java.util.ArrayList;
import java.util.Random;


public class Animal extends AbstractWorldMapElement {
    private Random animalRandom = new Random();
    private MapDirection orientation;
    private int age = 0;
    private int childCounter = 0;
    public IGene animalGene;
    private final ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private IWorldMap map;


    public Animal(Vector2d position, int energy, int length, IWorldMap map) {
        super(position, energy);
        this.animalGene = new RandomGene(length);
        this.orientation = MapDirection.toMapDirection(animalGene.getNextOrientation());
        this.map = map;
        this.observers.add((IPositionChangeObserver) map);
    }

    public Animal(Vector2d position, int energy, IGene childGene, IWorldMap map){
        super(position, energy);
        this.animalGene = childGene;
        this.orientation = MapDirection.toMapDirection(animalGene.getNextOrientation());
        this.map = map;
        this.observers.add((IPositionChangeObserver) map);
    }

    public static int animalsComparator(Animal animal1, Animal animal2) {
        int energy1 = animal1.getEnergy();
        int energy2 = animal2.getEnergy();
        int age1 = animal1.getAge();
        int age2 = animal2.getAge();
        int childCounter1 = animal1.getChildCounter();
        int childCounter2 = animal2.getChildCounter();

        if (energy1 > energy2) {
            return -1;
        } else if (energy1 < energy2) {
            return 1;
        } else if (age1 > age2) {
            return -1;
        } else if (age1 < age2) {
            return 1;
        } else if (childCounter1 > childCounter2) {
            return -1;
        } else if (childCounter1 < childCounter2) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
//        return orientation.toString();
        return " " + this.energy;
    }

    public void changeEnergy(int diff) {
        this.energy += diff;
    }

    public void changeAge(int diff) {
        this.age += diff;
    }

    public void updateChildCounter(int diff){
        this.childCounter += diff;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public int getAge() {
        return this.age;
    }

    public int getChildCounter() {
        return this.childCounter;
    }

    public IGene getAnimalGene() {
        return animalGene;
    }

    public void changeOrientation() {
        this.setOrientation(MapDirection.toMapDirection((this.orientation.toInt() +
                animalGene.getNextOrientation()) % 8));
    }

    public void moveAnimal() {
        Vector2d oldPosition = this.position;

        Vector2d orientationVector = new Vector2d(0, 0);
        switch (this.orientation.toInt()) {
            case 0:
                orientationVector = new Vector2d(0, 1);
                break;
            case 1:
                orientationVector = new Vector2d(1, 1);
                break;
            case 2:
                orientationVector = new Vector2d(1, 0);
                break;
            case 3:
                orientationVector = new Vector2d(1, -1);
                break;
            case 4:
                orientationVector = new Vector2d(0, -1);
                break;
            case 5:
                orientationVector = new Vector2d(-1, -1);
                break;
            case 6:
                orientationVector = new Vector2d(-1, 0);
                break;
            case 7:
                orientationVector = new Vector2d(-1, 1);
                break;
        }

        this.setPosition(this.position.add(orientationVector));
        this.map.normalizeAnimalState(this, oldPosition);
        this.positionChanged(oldPosition);
    }

    public void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.animalPositionChanged(this, oldPosition);
        }
    }

    public void setPosition(Vector2d newPosition) {
        this.position = newPosition;
    }

    public void setOrientation(MapDirection newOrientation) {
        this.orientation = newOrientation;
    }

    @Override
    public String getImageUrl() {
        return "src/main/resources/animal.png";
    }
}
