package agh.ics.oop;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;


public class SimulationEngine implements IEngine {
    private final IWorldMap map;
    private ArrayList<Animal> animalsList = new ArrayList<>();
    private final int refreshTime;
    private final int geneLength;

    private final int minMutationCount;
    private final int maxMutationCount;

    public SimulationEngine(IWorldMap map, int refreshTime, int initialAnimals, int initialAnimalEnergy, int geneLength,
                            int minMutationCount, int maxMutationCount) {
        this.map = map;
        this.refreshTime = refreshTime;
        this.geneLength = geneLength;
        this.minMutationCount = minMutationCount;
        this.maxMutationCount = maxMutationCount;

        createAndPlaceInitialAnimals(initialAnimals, initialAnimalEnergy);
    }

    private void createAndPlaceInitialAnimals(int initialAnimals, int initialAnimalEnergy){
        ArrayList<Vector2d> freePositions = map.generateFreePositions();
        Random rand = new Random();
        Vector2d position;

        for (int i = 0; i < initialAnimals; i++){
            position = freePositions.get(rand.nextInt(freePositions.size()));
            freePositions.remove(position);
            this.createAndPlaceAnimal(position, initialAnimalEnergy);
        }
    }

    private void createAndPlaceAnimal(Vector2d position, int animalEnergy) {
        Animal newAnimal = new Animal(position, animalEnergy, this.geneLength, map);
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
        try {
            for (int i = 0; true; i++) {
                Thread.sleep(this.refreshTime);
                System.out.println(map);

                this.updateAnimals(-1, 1);
                this.eatPlantsAndReproduce();
                this.growPlants();

                if (animalsList.size() == 0) {
                    System.out.println(map);
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
