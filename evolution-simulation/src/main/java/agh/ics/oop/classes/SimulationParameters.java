package agh.ics.oop.classes;

import agh.ics.oop.enums.AnimalsMoveVariant;
import agh.ics.oop.enums.MapVariant;
import agh.ics.oop.enums.MutationsVariant;
import agh.ics.oop.enums.PlantsGrowVariant;

public class SimulationParameters {

    public int mapHeight;
    public int mapWidth;
    public MapVariant mapVariant;

    public int initialPlants;
    public int plantEnergy;
    public int dailyPlantsGrow;
    public PlantsGrowVariant plantsGrowVariant;

    public int initialAnimals;
    public int initialAnimalEnergy;
    public int energyRequiredToReproduce;
    public int reproductionEnergyLoss;
    public AnimalsMoveVariant animalMoveVariant;

    public int minMutations;
    public int maxMutations;
    public MutationsVariant mutationVariant;
    public int geneLength;

    public int equatorHeight;

    public int dayDuration;

    public SimulationParameters(String mapHeight, String mapWidth, String mapVariant, String initialPlants,
                                String plantEnergy, String dailyPlantsGrow, String plantsGrowVariant,
                                String initialAnimals, String initialAnimalEnergy, String energyRequiredToReproduce,
                                String reproductionEnergyLoss, String animalMoveVariant, String minMutations,
                                String maxMutations, String mutationVariant, String geneLength, String dayDuration) throws Exception{

        /*
            validation?
         */
        try {
            this.mapHeight = Integer.parseInt(mapHeight);
            this.mapWidth = Integer.parseInt(mapWidth);
            this.initialPlants = Integer.parseInt(initialPlants);
            this.plantEnergy = Integer.parseInt(plantEnergy);
            this.dailyPlantsGrow = Integer.parseInt(dailyPlantsGrow);
            this.initialAnimals = Integer.parseInt(initialAnimals);
            this.initialAnimalEnergy = Integer.parseInt(initialAnimalEnergy);
            this.energyRequiredToReproduce = Integer.parseInt(energyRequiredToReproduce);
            this.reproductionEnergyLoss = Integer.parseInt(reproductionEnergyLoss);
            this.minMutations = Integer.parseInt(minMutations);
            this.maxMutations = Integer.parseInt(maxMutations);
            this.geneLength = Integer.parseInt(geneLength);
            this.dayDuration = Integer.parseInt(dayDuration);

            this.mapVariant = MapVariant.toMapVariant(mapVariant);
            this.plantsGrowVariant = PlantsGrowVariant.toPlantsGrowVariant(plantsGrowVariant);
            this.animalMoveVariant = AnimalsMoveVariant.toAnimalsMoveVariant(animalMoveVariant);
            this.mutationVariant = MutationsVariant.toMutationsVariant(mutationVariant);

            this.equatorHeight = Math.floorDiv(this.mapHeight, 3);
            if (this.mapHeight <= 0 || this.mapWidth <= 0){
                throw new TooSmallMapException();
            }
            else if (this.energyRequiredToReproduce < this.reproductionEnergyLoss){
                throw new EnergyException();
            }
            else if (this.minMutations > this.maxMutations){
                throw new MutationsException();
            }
        }
        catch (NumberFormatException e){
            throw e;
        }
    }

    public static SimulationParameters getPredefinedParameters1(){
        try {
            return new SimulationParameters("10", "10", "Spherical Earth", "5",
                    "5", "3", "Prefer Equator", "10", "25",
                    "10", "8", "Full Predestination", "1",
                    "4", "Fully Randomized", "8", "300");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static SimulationParameters getPredefinedParameters2(){
        try {
            return new SimulationParameters("20", "20", "Spherical Earth", "10",
                    "10", "5", "Prefer Equator", "15", "30",
                    "10", "6", "Full Predestination", "2",
                    "6", "Fully Randomized", "10", "600");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
