package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import java.util.*;


public class TestGame {
    private List<Player> playerList;
    private Queue<PlayerAction> playerActionList;

    public TestGame() {
        playerList = new ArrayList<>();
        playerActionList = new LinkedList<>();
    }

    public void doAllActions() {
        while (actionListIsNotEmpty()) {
            doAction(playerActionList.poll());
        }
    }

    public void doAction(PlayerAction playerAction) {
        switch (playerAction.getAction().getActionType()) {
            case MOVE:
                playerAction.getPlayer().move(playerAction.getAction().getValue());
                break;
            case DAMAGE:
                playerAction.getPlayer().takeDamage(playerAction.getAction().getValue());
                break;
            case ROTATE:
                playerAction.getPlayer().rotate(playerAction.getAction().getRotation());
                break;
        }
    }

    public void addActionToList(PlayerAction playerAction) {
        playerActionList.add(playerAction);
    }

    public boolean actionListIsNotEmpty() {
        return !playerActionList.isEmpty();
    }

    public boolean playerListIsNotEmpty() {
        return !playerList.isEmpty();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayerToList(Player player) {
        if (!playerList.contains(player)) {
            playerList.add(player);
        } else {
            System.out.println("This player already exists");
        }
    }


}
