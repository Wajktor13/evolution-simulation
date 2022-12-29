package agh.ics.oop.abstract_classes;

import agh.ics.oop.classes.Vector2d;
import agh.ics.oop.gui.IMapGuiElement;

public abstract class AbstractWorldMapElement  implements IMapGuiElement {
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
