package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.GUIRobot;
import inf112.skeleton.app.GUI.player.MovableGUIRobot;
import inf112.skeleton.app.gameLogic.board.pieces.Flag;
import inf112.skeleton.app.gameLogic.board.pieces.LaserShooter;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.Rotation;
import inf112.skeleton.app.gameLogic.game.PlayerAction;
import inf112.skeleton.app.gameLogic.board.pieces.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Flag;
import inf112.skeleton.app.gameLogic.board.pieces.LaserShooter;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.Rotation;
import inf112.skeleton.app.gameLogic.game.PlayerAction;
import inf112.skeleton.app.gameLogic.game.PlayerActionWrapper;
import inf112.skeleton.app.gameLogic.game.RespawnPoint;

import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class Player implements IPlayer {
    private String name;
    private int health;
    private final int maxHealth;
    private int damageTokens;
    private Direction facingDir;
    private Position pos;
    private Stack<ProgramCard> playerDeck;
    private MovableGUIRobot robot;
    private RespawnPoint respawnPoint;
    private boolean isAi;
    private boolean isAlive;




    /**
     * Constructs a player object with position, direction and health
     */
    public Player(String name, Position pos, Direction dir, int health, boolean isAi) {
        this.name = name;
        this.pos = pos;
        this.facingDir = dir;
        this.health = health;
        this.maxHealth = health;
        this.damageTokens = 0;
        this.robot = new MovableGUIRobot(1);
        this.respawnPoint = new RespawnPoint(pos, 1);
        this.isAi = isAi;
        this.isAlive = true;

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

    public void changePlayerPos(Position newPos){this.pos = newPos;}

    public PlayerAction die() {
        this.health--;
        this.damageTokens = 0;
        System.out.println("YOU LOST HP, NEW HP: " + this.health);
        this.pos = respawnPoint.getPos();
        this.isAlive = false;

        return new PlayerAction(this, Action.DIE, facingDir);
    }

    /**
     * Player gets one damageToken
     */


    @Override
    public void takeDamage(int amountOfDamage) {
        if (damageTokens + amountOfDamage < 10) {
            damageTokens += amountOfDamage;
        } else {
            this.die();
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
        //Coment out line below for the tests to run
        //robot.doAction(ActionType.ROTATE, facingDir);
    }

    public void setRespawnPoint() {
        respawnPoint.setPos(pos);
    }

    public boolean isNextFlag(Flag flag) {
        return this.getRespawnPoint().getNextFlag() == flag.getNumber();
    }

    public boolean isLastFlag(Flag flag, int numberOfFlags){
        return isNextFlag(flag) && flag.getNumber() == numberOfFlags;
    }

    public void setNextFlag(){
        setRespawnPoint();
        respawnPoint.setNextFlag();
    }

    public RespawnPoint getRespawnPoint() {
        return respawnPoint;
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

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public char getSymbol() {
        return 0;
    }

    @Override
    public Direction getPieceDirection() {
        return facingDir;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return new GUIRobot(1);
    }

    @Override
    public int getSize() {
        return 5;
    }

    public void setRobot(MovableGUIRobot robot) {
        this.robot = robot;
    }

    public MovableGUIRobot getRobot() {
        return this.robot;
    }

    public LaserShooter getLaserShooter(){
        return new LaserShooter(this.getDirection(), this.getPos(), 1);
    }

    public Stack getPlayerDeck () {
        return this.playerDeck;
    }
    public void setPlayerDeck (Stack playerDeck) {
        this.playerDeck = playerDeck;
    }

    public boolean isAi() { return this.isAi; }

    public boolean isDead() {
        return !isAlive;
    }

    public void setAlive() {
        this.isAlive = true;
    }
}
