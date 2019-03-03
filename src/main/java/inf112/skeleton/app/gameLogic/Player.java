package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.GUI.player.MovableRobot;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.Rotation;

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
    private int playerDeckSize;

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
        this.playerDeck = new Stack<>();
        this.playerDeckSize = 0;
    }

    @Override
    public void addProgramCard(ProgramCard programCard) {
        playerDeck.add(programCard);
        playerDeckSize++;
    }

    public Stack<ProgramCard> returnDeck() {
        return playerDeck;
    }

    public int getPlayerDeckSize() {
        return playerDeckSize;
    }

    public boolean playerDeckIsEmpty() {
        return playerDeck.isEmpty();
    }

    public ProgramCard getPlayerCard() {
        playerDeckSize--;
        return playerDeck.pop();
    }

    /**
     * TEST VERSION NOT FINAL
     * @param att
     */
    public void doAction(ActionType att){
        switch (att){
            case MOVE:
                this.move(dir);
                break;
            case ROTATE:
                this.rotate(Rotation.R);
        }
    }

    /**
     * @param dir The direction the piece should move
     */
    @Override
    public void move(Direction dir) {
            switch (dir) {
                case NORTH: this.pos = this.pos.north(); break;
                case EAST: this.pos = this.pos.east(); break;
                case SOUTH: this.pos = this.pos.south(); break;
                case WEST: this.pos = this.pos.west(); break;
            }
        robot.doAction(ActionType.MOVE, dir);
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
                case NORTH:
                    this.dir = Direction.EAST;
                    break;
                case EAST:
                    this.dir = Direction.SOUTH;
                    break;
                case SOUTH:
                    this.dir = Direction.WEST;
                    break;
                case WEST:
                    this.dir = Direction.NORTH;
                    break;
            }
        }
        else if (r == Rotation.L) {
            switch (this.dir) {
                case NORTH:
                    this.dir = Direction.WEST;
                    break;
                case EAST:
                    this.dir = Direction.NORTH;
                    break;
                case SOUTH:
                    this.dir = Direction.EAST;
                    break;
                case WEST:
                    this.dir = Direction.SOUTH;
                    break;
            }
        }
        else if (r == Rotation.U) {
            switch (this.dir) {
                case NORTH:
                    this.dir = Direction.SOUTH;
                    break;
                case EAST:
                    this.dir = Direction.WEST;
                    break;
                case SOUTH:
                    this.dir = Direction.NORTH;
                    break;
                case WEST:
                    this.dir = Direction.EAST;
                    break;
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

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public char getSymbol() {
        return 0;
    }

    @Override
    public Direction getRotation() {
        return null;
    }
}
