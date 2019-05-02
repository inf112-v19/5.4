package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Flag;
import inf112.skeleton.app.gameLogic.enums.ActionType;

import java.util.*;


public class TestGame {
    private List<Player> playerList;
    private Queue<PlayerAction> playerActionList;
    private PlayerActionWrapper GUIplayeractionList;
    private Board board;
    private Checker checker;

    public TestGame() {
        this.GUIplayeractionList = new PlayerActionWrapper();
        this.playerList = new ArrayList<>();
        this.playerActionList = new LinkedList<>();
        this.board = new Board("TestBoard", "TestBoard.json");
        this.checker = new Checker(board);
    }

    public void doAllActions() {
        while (actionListIsNotEmpty()) {
            //player action board
            PlayerAction playerAction = playerActionList.poll();
            if (playerAction.getAction().getActionType() == ActionType.DAMAGE){
                playerAction.getPlayer().takeDamage(playerAction.getAction().getValue());
            }
            //Checker checker = new Checker(playerAction.getPlayer(), playerAction.getAction(), board, GUIplayeractionList);
            List<List<PlayerAction>> boss = checker.doAction(playerAction.getAction(), playerAction.getPlayer());
            //checker.doAction();
        }

    }

    public Checker getChecker() {
        return checker;
    }

    /*
    public void doAction(PlayerAction playerAction) {
        switch (playerAction.getAction().getActionType()) {
            case MOVE:
                Checker checker = new Checker(playerAction.getPlayer(), );
                playerAction.getPlayer().move(playerAction.getPlayer().getDirection(), playerAction.getAction().getValue());
                break;
            case DAMAGE:
                playerAction.getPlayer().takeDamage(playerAction.getAction().getValue());
                break;
            case ROTATE:
                playerAction.getPlayer().rotate(playerAction.getAction().getRotation());
                break;
        }
    }
    */

    public PlayerActionWrapper getGUIplayeractionList() {
        return GUIplayeractionList;
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

    public Board getBoard() {
        return board;
    }

    public void addPlayerToList(Player player) {
        if (!playerList.contains(player)) {
            playerList.add(player);
        } else {
            System.out.println("This player already exists");
        }
    }


}
