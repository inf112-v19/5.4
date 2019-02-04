package inf112.skeleton.app.board.pieces;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.board.IPiece;

public class LaserShooter_1 implements IPiece {

    private Direction direction;

    public LaserShooter_1(Direction dir){
        this.direction = dir;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "LaserShooter_1_1";
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
