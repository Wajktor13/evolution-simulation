package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

public class EquatorPlantsSpawner implements IPlantsSpawner {
    private int radiusY;
    private int centerY;
    private Random random = new Random();

    public EquatorPlantsSpawner(Vector2d lowerLeft, Vector2d upperRight, int equatorHeight) {
        this.radiusY = Math.floorDiv(equatorHeight, 2);
        this.centerY = Math.floorDiv(upperRight.y - lowerLeft.y, 2);
    }

    @Override
    public Vector2d generatePlantPosition(ArrayList<Vector2d> freePositions) {
        float selectArea = random.nextFloat();
        ArrayList<Vector2d> positionsToSelectFrom = new ArrayList<>();

        if (selectArea <= 0.2) {
            for (Vector2d position : freePositions) {
                if (!position.yBetween(new Vector2d(0, this.centerY - this.radiusY),
                        new Vector2d(0, this.centerY + radiusY))) {
                    positionsToSelectFrom.add(position);
                }
            }
        } else {
            for (Vector2d position : freePositions) {
                if (position.yBetween(new Vector2d(0, this.centerY - this.radiusY),
                        new Vector2d(0, this.centerY + radiusY))) {
                    positionsToSelectFrom.add(position);
                }
            }
        }

        return positionsToSelectFrom.get(random.nextInt(0, positionsToSelectFrom.size()));

    }
}
