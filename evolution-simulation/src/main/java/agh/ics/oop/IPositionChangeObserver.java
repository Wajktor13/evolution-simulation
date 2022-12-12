package agh.ics.oop;


public interface IPositionChangeObserver {

    /**
     * Changes the position of the given animal
     * If no animals are on position - delete TreeSet?
     *
     *
     * @param animal
     *            Animal that position has changed
     */
    void positionChanged(Animal animal);
}
