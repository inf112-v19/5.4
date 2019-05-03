package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIGear;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.io.Serializable;

public class Gears implements IPiece, Serializable {

    private Action action;

    public Gears(Action rotation) {
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
    public Direction getPieceDirection() {
        return null;
    }

    @Override
    public GUIPiece getGUIPiece() {
        // Seems very easily breakable.
        return new GUIGear(this.action.getRotation());
    }

    @Override
    public int getSize() {
        return 0;
    }

    public Action getAction() {
        return action;
    }
}
