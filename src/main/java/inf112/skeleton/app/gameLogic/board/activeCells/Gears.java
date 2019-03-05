package inf112.skeleton.app.gameLogic.board.activeCells;

import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.board.IActiveCell ;

import java.util.List;

public class Gears implements IActiveCell {

    private Action action;

    public Gears(Action rotation){
        this.action = rotation;
    }

    @Override
    public IActiveCell getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Gear";
    }

    @Override
    public char getSymbol() {
        return '*';
    }

    @Override
    public Action getAction() {
        return action;
    }

    public List<IPiece> getPiecesInCell() {
        return null;
    }
}
