package agh.ics.oop;

public class SphericalWorldMap extends AbstractWorldMap{
    public MapVisualizer sphericalMapVisualizer;


    public SphericalWorldMap(int width, int height, IPlantsSpawner plantsSpawner){
        super(width, height, plantsSpawner);
        sphericalMapVisualizer = new MapVisualizer(this);
    }

    @Override
    public void normalizeAnimalState(Animal animal, Vector2d oldPosition){
        Vector2d newPosition = animal.getPosition();

        if (!this.animalCanMoveTo(newPosition)){
            animal.setPosition(oldPosition);
            animal.setOrientation(animal.getOrientation().oppositeDirection());

        } else if (newPosition.hasLowerX(this.getLowerLeft())) {
            animal.setPosition(new Vector2d(this.getUpperRight().x, newPosition.getY()));

        } else if (newPosition.hasHigherX(this.getUpperRight())) {
            animal.setPosition(new Vector2d(this.getLowerLeft().x, newPosition.getY()));
        }
    }

    /*
     needs to be changed
     plants spawning logic should be in a class that implements IPlantsSpawner
     */

    @Override
    public boolean animalCanMoveTo(Vector2d position) {
        return position.yBetween(getLowerLeft(), getUpperRight());
    }

    @Override
    public String toString() {
        return sphericalMapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
