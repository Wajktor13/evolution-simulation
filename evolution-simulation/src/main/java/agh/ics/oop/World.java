package agh.ics.oop;

public class World {

    public static void main(String args[]){

        // Simulation parameters
        int mapHeight = 10;
        int mapWidth = 10;
        int mapType = 0;

        int plantsStartCount = 20;
        int plantEnergy = 10;
        int plantsDailyGrowCount = 3;
        int plantsGrowType = 0;

        int animalStartCount = 2;
        int animalStartEnergy = 9;
        int animalReadyForReproductionEnergy = 60;
        int animalReproductionLostEnergy = 20;
        int animalMoveType = 0;

        int childMinMutationCount = 0;
        int childMaxMutationCount = 10;
        int mutationType = 0;
        int geneLength = 6;

        int equatorHeight = Math.floorDiv(mapHeight, 3);

        int refreshTime = 1500;

        EquatorPlantsSpawner plantsSpawner = new EquatorPlantsSpawner(mapWidth, equatorHeight);
        SphericalWorldMap map = new SphericalWorldMap(mapWidth, mapHeight, plantsSpawner);
        map.placePlants(plantsStartCount, plantEnergy);
        Vector2d[] initialPositions = {new Vector2d(5, 5), new Vector2d(4, 5), new Vector2d(3, 2), new Vector2d(5, 6),
                new Vector2d(4, 4), new Vector2d(2, 4), new Vector2d(4, 3)};
        SimulationEngine engine = new SimulationEngine(map, initialPositions, refreshTime, animalStartEnergy, geneLength);

        engine.run();
    }
}
