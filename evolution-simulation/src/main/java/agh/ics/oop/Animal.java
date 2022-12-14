package agh.ics.oop;

import java.util.Random;

public class Animal extends AbstractWorldMapElement{
    private Random animalRandom = new Random();
    private MapDirection orientation;
    private int age = 0;
    private int childCounter = 0;
    public Gene animalGene;


    public Animal(Vector2d position, int energy, int length){
        super(position, energy);
        this.animalGene = new Gene(length);
        this.orientation = MapDirection.toMapDirection(animalGene.getNextOrientation());
        System.out.println(animalGene.getGene());
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }

    public int getAge(){
        return this.age;
    }

    public int getChildCounter(){
        return this.childCounter;
    }

    public void changeOrientation(){
        this.orientation = MapDirection.toMapDirection( (this.orientation.toInt() + animalGene.getNextOrientation()) % 8);
    }

    public void moveAnimal(){
        Vector2d orientationVector = new Vector2d(0, 0);
        switch (this.orientation.toInt()){
            case 0:
                orientationVector = new Vector2d(0, 1);
                break;
            case 1: orientationVector = new Vector2d(1, 1);
                break;
            case 2: orientationVector = new Vector2d(1, 0);
                break;
            case 3: orientationVector = new Vector2d(1, -1);
                break;
            case 4: orientationVector = new Vector2d(0, -1);
                break;
            case 5: orientationVector = new Vector2d(-1, -1);
                break;
            case 6: orientationVector = new Vector2d(-1, 0);
                break;
            case 7: orientationVector = new Vector2d(-1, 1);
                break;
        }
        this.position = this.position.add(orientationVector);
    }
}
