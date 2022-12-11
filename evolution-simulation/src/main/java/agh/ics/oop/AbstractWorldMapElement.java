package agh.ics.oop;

abstract class AbstractWorldMapElement {
    protected Vector2d position;
    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
