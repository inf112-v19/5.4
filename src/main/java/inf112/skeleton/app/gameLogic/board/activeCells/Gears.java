package inf112.skeleton.app.Game.board.activeCells;

import inf112.skeleton.app.Game.Enum.ActionType;
import inf112.skeleton.app.Game.Enum.Rotation;
import inf112.skeleton.app.Game.board.IActiveCell;

public class Gears implements IActiveCell {

    private ActionType actionType = ActionType.TURN;
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
