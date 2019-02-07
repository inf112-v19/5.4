package inf112.skeleton.app.Game.board.activeCells;

import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.board.IActiveCell;

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