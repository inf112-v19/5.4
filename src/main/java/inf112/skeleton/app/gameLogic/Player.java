package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.GUIRobot;
import inf112.skeleton.app.GUI.player.MovableGUIRobot;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.Rotation;

import java.util.List;
import java.util.Stack;


public class Player implements IPlayer {
    private int health;
    private final int maxHealth;
    private int damageTokens;
    private Direction facingDir;
    private Position pos;
    private Stack<ProgramCard> playerDeck;
    private List<ProgramCard> playerRegister;
    private MovableGUIRobot robot;
    private Position respawnPoint;

    private Board board;


    /**
     * Constructs a player object with position, direction and health
     */
    public Player(Position pos, Direction dir, int health, Board board) {
        this.pos = pos;
        this.facingDir = dir;
        this.health = health;
        this.maxHealth = health;
        this.damageTokens = 0;
        this.board = board;
        //Kommenter ut linjen under for at testene skal kjøre
        //this.robot = new MovableGUIRobot(1);
        this.respawnPoint = pos;
    }

    @Override
    public void addProgramCard(ProgramCard programCard) {
        this.playerDeck.push(programCard);
    }

    public Stack<ProgramCard> returnDeck() {
        return playerDeck;
    }

    @Override
    public void move(Direction dir) {
        pos = pos.changePos(dir);
    }

    public void die() {
        this.health--;
        System.out.println("YOU LOST HP, NEW HP: " + this.health);
        this.pos = respawnPoint;
    }

    /**
     * Player gets one damageToken
     */


    @Override
    public void takeDamage(int amountOfDamage) {
        if (damageTokens + amountOfDamage < 10) {
            damageTokens += amountOfDamage;
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
        if (damageTokens > 0) {
            damageTokens--;
        }
    }


    /**
     * The piece rotates in the designated direction
     *
     * @param r
     */
    @Override
    public void rotate(Rotation r) {
        if (r == Rotation.R) {
            switch (this.facingDir) {
                case NORTH:
                    this.facingDir = Direction.EAST;
                    break;
                case EAST:
                    this.facingDir = Direction.SOUTH;
                    break;
                case SOUTH:
                    this.facingDir = Direction.WEST;
                    break;
                case WEST:
                    this.facingDir = Direction.NORTH;
                    break;
            }
        } else if (r == Rotation.L) {
            switch (this.facingDir) {
                case NORTH:
                    this.facingDir = Direction.WEST;
                    break;
                case EAST:
                    this.facingDir = Direction.NORTH;
                    break;
                case SOUTH:
                    this.facingDir = Direction.EAST;
                    break;
                case WEST:
                    this.facingDir = Direction.SOUTH;
                    break;
            }
        } else if (r == Rotation.U) {
            switch (this.facingDir) {
                case NORTH:
                    this.facingDir = Direction.SOUTH;
                    break;
                case EAST:
                    this.facingDir = Direction.WEST;
                    break;
                case SOUTH:
                    this.facingDir = Direction.NORTH;
                    break;
                case WEST:
                    this.facingDir = Direction.EAST;
                    break;
            }
        } else {
            throw new IllegalArgumentException("Not a valid rotation!");
        }
        //Comment this out if you want the tests to work
        //robot.doAction(ActionType.ROTATE, facingDir);
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
        return this.facingDir;
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
    public Direction getPieceDirection() {
        return null;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return new GUIRobot(1);
    }

    public void setRobot(MovableGUIRobot robot) {
        this.robot = robot;
    }

    public MovableGUIRobot getRobot() {
        return this.robot;
    }
}
