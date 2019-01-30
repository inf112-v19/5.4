package inf112.skeleton.app;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.Enum.Rotation;

public class Player implements IPlayer {
    private int health = 8;
    private Direction dir;

    @Override
    public void move(int i) {

    }

    @Override
    public void takeDamage() {
        this.health = health-1;
    }


    /**
     * The piece rotates in the designated direction
     * @param r
     */
    @Override
    public void rotate(Rotation r) {
        if (r == Rotation.R) {
            switch (this.dir) {
                case NORTH: this.dir = Direction.EAST;
                case EAST: this.dir = Direction.SOUTH;
                case SOUTH: this.dir = Direction.WEST;
                case WEST: this.dir = Direction.NORTH;
            }
        }
        else if (r == Rotation.L) {
            switch (this.dir) {
                case NORTH: this.dir = Direction.WEST;
                case EAST: this.dir = Direction.NORTH;
                case SOUTH: this.dir = Direction.EAST;
                case WEST: this.dir = Direction.SOUTH;
            }
        }
        else if (r == Rotation.U) {
            switch (this.dir) {
                case NORTH: this.dir = Direction.SOUTH;
                case EAST: this.dir = Direction.WEST;
                case SOUTH: this.dir = Direction.NORTH;
                case WEST: this.dir = Direction.EAST;
            }
        }
    }

    @Override
    public void fire() {

    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Direction direction() {
        return this.dir;
    }
}
