package inf112.skeleton.app.Game.board.pieces;

import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.board.IPiece;

public class Wall implements IPiece {

    private Direction direction;

    public Wall(Direction dir){
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
