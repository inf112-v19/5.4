package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.board.IPiece;

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
        return "GUIWall";
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
