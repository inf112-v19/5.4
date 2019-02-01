package inf112.skeleton.app;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.Enum.Rotation;

public class Player implements IPlayer {
    private int health;
    private final int maxHealth;
    private Direction dir;
    private int xPos;
    private int yPos;
    // private Game game;

    /**
     * Constructs a Player object with position, direction and health
     */
    public Player(int xPos, int yPos, Direction dir, int health) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.health = health;
        this.maxHealth = health;
    }

    /**
     * The piece moves i times in the direction it's facing
     * @param i
     */
    @Override
    public void move(int i) {
        for (int j = 0; j < i; j++) {
            switch (this.dir) {
                case NORTH: this.yPos--; break;
                case EAST: this.xPos++; break;
                case SOUTH: this.yPos++; break;
                case WEST: this.xPos--; break;
            }
        }
    }

    /**
     * This piece loses 1 health
     */
    @Override
    public void takeDamage() {
        this.health--;
    }

    /**
     * Piece's health goes back to max
     */
    @Override
    public void repair() {
        this.health = this.maxHealth;
    }


    /**
     * The piece rotates in the designated direction
     * @param r
     */
    @Override
    public void rotate(Rotation r) {
        if (r == Rotation.R) {
            switch (this.dir) {
                case NORTH: this.dir = Direction.EAST; break;
                case EAST: this.dir = Direction.SOUTH; break;
                case SOUTH: this.dir = Direction.WEST; break;
                case WEST: this.dir = Direction.NORTH; break;
            }
        }
        else if (r == Rotation.L) {
            switch (this.dir) {
                case NORTH: this.dir = Direction.WEST; break;
                case EAST: this.dir = Direction.NORTH; break;
                case SOUTH: this.dir = Direction.EAST; break;
                case WEST: this.dir = Direction.SOUTH; break;
            }
        }
        else if (r == Rotation.U) {
            switch (this.dir) {
                case NORTH: this.dir = Direction.SOUTH; break;
                case EAST: this.dir = Direction.WEST; break;
                case SOUTH: this.dir = Direction.NORTH; break;
                case WEST: this.dir = Direction.EAST; break;
            }
        }
        else {
            throw new IllegalArgumentException("Not a valid rotation!");
        }
    }

    /**
     * @return This piece's current health
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * @return The direction this piece currently is facing
     */
    @Override
    public Direction getDirection() {
        return this.dir;
    }

    /**
     * @return This piece's current X position
     */
    @Override
    public int getXpos() {
        return this.xPos;
    }

    /**
     * @return This piece's current Y position
     */
    @Override
    public int getYpos() {
        return this.yPos;
    }

    /**
     * @return true if piece's health is above 0, otherwise false
     */
    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
}
