package agh.ics.oop.classes;

import agh.ics.oop.enums.SimulationStatus;
import agh.ics.oop.gui.App;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;


public class SimulationEngine implements Runnable {
    public final IWorldMap map;
    private ArrayList<Animal> animalsList = new ArrayList<>();
    private final int refreshTime;
    private final int geneLength;
    private final GridPane grid;
    private final int minMutationCount;
    private final int maxMutationCount;
    private final int initialAnimalEnergy;
    private SimulationStatus simulationStatus = SimulationStatus.RUNNING;

    public SimulationEngine(IWorldMap map, int refreshTime, int initialAnimals, int initialAnimalEnergy, int geneLength,
                            int minMutationCount, int maxMutationCount, GridPane grid) {
        this.map = map;
        this.refreshTime = refreshTime;
        this.geneLength = geneLength;
        this.minMutationCount = minMutationCount;
        this.maxMutationCount = maxMutationCount;
        this.grid = grid;
        this.initialAnimalEnergy = initialAnimalEnergy;

        createAndPlaceInitialAnimals(initialAnimals, initialAnimalEnergy);
    }

    private void createAndPlaceInitialAnimals(int initialAnimals, int initialAnimalEnergy){
        ArrayList<Vector2d> freePositions = map.generateFreePositions();
        Random rand = new Random();
        Vector2d position;

        for (int i = 0; i < initialAnimals; i++){
            position = freePositions.get(rand.nextInt(freePositions.size()));
            this.createAndPlaceAnimal(position, initialAnimalEnergy);
        }
    }

    private void createAndPlaceAnimal(Vector2d position, int animalEnergy) {
        Animal newAnimal = new Animal(position, animalEnergy, this.geneLength, map, this.initialAnimalEnergy);
        newAnimal.getAnimalGene().updateMutationCount(this.minMutationCount, this.maxMutationCount);
        animalsList.add(newAnimal);
        this.map.placeAnimal(newAnimal);
    }

    private void updateAnimals(int energyChange, int ageChange) {
        ListIterator<Animal> iter = animalsList.listIterator();

        while (iter.hasNext()) {
            Animal animal = iter.next();

            animal.changeEnergy(energyChange);
            if (animal.getEnergy() < 0) {

                this.map.removeAnimalFromPosition(animal, animal.getPosition());
                iter.remove();
            } else {
                animal.changeAge(ageChange);
                animal.changeOrientation();
                animal.moveAnimal();
            }
        }
    }

    private void eatPlantsAndReproduce(){
        ArrayList<Animal> animalsBorn = this.map.updateFields();
        this.animalsList.addAll(animalsBorn);
    }

    private void growPlants(){
        this.map.growPlants(this.map.getPlantsDailyGrow(), this.map.getPlantsGrowEnergy());
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(this.refreshTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (this.simulationStatus == SimulationStatus.PAUSED) continue;

            this.updateAnimals(-1, 1);
            this.eatPlantsAndReproduce();
            this.growPlants();

            Platform.runLater(() -> {
                App.renderGrid(this.grid, this.map);
            });

        }
        while (animalsList.size() != 0);
    }

    public SimulationStatus getSimulationStatus(){
        return this.simulationStatus;
    }

    public void changeSimulationStatus(){
        if (this.simulationStatus == SimulationStatus.RUNNING){
            this.simulationStatus = SimulationStatus.PAUSED;
        } else {
            this.simulationStatus = SimulationStatus.RUNNING;
        }
    }
}
