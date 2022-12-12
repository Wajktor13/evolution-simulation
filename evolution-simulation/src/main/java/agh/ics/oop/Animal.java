package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orientation;
    private int age = 0;
    private int childCounter = 0;

    public Animal(Vector2d position){
        super(position);
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
}
