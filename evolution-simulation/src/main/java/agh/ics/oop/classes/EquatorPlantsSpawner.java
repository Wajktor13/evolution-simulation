package agh.ics.oop.classes;

import agh.ics.oop.interfaces.IPlantsSpawner;

import java.util.ArrayList;
import java.util.Random;

public class EquatorPlantsSpawner implements IPlantsSpawner {
    private int radiusY;
    private int centerY;
    private Random random = new Random();

    public EquatorPlantsSpawner(int mapHeight, int equatorHeight) {
        this.radiusY = Math.floorDiv(equatorHeight, 2);
        this.centerY = Math.floorDiv(mapHeight, 2);
    }

    @Override
    public Vector2d generatePlantPosition(ArrayList<Vector2d> freePositions) {
        float randomFloat = random.nextFloat();
        ArrayList<Vector2d> onEquator = new ArrayList<>();
        ArrayList<Vector2d> outsideEquator = new ArrayList<>();

        for (Vector2d position : freePositions) {
            if (position.yBetween(new Vector2d(0, this.centerY - this.radiusY),
                    new Vector2d(0, this.centerY + radiusY))) {
                onEquator.add(position);
            } else {
                outsideEquator.add(position);
            }
        }

        /*
            If randomFloat <= 0.2 and there are free position outside the equator -> select a position
             outside the equator
            else if there are free position on the equator -> select a position from the equator
            else null
         */

        if ((randomFloat <= 0.2 || onEquator.size() == 0) && outsideEquator.size() > 0) {
            return outsideEquator.get(random.nextInt(0, outsideEquator.size()));

        } else if (onEquator.size() > 0) {
            return onEquator.get(random.nextInt(0, onEquator.size()));
        }

        return null;
    }
}
