package pxlgen.core;

/**
 * Direction.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public enum Direction {

    NORTH(0, -1),
    NORTH_EAST(1, -1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, 1),
    SOUTH_WEST(-1, 1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    private int relX, relY;

    Direction(int x, int y) {
        relX = x;
        relY = y;
    }

    public int getRelX() {
        return relX;
    }

    public int getRelY() {
        return relY;
    }
}
