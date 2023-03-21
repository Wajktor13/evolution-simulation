package agh.ics.oop.classes;

import agh.ics.oop.abstract_classes.AbstractWorldMap;
import agh.ics.oop.interfaces.IPlantsSpawner;


public class InfernalPortalWorldMap extends AbstractWorldMap {
    public MapVisualizer sphericalMapVisualizer;

    public InfernalPortalWorldMap(int width, int height, IPlantsSpawner plantsSpawner, int initialPlants,
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
            animal.changeEnergy(-this.getAnimalReproductionLostEnergy());

            animal.setPosition(new Vector2d((int)(Math.random() * (this.getUpperRight().getX() -
                    this.getLowerLeft().getX()) + this.getLowerLeft().getX()), (int)(Math.random() *
                    (this.getUpperRight().getY() - this.getLowerLeft().getY()) + this.getLowerLeft().getY())));
        }
    }

    @Override
    public boolean animalCanMoveTo(Vector2d position) {
        return position.yBetween(getLowerLeft(), getUpperRight()) && position.xBetween(getLowerLeft(), getUpperRight());
    }

    @Override
    public String toString() {
        return sphericalMapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
