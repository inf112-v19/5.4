package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.Player;

public class PlayerAction {
    private Player player;
    private Action action;

    public PlayerAction(Player player, Action action) {
        this.player = player;
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public Player getPlayer() {
        return player;
    }
}
