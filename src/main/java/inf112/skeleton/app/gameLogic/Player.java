package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.GUI.player.MovableRobot;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
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
    private Board board;


    /**
     * Constructs a player object with position, direction and health
     */
    public Player(Position pos, Direction dir, int health, Board board) {
        this.pos = pos;
        this.dir = dir;
        this.health = health;
        this.maxHealth = health;
        this.damageTokens = 0;
//        this.robot = new MovableRobot(0);
        this.board = board;
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
     *
     * @param att
     */
    public void doAction(Action att) {
        switch (att.getActionType()) {
            case MOVE:
                this.move(dir, att.getValue());
                break;
            case ROTATE:
                this.rotate(Rotation.R);
        }
    }

    /**
     * @param dir The direction the piece should move
     */
    @Override
    public void move(Direction dir, int numSteps) {
        System.out.println("Dir: " + dir + " Pre: " + pos.getX() + " " + pos.getY());
        for (int i = 0; i < numSteps; i++) {
            if (canMove(dir, board.getCellAt(this.pos))){
                this.pos = this.pos.changePos(dir);
                robot.doAction(ActionType.MOVE, dir);
            }
            //Comment this out if you want the tests to work
//            robot.doAction(ActionType.MOVE, dir);
        }
        System.out.println("Post :" + pos.getX() +" "+ pos.getY());
    }

    private boolean canMove(Direction goingDir, ICell currCell) {
        if (currCell == null) {
            return true;
        }

        List<IPiece> piecesInCurrCell = board.getCellAt(pos).getPiecesInCell();
        for (IPiece piece : piecesInCurrCell) {
            System.out.println(piece.getName() +"-"+ piece.getRotation());
            if (piece instanceof Wall && piece.getRotation() == goingDir) {
                System.out.println("hit wall");
                return false;
            }
        }

        Direction oppositeDir = goingDir.oppositeDir(goingDir);
        if (board.getNextCell(pos, goingDir) == null) {
            return true;
        }
        List<IPiece> piecesInNextCell = board.getNextCell(pos, goingDir).getPiecesInCell();

        for (IPiece piece : piecesInNextCell) {
            if (piece instanceof Wall && piece.getRotation() == oppositeDir) {

                return false;
            }
        }
        for (IPiece piece : piecesInNextCell) {
            if (piece instanceof Player) {
                Player player = (Player) piece;
                if (canMove(goingDir, board.getNextCell(pos, goingDir))) {
                    player.move(goingDir, 1);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
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
        } else if (r == Rotation.L) {
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
        } else if (r == Rotation.U) {
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
        } else {
            throw new IllegalArgumentException("Not a valid rotation!");
        }
        //Comment this out if you want the tests to work
//        robot.doAction(ActionType.ROTATE, dir);
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

    public void setRobot(MovableRobot hans) {
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
