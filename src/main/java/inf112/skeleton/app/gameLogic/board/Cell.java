package inf112.skeleton.app.gameLogic.board;

import java.util.LinkedList;
import java.util.List;

public class Cell implements ICell {

    private List<IPiece> piecesInCell;

    public Cell() {
        this.piecesInCell = new LinkedList<IPiece>();
    }

    /**
     * Method for getting the list of pieces in the cell
     * @return List of pieces
     */
    @Override
    public List<IPiece> getPiecesInCell() {
        return piecesInCell;
    }

    /**
     * Method for adding pieces to the list of pieces in the Cell
     * @param piece the piece you want to add
     */
    @Override
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