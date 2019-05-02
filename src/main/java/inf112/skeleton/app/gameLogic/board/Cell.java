package inf112.skeleton.app.gameLogic.board;

import java.util.List;

public class Cell implements ICell {

    private List<IPiece> piecesInCell;

    public List<IPiece> getPiecesInCell() {
        return piecesInCell;
    }

    public void addPiece(IPiece piece) {
        piecesInCell.add(piece);
    }

    @Override
    public ICell getType() {
        return null;
    }

    @Override
    public char getSymbol() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}