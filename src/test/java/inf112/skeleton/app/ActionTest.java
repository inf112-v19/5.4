package inf112.skeleton.app;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.game.TestGame;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.game.Action;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.ProgramCard;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class ActionTest {

    private Player player;
    private TestGame game;

    @Before
    public void setupActionTest() {
        player = new Player(new Position(3, 3), Direction.NORTH, 3);
        game = new TestGame();
    }

    @Test
    public void testActionPlayerGetsADamageToken() {
        assertEquals(0, player.getDamageTokens());
        Action action = new Action(player, ActionType.DAMAGE_1);
        game.addActionToList(action);
        game.doAllActions();
        assertEquals(1, player.getDamageTokens());
    }

    @Test
    public void testActionTurnLeft() {
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.ROTATE_L);
        game.addActionToList(action);
        game.doAllActions();
        assertEquals(Direction.WEST, player.getDirection());
    }

    @Test
    public void testActionTurnRight() {
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.ROTATE_R);
        game.addActionToList(action);
        game.doAllActions();
        assertEquals(Direction.EAST, player.getDirection());
    }

    @Test
    public void testActionTurnU() {
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.ROTATE_U);
        game.addActionToList(action);
        game.doAllActions();
        assertEquals(Direction.SOUTH, player.getDirection());
    }

    @Test
    public void testPositionAfterTwoActions() {
        ProgramCardDeck deck = new ProgramCardDeck();
        for (int i = 0; i < 2; i++) {
            ProgramCard tempCard = deck.getTopCard();
            Action action = new Action(player, tempCard.getCardType().getActionType());
            game.addActionToList(action);
        }

        game.doAllActions();

        assertEquals(Direction.NORTH, player.getDirection());
        assertEquals(3, player.getPos().getX());
        assertEquals(9, player.getPos().getY());
    }

    @Test
    public void testTwoPlayersMoving() {
        Player player2 = new Player(new Position(0, 0), Direction.NORTH, 3);
        ProgramCardDeck deck = new ProgramCardDeck();
        int cardsForPlayer = 2;
        game.addPlayerToList(player);
        game.addPlayerToList(player2);

        for (Player currPlayer : game.getPlayerList()) {
            for (int i = 0; i < cardsForPlayer; i++) {
                ProgramCard tempCard = deck.getTopCard();
                System.out.println(tempCard.toString());
                Action action = new Action(currPlayer, tempCard.getCardType().getActionType());
                game.addActionToList(action);
            }
        }

        game.doAllActions();

        assertEquals(Direction.NORTH, player.getDirection());
        assertEquals(3, player.getPos().getX());
        assertEquals(9, player.getPos().getY());

        assertEquals(Direction.NORTH, player2.getDirection());
        assertEquals(0, player2.getPos().getX());
        assertEquals(6, player2.getPos().getY());
    }

}
