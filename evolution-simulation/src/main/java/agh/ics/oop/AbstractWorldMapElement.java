package agh.ics.oop;

abstract class AbstractWorldMapElement {
    protected int energy;
    protected Vector2d position;

    public AbstractWorldMapElement(Vector2d position, int energy){
        this.position = position;
        this.energy = energy;
    }

    @Override
    public abstract String toString();

    public Vector2d getPosition(){
        return this.position;
    }

    public int getEnergy(){
        return this.energy;
    }
}
