package agh.ics.oop;

public class World {

    public static void main(String args[]){
        EquatorPlantsSpawner plantsSpawner = new EquatorPlantsSpawner();
        SphericalWorldMap map = new SphericalWorldMap(10, 10, plantsSpawner);
        map.placePlant(new Plant(new Vector2d(7, 7)));
        map.placePlant(new Plant(new Vector2d(3, 9)));
        Vector2d[] initialPositions = {new Vector2d(1, 3), new Vector2d(8, 2)};
        SimulationEngine engine = new SimulationEngine(map, initialPositions, 1500);
        engine.run();
        System.out.println(new MapVisualizer(map).draw(map.getLowerLeft(), map.getUpperRight()));
        System.out.println(map.getAnimalsHashMap());
        System.out.println(map.getPlantsHashMap());
    }
}
