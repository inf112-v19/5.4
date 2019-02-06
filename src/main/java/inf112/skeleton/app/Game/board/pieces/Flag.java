package inf112.skeleton.app.Game.board.pieces;

import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.board.IPiece;

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
