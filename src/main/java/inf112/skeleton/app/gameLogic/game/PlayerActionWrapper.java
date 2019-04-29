package inf112.skeleton.app.gameLogic.game;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerActionWrapper {
    private LinkedList<Queue<PlayerAction>> playerActionList;

    public PlayerActionWrapper(){
        playerActionList = new LinkedList<>();
    }

    public void addElementToCurrent(PlayerAction playerAction){
        playerActionList.getLast().add(playerAction);
    }

    public void addElememt(){
        playerActionList.add(new LinkedList<PlayerAction>());
    }

    public int size(){
        return playerActionList.size();
    }

    public void viewActionList(){
        for(Queue<PlayerAction> fromQueue : playerActionList){
            System.out.println("----------");
            for(PlayerAction pa : fromQueue) {
                System.out.println("Player:" + pa.getPlayer().getName() + " - Action: " + pa.getAction().getDescription());
            }
        }
    }

    public PlayerAction getElement(){
        while(!playerActionList.isEmpty()){
            if(playerActionList.getFirst().isEmpty()){
                playerActionList.removeFirst();
            } else {
                return playerActionList.getFirst().poll();
            }
        }
        return null;
    }
}
