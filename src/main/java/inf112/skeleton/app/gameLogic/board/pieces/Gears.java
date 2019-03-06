package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.board.IActiveCell ;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.List;

public class Gears implements IPiece {

    private Action action;

    public Gears(Action rotation){
        this.action = rotation;
    }


    @Override
    public IPiece getType() {
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
    public Direction getRotation() {
        return null;
    }

    public Action getAction() {
        return action;
    }
}
