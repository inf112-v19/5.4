package inf112.skeleton.app.board.pieces;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.Enum.Rotation;
import inf112.skeleton.app.board.IPiece;

public class Flag implements IPiece {

    private Direction direction = Direction.NORTH;
    private int number;

    public Flag(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Flag";
    }

    @Override
    public char getSymbol() {
        return 'F';
    }

    @Override
    public Direction getRotation() {
        return direction;
    }
}
