package agh.ics.oop;


public class SphericalWorldMap extends AbstractWorldMap{


    public SphericalWorldMap(int width, int height, IPlantsSpawner plantsSpawner){
        super(width, height, plantsSpawner);
    }

    @Override
    public void normalizeAnimalState(Animal animal, Vector2d newPosition){

    }

    @Override
    public boolean animalCanMoveTo(Vector2d position) {
        return true;
    }
}
