package agh.ics.oop;

enum MapDirection{
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public int toInt() {
        return switch (this) {
            case NORTH -> 0;
            case NORTHEAST -> 1;
            case EAST -> 2;
            case SOUTHEAST -> 3;
            case SOUTH -> 4;
            case SOUTHWEST -> 5;
            case WEST -> 6;
            case NORTHWEST -> 7;
        };
    }

    public static MapDirection toMapDirection(int orientation) {
        System.out.println(orientation);
        return switch (orientation) {
            case 0 -> NORTH;
            case 1 -> NORTHEAST ;
            case 2 -> EAST;
            case 3 -> SOUTHEAST;
            case 4 -> SOUTH;
            case 5 -> SOUTHWEST;
            case 6 -> WEST;
            case 7 -> NORTHWEST;
            default -> throw new IllegalStateException("Unexpected value: " + orientation);
        };
    }

    public MapDirection oppositeDirection(){
        return toMapDirection((toInt() + 4) % 8);
    }

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "N_";
            case NORTHEAST -> "NE";
            case EAST -> "E_";
            case SOUTHEAST -> "SE";
            case SOUTH -> "S_";
            case SOUTHWEST -> "SW";
            case WEST -> "W_";
            case NORTHWEST -> "NW";
        };
        //its this ugly because it would be uglier in the mapvisualizer xD
    }
}
