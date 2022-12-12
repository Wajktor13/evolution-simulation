package agh.ics.oop;


public interface IPositionChangeObserver {

    /**
     * Changes the position of the given animal
     *
     * @param animal
     *            Animal that position has changed
     */
    void positionChanged(Animal animal);
}
