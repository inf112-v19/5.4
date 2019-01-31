package inf112.skeleton.app.board;

import inf112.skeleton.app.Enum.Direction;

public class LaserShooter implements IPiece{

    private Direction direction;

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "LaserShooter";
    }

    @Override
    public char getSymbol() {
        return '>';
    }

    @Override
    public Direction getRotation() {
        return direction;
    }
}
