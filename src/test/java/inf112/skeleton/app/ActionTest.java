package inf112.skeleton.app;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.game.TestGame;
import inf112.skeleton.app.gameLogic.enums.*;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.game.Action;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.ProgramCard;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;


public class ActionTest {

    private Player player;
    private TestGame game;

    @Before
    public void setupActionTest(){
        player = new Player(new Position(3,3), Direction.NORTH, 3);
        game = new TestGame();
    }

    @Test
    public void testActionPlayerGetsADamageToken(){
        assertEquals(0, player.getDamageTokens());
        Action action = new Action(player, ActionType.DAMAGE, 1);
        game.addActionToList(action);
        game.doAction();
        assertEquals(1, player.getDamageTokens());
    }

    @Test
    public void testActionTurnLeft(){
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.TURN, Rotation.L);
        game.addActionToList(action);
        game.doAction();
        assertEquals(Direction.WEST, player.getDirection());
    }

    @Test
    public void testActionTurnRight(){
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.TURN, Rotation.R);
        game.addActionToList(action);
        game.doAction();
        assertEquals(Direction.EAST, player.getDirection());
    }

    @Test
    public void testActionTurnU(){
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.TURN, Rotation.U);
        game.addActionToList(action);
        game.doAction();
        assertEquals(Direction.SOUTH, player.getDirection());
    }

    @Test
    public void testPositionAfterTwoActions(){
        ProgramCardDeck deck = new ProgramCardDeck();
        for(int i = 0; i < 2; i++){
            ProgramCard tempCard = deck.getTopCard();
            Action action = new Action(player, tempCard);
            game.addActionToList(action);
        }

        while(!game.actionListIsEmpty()){
            game.doAction();
        }

        assertEquals(Direction.NORTH, player.getDirection());
        assertEquals(3, player.getPos().getX());
        assertEquals(9, player.getPos().getY());
    }

    @Test
    public void testTwoPlayersMoving(){
        Player player2 = new Player(new Position(0,0), Direction.NORTH, 3);
        ProgramCardDeck deck = new ProgramCardDeck();
        int cardsForPlayer = 2;
        game.addPlayerToList(player);
        game.addPlayerToList(player2);

        for (Player currPlayer : game.getPlayerList()){
            for(int i = 0; i < cardsForPlayer; i++){
                ProgramCard tempCard = deck.getTopCard();
                System.out.println(tempCard.toString());
                Action action = new Action(currPlayer, tempCard);
                game.addActionToList(action);
            }
        }

        while(!game.actionListIsEmpty()){
            game.doAction();
        }

        assertEquals(Direction.NORTH, player.getDirection());
        assertEquals(3, player.getPos().getX());
        assertEquals(9, player.getPos().getY());

        assertEquals(Direction.NORTH, player2.getDirection());
        assertEquals(0, player2.getPos().getX());
        assertEquals(6, player2.getPos().getY());
    }

}
