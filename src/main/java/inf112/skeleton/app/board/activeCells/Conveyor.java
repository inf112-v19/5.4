package inf112.skeleton.app.board.activeCells;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.board.IActiveCell;
import inf112.skeleton.app.board.IPiece;

public class Conveyor implements IActiveCell {

    private Direction direction;
    private int speed = 1;

    public Conveyor(Direction dir){
        this.direction = dir;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public IActiveCell getType() {
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

    public Direction getRotation() {
        return direction;
    }

    @Override
    public String getAction() {
        return null;
    }
}
