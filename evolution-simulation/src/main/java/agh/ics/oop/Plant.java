package agh.ics.oop;

public class Plant extends AbstractWorldMapElement{

    public Plant(Vector2d position){
        super(position);
    }

    @Override
    public String toString() {
        return "p";
    }
}
