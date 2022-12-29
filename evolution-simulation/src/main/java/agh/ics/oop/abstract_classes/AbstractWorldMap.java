package agh.ics.oop.abstract_classes;

import agh.ics.oop.classes.Animal;
import agh.ics.oop.classes.Plant;
import agh.ics.oop.classes.RandomGene;
import agh.ics.oop.classes.Vector2d;
import agh.ics.oop.interfaces.IGene;
import agh.ics.oop.interfaces.IPlantsSpawner;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.*;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private Random plantRandom = new Random();
    private final int width;
    private final int height;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final Map<Vector2d, PriorityQueue<Animal>> animalsHashMap = new HashMap<>();
    private final Map<Vector2d, Plant> plantsHashMap = new HashMap<>();
    private IPlantsSpawner plantsSpawner;
    private ArrayList<Vector2d> freePositionsForPlants = new ArrayList<Vector2d>();
    private int plantsDailyGrow;
    private int plantsGrowEnergy;
    private final int animalReadyForReproductionEnergy;
    private final int animalReproductionLostEnergy;


    protected AbstractWorldMap(int width, int height, IPlantsSpawner plantsSpawner, int initialPlants,
                               int plantsDailyGrow, int plantsEnergy, int energyRequiredToReproduce,
                               int reproductionEnergyLoss) {
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.plantsSpawner = plantsSpawner;
        this.plantsDailyGrow = plantsDailyGrow;
        this.plantsGrowEnergy = plantsEnergy;
        this.animalReadyForReproductionEnergy = energyRequiredToReproduce;
        this.animalReproductionLostEnergy = reproductionEnergyLoss;
        this.freePositionsForPlants = generateFreePositions();
        this.growPlants(initialPlants, plantsEnergy);
    }

    public ArrayList<Vector2d> generateFreePositions() {
        ArrayList<Vector2d> freePositions = new ArrayList<>();

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                freePositions.add(new Vector2d(i, j));
            }
        }

        return freePositions;
    }

    public void growPlants(int numberOfPlantsToGrow, int plantEnergy) {
        for (int i = 0; i < numberOfPlantsToGrow; i++) {
            Vector2d newPosition = plantsSpawner.generatePlantPosition(freePositionsForPlants);

            if (newPosition == null) return;

            Plant toPlace = new Plant(newPosition, plantEnergy);
            placePlant(toPlace);
        }
    }

    @Override
    public abstract void normalizeAnimalState(Animal animal, Vector2d oldPosition);

    @Override
    public void placeAnimal(Animal animal) {
        Vector2d position = animal.getPosition();
        PriorityQueue<Animal> animalsAtPosition = this.animalsAt(position);

        if (animalsAtPosition == null) {

            /*
                if there is no set at the position create a new one
             */

            animalsAtPosition = new PriorityQueue<Animal>(Animal::animalsComparator);
            this.animalsHashMap.put(position, animalsAtPosition);
        }

        animalsAtPosition.add(animal);
    }

    @Override
    public void placePlant(Plant plant) {
        this.plantsHashMap.put(plant.getPosition(), plant);
        freePositionsForPlants.remove(plant.getPosition());
    }

    @Override
    public void removePlant(Plant plant){
        this.plantsHashMap.remove(plant.position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    @Override
    public PriorityQueue<Animal> animalsAt(Vector2d position) {
        return this.animalsHashMap.get(position);
    }

    @Override
    public Plant plantAt(Vector2d position) {
        return this.plantsHashMap.get(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        PriorityQueue<Animal> animalsAtPosition = this.animalsAt(position);

        if (animalsAtPosition != null && animalsAtPosition.peek() != null) {
            return animalsAtPosition.peek();
        } else {
            return this.plantAt(position);
        }
    }

    @Override
    public void removeAnimalFromPosition(Animal animal, Vector2d oldPosition) {
        PriorityQueue<Animal> animalsAtOldPosition = this.animalsHashMap.get(oldPosition);
        animalsAtOldPosition.remove(animal);

        /*

        if (animalsAtOldPosition.isEmpty()){
            this.animalsHashMap.remove(oldPosition);
        }

          if there is no animal in the set - delete the set?

         */
    }

    @Override
    public void animalPositionChanged(Animal animal, Vector2d oldPosition) {
        this.removeAnimalFromPosition(animal, oldPosition);
        this.placeAnimal(animal);
    }

    public Vector2d getLowerLeft() {
        return this.lowerLeft;
    }

    public Vector2d getUpperRight() {
        return this.upperRight;
    }

    public Map<Vector2d, PriorityQueue<Animal>> getAnimalsHashMap() {
        return this.animalsHashMap;
    }

    public Map<Vector2d, Plant> getPlantsHashMap() {
        return this.plantsHashMap;
    }

    @Override
    public int getPlantsDailyGrow(){
        return this.plantsDailyGrow;
    }

    @Override
    public int getPlantsGrowEnergy() {
        return this.plantsGrowEnergy;
    }

    @Override
    public ArrayList<Animal> updateFields() {
        ArrayList<Animal> animalsBorn = new ArrayList<>();
        for (Map.Entry<Vector2d, PriorityQueue<Animal>> entry : this.animalsHashMap.entrySet()){
            Vector2d position = entry.getKey();
            PriorityQueue<Animal> animalsAtPosition = entry.getValue();

            if (!animalsAtPosition.isEmpty()){

                Animal alfa = animalsAtPosition.poll();
                Animal beta = animalsAtPosition.poll(); // might be null
                Plant plant = this.plantAt(position);

                /*
                    eating plant
                 */
                if (plant != null){
                    /*
                        there are animals and a plant at the position
                     */

                    alfa.changeEnergy(plant.getEnergy());
                    this.removePlant(plant);
                    this.freePositionsForPlants.add(plant.position);

                }

                /*
                    reproduction
                 */
                if (beta != null){
                    /*
                        beta exists, perform reproduction
                     */
                    if (alfa.getEnergy() >= animalReadyForReproductionEnergy && beta.getEnergy() >= animalReadyForReproductionEnergy){
                        IGene alfaGene = alfa.getAnimalGene();
                        IGene betaGene = beta.getAnimalGene();

                        // take energy from alfa and beta
                        alfa.changeEnergy(-animalReproductionLostEnergy);
                        beta.changeEnergy(-animalReproductionLostEnergy);

                        // update their child counts
                        alfa.updateChildCounter(1);
                        beta.updateChildCounter(1);

                        // create the child on the same position but with animalReproductionLostEnergy * 2 (because it's taken from both parents)
                        Animal childAnimal = new Animal(position, animalReproductionLostEnergy * 2,
                                new RandomGene(alfa, beta), this, alfa.maxEnergy);
                        // update childAnimal min and max mutation count
                        // and then mutate it's Gene
                        childAnimal.getAnimalGene().updateMutationCount(alfaGene.getMinMutationCount(), alfaGene.getMaxMutationCount());
                        childAnimal.getAnimalGene().mutateGene();

                        placeAnimal(childAnimal);

                        animalsBorn.add(childAnimal);
                    }

                }

                animalsAtPosition.add(alfa);

                if (beta != null){
                    animalsAtPosition.add(beta);
                }

            }
        }
        return animalsBorn;
    }
}
