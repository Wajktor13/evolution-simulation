package agh.ics.oop.classes;

import agh.ics.oop.abstract_classes.AbstractWorldMap;
import agh.ics.oop.interfaces.IPlantsSpawner;

public class SphericalWorldMap extends AbstractWorldMap {
    public MapVisualizer sphericalMapVisualizer;


    public SphericalWorldMap(int width, int height, IPlantsSpawner plantsSpawner, int initialPlants,
                             int plantsDailyGrow, int plantsEnergy, int animalReadyForReproductionEnergy,
                             int animalReproductionLostEnergy) {
        super(width, height, plantsSpawner, initialPlants, plantsDailyGrow, plantsEnergy,
                animalReadyForReproductionEnergy, animalReproductionLostEnergy);
        sphericalMapVisualizer = new MapVisualizer(this);
    }

    @Override
    public void normalizeAnimalState(Animal animal, Vector2d oldPosition) {
        Vector2d newPosition = animal.getPosition();

        if (!this.animalCanMoveTo(newPosition)) {
            animal.setPosition(oldPosition);
            animal.setOrientation(animal.getOrientation().oppositeDirection());

        } else if (newPosition.hasLowerX(this.getLowerLeft())) {
            animal.setPosition(new Vector2d(this.getUpperRight().x, newPosition.getY()));

        } else if (newPosition.hasHigherX(this.getUpperRight())) {
            animal.setPosition(new Vector2d(this.getLowerLeft().x, newPosition.getY()));
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
