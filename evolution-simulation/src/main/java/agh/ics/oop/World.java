package agh.ics.oop;

public class World {

    public static void main(String args[]) {

        // Simulation parameters
        int mapHeight = 15;
        int mapWidth = 15;
        int mapType = 0;

        int plantsStartCount = 10;
        int plantEnergy = 10;
        int plantsDailyGrowCount = 2;
        int plantsGrowType = 0;

        int animalStartCount = 1;
        int animalStartEnergy = 10;
        int animalReadyForReproductionEnergy = 60;
        int animalReproductionLostEnergy = 20;
        int animalMoveType = 0;

        int childMinMutationCount = 0;
        int childMaxMutationCount = 10;
        int mutationType = 0;
        int geneLength = 8;

        int equatorHeight = Math.floorDiv(mapHeight, 3);

        int refreshTime = 1200;

        EquatorPlantsSpawner plantsSpawner = new EquatorPlantsSpawner(mapHeight, equatorHeight);
        SphericalWorldMap map = new SphericalWorldMap(mapWidth, mapHeight, plantsSpawner, plantsDailyGrowCount,
                plantEnergy);
        map.growPlants(plantsStartCount, plantEnergy);
        Vector2d[] initialPositions = {new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5),
                new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5),
                new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5), new Vector2d(5, 5)};
        SimulationEngine engine = new SimulationEngine(map, initialPositions, refreshTime, animalStartEnergy, geneLength);

        engine.run();
    }
}
