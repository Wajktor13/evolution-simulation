package agh.ics.oop;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final ArrayList<Animal> animalsList = new ArrayList<>();
    private final int refreshTime;
    private final int geneLength;

    public SimulationEngine(IWorldMap map, Vector2d[] initialPositions, int refreshTime, int animalEnergy, int geneLength){
        this.map = map;
        this.refreshTime = refreshTime;
        this.geneLength = geneLength;

        for (Vector2d position : initialPositions){
            createAnimal(position, animalEnergy);
        }
    }

    private void createAnimal(Vector2d position, int animalEnergy){
        Animal newAnimal = new Animal(position, animalEnergy, this.geneLength);
        animalsList.add(newAnimal);
        this.map.placeAnimal(newAnimal);
    }

    private void moveAnimals(){
        for (Animal animal : animalsList){
            System.out.println(animal.getPosition());
            animal.changeOrientation();
            animal.moveAnimal();
        }
    }
    @Override
    public void run() {
        // TODO
        // change so that it works like in the instruction
        // animals appear only on their initial spot
        // maybe observers
        for (int i = 0; i < geneLength; i++){
            System.out.println(map.toString());
            moveAnimals();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //Delete dead animals -> move animals -> eat plants -> reproduction -> grow plants
    }
}
