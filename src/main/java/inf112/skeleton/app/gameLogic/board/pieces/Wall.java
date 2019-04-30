package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.GUIWall;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class Wall implements IPiece {

    private Direction direction;

    public Wall(Direction dir) {
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
    public Direction getPieceDirection() {
        return direction;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return new GUIWall(this.getPieceDirection());
    }
}
