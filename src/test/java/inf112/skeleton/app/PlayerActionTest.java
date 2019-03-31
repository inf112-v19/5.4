package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.GUI.GUIMain;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.game.PlayerAction;
import inf112.skeleton.app.gameLogic.game.TestGame;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.ProgramCard;
import org.junit.Before;
import org.junit.Test;


import java.io.File;

import static org.junit.Assert.assertEquals;


public class PlayerActionTest {

    private Player player;
    private TestGame game;

    @Before
    public void setupActionTest() {
        System.out.println(new File("DankBoard.json").getAbsoluteFile());
        //new LwjglApplication(new GUIMain());
        this.board = new Board("Captain Hook", "DankBoard.json");
        game = new TestGame();
        player = new Player(new Position(7, 7), Direction.NORTH, 3, game.getBoard());
    }

    @Test
    public void testActionPlayerGetsADamageToken() {
        assertEquals(0, player.getDamageTokens());
        PlayerAction playerAction = new PlayerAction(player, Action.DAMAGE_1);
        game.addActionToList(playerAction);
        game.doAllActions();
        assertEquals(1, player.getDamageTokens());
    }

    @Test
    public void testActionTurnLeft() {
        assertEquals(Direction.NORTH, player.getDirection());
        PlayerAction playerAction = new PlayerAction(player, Action.ROTATE_L);
        game.addActionToList(playerAction);
        game.doAllActions();
        assertEquals(Direction.WEST, player.getDirection());
    }

    @Test
    public void testActionTurnRight() {
        assertEquals(Direction.NORTH, player.getDirection());
        PlayerAction playerAction = new PlayerAction(player, Action.ROTATE_R);
        game.addActionToList(playerAction);
        game.doAllActions();
        assertEquals(Direction.EAST, player.getDirection());
    }

    @Test
    public void testActionTurnU() {
        assertEquals(Direction.NORTH, player.getDirection());
        PlayerAction playerAction = new PlayerAction(player, Action.ROTATE_U);
        game.addActionToList(playerAction);
        game.doAllActions();
        assertEquals(Direction.SOUTH, player.getDirection());
    }

    @Test
    public void testPositionAfterTwoActions() {
        ProgramCardDeck deck = new ProgramCardDeck();
        for (int i = 0; i < 2; i++) {
            ProgramCard tempCard = deck.getTopCard();
            PlayerAction playerAction = new PlayerAction(player, tempCard.getCardType().getAction());
            game.addActionToList(playerAction);
        }

        game.doAllActions();

        assertEquals(Direction.NORTH, player.getDirection());
        assertEquals(7, player.getPos().getX());
        assertEquals(1, player.getPos().getY());
    }

    @Test
    public void testTwoPlayersMoving() {
        Player player2 = new Player(new Position(6, 6), Direction.NORTH, 3, game.getBoard());
        ProgramCardDeck deck = new ProgramCardDeck();
        int cardsForPlayer = 2;
        game.addPlayerToList(player);
        game.addPlayerToList(player2);

        for (Player currPlayer : game.getPlayerList()) {
            for (int i = 0; i < cardsForPlayer; i++) {
                ProgramCard tempCard = deck.getTopCard();
                System.out.println(tempCard.toString());
                PlayerAction playerAction = new PlayerAction(currPlayer, tempCard.getCardType().getAction());
                game.addActionToList(playerAction);
            }
        }

        game.doAllActions();

        assertEquals(Direction.NORTH, player.getDirection());
        assertEquals(7, player.getPos().getX());
        assertEquals(1, player.getPos().getY());

        assertEquals(Direction.NORTH, player2.getDirection());
        assertEquals(6, player2.getPos().getX());
        assertEquals(0, player2.getPos().getY());
    }

}
