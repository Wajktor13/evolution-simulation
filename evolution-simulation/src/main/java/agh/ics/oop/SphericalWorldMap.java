package agh.ics.oop;

import java.util.Random;


public class SphericalWorldMap extends AbstractWorldMap{
    private Random plantRandom = new Random();
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
    public void placePlants(int plantsStartCount, int plantEnergy){
        for (int i = 0; i < plantsStartCount; i++){
            Vector2d newPosition = new Vector2d(plantRandom.nextInt(this.getLowerLeft().x, this.getUpperRight().x), plantRandom.nextInt(this.getLowerLeft().y, this.getUpperRight().y));
            Plant toPlace = new Plant(newPosition, plantEnergy);
            if (!isOccupied(newPosition)){
                placePlant(toPlace);
            }
        }
    }

    @Override
    public boolean animalCanMoveTo(Vector2d position) {
        return position.yBetween(getLowerLeft(), getUpperRight());
    }

    @Override
    public String toString() {
        return sphericalMapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
