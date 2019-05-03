package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.GUIRepair;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class Repair implements IPiece{
    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Repair";
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
        return new GUIRepair();
    }

    @Override
    public int getSize() {
        return 0;
    }
}
