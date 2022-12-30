package agh.ics.oop.classes;

import agh.ics.oop.enums.SimulationStatus;
import agh.ics.oop.gui.App;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Platform;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
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
    private ArrayList<XYChart.Series<Number, Number>> chartSeriesArray;
    private final int minMutationCount;
    private final int maxMutationCount;
    private final int initialAnimalEnergy;
    private int daysPassed;
    private int totalDaysLived;
    private int deadAnimalsCount;
    private SimulationStatus simulationStatus = SimulationStatus.RUNNING;

    public SimulationEngine(IWorldMap map, int refreshTime, int initialAnimals, int initialAnimalEnergy, int geneLength,
                            int minMutationCount, int maxMutationCount, GridPane grid, ArrayList<XYChart.Series<Number, Number>> chartSeriesArray) {
        this.map = map;
        this.refreshTime = refreshTime;
        this.geneLength = geneLength;
        this.minMutationCount = minMutationCount;
        this.maxMutationCount = maxMutationCount;
        this.grid = grid;
        this.initialAnimalEnergy = initialAnimalEnergy;
        this.daysPassed = 0;
        this.chartSeriesArray = chartSeriesArray;
        this.totalDaysLived = 0;
        this.deadAnimalsCount = 0;
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
        this.daysPassed += ageChange;
        while (iter.hasNext()) {
            Animal animal = iter.next();

            animal.changeEnergy(energyChange);
            if (animal.getEnergy() < 0) {
                this.totalDaysLived += animal.getAge();
                this.deadAnimalsCount += 1;
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

    private double getAvgEnergy(){
        int totalEnergy = 0;
        int animalsCount = 0;
        for (Animal a: animalsList){
            totalEnergy += a.getEnergy();
            animalsCount += 1;
        }
        return (totalEnergy * 1.0) / animalsCount;
    }

    private double getAvgChildrenCount(){
        int totalChildrenCount = 0;
        int animalsCount = 0;
        for (Animal a: animalsList){
            totalChildrenCount += a.getChildCounter();
            animalsCount += 1;
        }
        return (totalChildrenCount * 1.0) / animalsCount;
    }

    private double getAvgLifespan(){
        return (this.totalDaysLived * 1.0)/this.deadAnimalsCount;
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
                App.renderCharts(this.chartSeriesArray, this.daysPassed, animalsList.size(),
                        this.map.getTotalPlantCount(), getAvgEnergy(), getAvgChildrenCount(), getAvgLifespan());
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
