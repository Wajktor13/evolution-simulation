package agh.ics.oop;

public class World {

    public static void main(String args[]) {

        // Simulation parameters
        int mapHeight = 15;
        int mapWidth = 15;
        int mapType = 0;

        int plantsStartCount = 5;
        int plantEnergy = 5;
        int plantsDailyGrowCount = 1;
        int plantsGrowType = 0;

        int animalStartCount = 1;
        int animalStartEnergy = 100;
        int animalReadyForReproductionEnergy = 40;
        int animalReproductionLostEnergy = 10;
        int animalMoveType = 0;

        int childMinMutationCount = 0;
        int childMaxMutationCount = 10;
        int mutationType = 0;
        int geneLength = 10;

        int equatorHeight = Math.floorDiv(mapHeight, 3);

        int refreshTime = 1000;

        EquatorPlantsSpawner plantsSpawner = new EquatorPlantsSpawner(mapHeight, equatorHeight);
        SphericalWorldMap map = new SphericalWorldMap(mapWidth, mapHeight, plantsSpawner, plantsDailyGrowCount,
                plantEnergy, animalReadyForReproductionEnergy, animalReproductionLostEnergy);
        map.growPlants(plantsStartCount, plantEnergy);
        Vector2d[] initialPositions = {new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5),
                new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5),
                new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5)};
        SimulationEngine engine = new SimulationEngine(map, initialPositions, refreshTime, animalStartEnergy, geneLength, childMinMutationCount, childMaxMutationCount);

        engine.run();
    }
}
