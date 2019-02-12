package inf112.skeleton.app.Game;

import inf112.skeleton.app.Game.Enum.ActionType;
import inf112.skeleton.app.Game.Enum.Rotation;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private List<Player> playerList;
    private Queue<Action> actionList;

    public Game() {
        playerList = new ArrayList<>();
        actionList = new LinkedList<>();
    }

    public void doAction() {
        if (!actionList.isEmpty()) {
            Action action = actionList.poll();
            switch (action.getActionType()) {
                case DAMAGE:
                    action.getPlayer().takeDamage();
                    break;
                case MOVE:
                    action.getPlayer().move(action.getAmount());
                    break;
                case TURN:
                    if (action.getRotation() != null) {
                        action.getPlayer().rotate(action.getRotation());
                    }
                    break;
            }
        } else {
            System.out.println("actionList Empty");
        }
    }

    public void addActionToList(Action action) {
        actionList.add(action);
    }

}
