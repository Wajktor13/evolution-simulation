package agh.ics.oop;

import agh.ics.oop.enums.AnimalsMoveVariant;
import agh.ics.oop.enums.MapVariant;
import agh.ics.oop.enums.MutationsVariant;
import agh.ics.oop.enums.PlantsGrowVariant;

public class SimulationParameters {

    public final int mapHeight;
    public final int mapWidth;
    public final MapVariant mapVariant;

    public final int initialPlants;
    public final int plantEnergy;
    public final int dailyPlantsGrow;
    public final PlantsGrowVariant plantsGrowVariant;

    public final int initialAnimals;
    public final int initialAnimalEnergy;
    public final int energyRequiredToReproduce;
    public final int reproductionEnergyLoss;
    public final AnimalsMoveVariant animalMoveVariant;

    public final int minMutations;
    public final int maxMutations;
    public final MutationsVariant mutationVariant;
    public final int geneLength;

    public final int equatorHeight;

    public final int dayDuration;

    public SimulationParameters(String mapHeight, String mapWidth, String mapVariant, String initialPlants,
                                String plantEnergy, String dailyPlantsGrow, String plantsGrowVariant,
                                String initialAnimals, String initialAnimalEnergy, String energyRequiredToReproduce,
                                String reproductionEnergyLoss, String animalMoveVariant, String minMutations,
                                String maxMutations, String mutationVariant, String geneLength, String dayDuration){

        /*
            validation?
         */

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
    }

    public static SimulationParameters getPredefinedParameters1(){
        return new SimulationParameters("10", "10", "Spherical Earth", "5",
                "5", "3", "Prefer Equator", "10", "20",
                "10", "3", "Full Predestination", "1",
                "4", "Fully Randomized", "8", "400");
    }

    public static SimulationParameters getPredefinedParameters2(){
        return new SimulationParameters("20", "20", "Spherical Earth", "10",
                "10", "5", "Prefer Equator", "25", "25",
                "5", "2", "Full Predestination", "2",
                "6", "Fully Randomized", "10", "800");
    }
}
