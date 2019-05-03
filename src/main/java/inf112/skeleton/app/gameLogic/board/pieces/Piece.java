package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class Piece implements IPiece {

    char symbol = '-';
    String name = "undefined";
    int size = 4;


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
        return this.symbol;
    }

    @Override
    public Direction getPieceDirection() {
        return null;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return null;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
