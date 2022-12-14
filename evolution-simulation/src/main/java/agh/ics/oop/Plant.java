package agh.ics.oop;

public class Plant extends AbstractWorldMapElement{

    public Plant(Vector2d position, int energy){
        super(position, energy);
    }

    @Override
    public String toString() {
        return " P";
    }
}
