package inf112.skeleton.app.gameLogic.board.activeCells;

import inf112.skeleton.app.gameLogic.enums.ActionType;

import inf112.skeleton.app.gameLogic.board.IActiveCell;
import inf112.skeleton.app.gameLogic.enums.Rotation;
public class Gears implements IActiveCell {

    private ActionType actionType;
    private Rotation rotation;

    public Gears(Rotation rotation){
        this.rotation = rotation;
    }

    public Rotation getRotation() {
        return rotation;
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
    public ActionType getActionType() {
        return actionType;
    }
}
