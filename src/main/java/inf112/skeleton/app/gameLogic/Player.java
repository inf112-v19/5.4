package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.GUI.player.MovableRobot;
import inf112.skeleton.app.gameLogic.enums.*;
import inf112.skeleton.app.GUI.player.Position;

import java.util.List;
import java.util.Stack;


public class Player implements IPlayer {
    private int health;
    private final int maxHealth;
    private int damageTokens;
    private Direction dir;
    private Position pos;
    private Stack<ProgramCard> playerDeck;
    private List<ProgramCard> playerRegister;
    private MovableRobot robot;



    /**
     * Constructs a player object with position, direction and health
     */
    public Player(Position pos, Direction dir, int health) {
        this.pos = pos;
        this.dir = dir;
        this.health = health;
        this.maxHealth = health;
        this.damageTokens = 0;
        this.robot = robot;
    }

    @Override
    public void addProgramCard(ProgramCard programCard) {
        this.playerDeck.push(programCard);
    }

    public Stack<ProgramCard> returnDeck() {
        return playerDeck;
    }

    /**
     * TEST VERSION NOT FINAL
     * @param att
     */
    public void doAction(ActionType att){
        switch (att){
            case MOVE:
                this.move(1);
                break;
            case ROTATE:
                this.rotate(Rotation.R);
        }
    }

    /**
     * The piece moves i times in the direction it's facing
     * @param steps
     */
    @Override
    public void move(int steps) {
        for (int j = 0; j < steps; j++) {
            switch (this.dir) {
                case NORTH: this.pos = this.pos.north(); break;
                case EAST: this.pos = this.pos.east(); break;
                case SOUTH: this.pos = this.pos.south(); break;
                case WEST: this.pos = this.pos.west(); break;
            }
        }
        if (steps == -1) {
            switch (this.dir) {
                case NORTH: this.pos = this.pos.south(); break;
                case EAST: this.pos = this.pos.west(); break;
                case SOUTH: this.pos = this.pos.north(); break;
                case WEST: this.pos = this.pos.east(); break;
            }
        }
        robot.doAction(ActionType.MOVE, this.dir);
    }

    /**
     * Player gets one damageToken
     */
    @Override
    public void takeDamage(int amountOfDamage) {
        if(damageTokens + amountOfDamage < 10){
            damageTokens+=amountOfDamage;
        } else {
            damageTokens = 0;
            health--;
        }
    }

    /**
     * Player loses a damageToken
     */
    @Override
    public void repair() {
        if(damageTokens > 0){
            damageTokens--;
        }
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
        robot.doAction(ActionType.ROTATE, dir);
    }

    /**
     * @return This piece's current health
     */
    @Override
    public int getHealth() {
        return this.health;
    }


    /**
     * @return This piece's current amount of damageTokens
     */
    @Override
    public int getDamageTokens() {
        return damageTokens;
    }

    /**
     * @return The direction this piece currently is facing
     */
    @Override
    public Direction getDirection() {
        return this.dir;
    }

    @Override
    public Position getPos() {
        return this.pos;
    }

    /**
     * @return true if piece's health is above 0, otherwise false
     */
    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    public void setRobot(MovableRobot hans){
        this.robot = hans;
    }

}
