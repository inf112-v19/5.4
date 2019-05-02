package inf112.skeleton.app.gameLogic.enums;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public Direction oppositeDir() {
        switch (this) {
            case NORTH:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.NORTH;
            case EAST:
                return Direction.WEST;
            default:
                return Direction.EAST;
        }
    }
}
