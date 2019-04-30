package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIHole;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class Hole implements IPiece {

    public Hole() {

    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public char getSymbol() {
        return 0;
    }

    @Override
    public Direction getPieceDirection() {
        return null;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return new GUIHole();
    }
}
