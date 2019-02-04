package inf112.skeleton.app.board.pieces;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.board.IPiece;

public class Laser implements IPiece {

    private Direction direction;

    public Laser(Direction dir){
        this.direction = dir;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Wall";
    }

    @Override
    public char getSymbol() {
        return '#';
    }

    @Override
    public Direction getRotation() {
        return direction;
    }
}
