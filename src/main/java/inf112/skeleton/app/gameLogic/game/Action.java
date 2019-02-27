package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.Player;

public class Action {
    private Player player;
    private ActionType actionType;

    public Action(Player player, ActionType actionType) {
        this.player = player;
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Player getPlayer() {
        return player;
    }
}
