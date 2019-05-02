package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIConveyor;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class Conveyor implements IPiece {

    private Direction direction;
    private int speed = 1;

    public Conveyor(Direction dir){
        this.direction = dir;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Conveyor";
    }

    @Override
    public char getSymbol() {
        switch (this.direction) {
            case NORTH: return '↑';
            case SOUTH: return '↓';
            case EAST: return '→';
            default: return '←';
        }
    }

    public Direction getPieceDirection() {
        return direction;
    }


    public Action getAction() {
        return null;
    }

    @Override
    public GUIPiece getGUIPiece() { return new GUIConveyor(this.getPieceDirection()); }
}
