package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final ArrayList<Animal> animalsList = new ArrayList<>();
    private final int refreshTime;

    public SimulationEngine(IWorldMap map, Vector2d[] initialPositions, int refreshTime ){
        this.map = map;
        this.refreshTime = refreshTime;

        for (Vector2d position : initialPositions){
            createAnimal(position);
        }
    }

    private void createAnimal(Vector2d position){
        Animal newAnimal = new Animal(position);
        animalsList.add(newAnimal);
        this.map.placeAnimal(newAnimal);
    }

    @Override
    public void run() {
        //Delete dead animals -> move animals -> eat plants -> reproduction -> grow plants
    }
}
