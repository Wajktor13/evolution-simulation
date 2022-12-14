package agh.ics.oop;

public class World {

    public static void main(String args[]){

        // Simulation parameters
        int mapHeight = 10;
        int mapWidth = 10;
        int mapType = 0;

        int plantsStartCount = 3;
        int plantEnergy = 10;
        int plantsDailyGrowCount = 3;
        int plantsGrowType = 0;

        int animalStartCount = 2;
        int animalStartEnergy = 100;
        int animalReadyForReproductionEnergy = 60;
        int animalReproductionLostEnergy = 20;
        int animalMoveType = 0;

        int childMinMutationCount = 0;
        int childMaxMutationCount = 10;
        int mutationType = 0;
        int geneLength = 5;

        EquatorPlantsSpawner plantsSpawner = new EquatorPlantsSpawner();
        SphericalWorldMap map = new SphericalWorldMap(mapWidth, mapHeight, plantsSpawner);
        map.placePlants(plantsStartCount, plantEnergy);
        Vector2d[] initialPositions = {new Vector2d(1, 3), new Vector2d(8, 2)};
        SimulationEngine engine = new SimulationEngine(map, initialPositions, 1500, animalStartEnergy, geneLength);
        engine.run();
        System.out.println(map.getAnimalsHashMap());
        System.out.println(map.getPlantsHashMap());
    }
}
