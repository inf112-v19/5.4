package inf112.skeleton.app;

import inf112.skeleton.app.GUI.player.*;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.Rotation;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.game.PlayerAction;
import inf112.skeleton.app.gameLogic.game.TestGame;
import org.junit.Before;
import org.junit.Test;

import javax.management.QueryEval;

import java.util.Queue;

import static org.junit.Assert.*;

public class PlayerTest extends GameTest {
    private TestGame game;
    private Queue<PlayerAction> playerActionQueue;

    @Before
    public void setupActionTest() {
        game = new TestGame();
    }


    @Test
    public void testThatYouLoseHealthWhenYouTakeDamage() {
        Player player = new Player("1", new Position(1, 1), Direction.NORTH, 3, game.getBoard(), playerActionQueue);
        player.takeDamage(1);
        assertEquals(1, player.getDamageTokens());
    }

    @Test
    public void testThatYouFaceTheCorrectDirectionAfterRotatingRight() {
        Player player = new Player("1", new Position(1,1), Direction.NORTH, 1, game.getBoard(), playerActionQueue);

        player.rotate(Rotation.R);
        assertEquals(Direction.EAST, player.getDirection());

        player.rotate(Rotation.R);
        assertEquals(Direction.SOUTH, player.getDirection());

        player.rotate(Rotation.R);
        assertEquals(Direction.WEST, player.getDirection());

        player.rotate(Rotation.R);
        assertEquals(Direction.NORTH, player.getDirection());
    }

    @Test
    public void testThatYouFaceTheCorrectDirectionAfterRotatingLeft() {
        Player player = new Player("1", new Position(1,1), Direction.NORTH,1, game.getBoard(), playerActionQueue);

        player.rotate(Rotation.L);
        assertEquals(Direction.WEST, player.getDirection());

        player.rotate(Rotation.L);
        assertEquals(Direction.SOUTH, player.getDirection());

        player.rotate(Rotation.L);
        assertEquals(Direction.EAST, player.getDirection());

        player.rotate(Rotation.L);
        assertEquals(Direction.NORTH, player.getDirection());
    }

    @Test
    public void testThatYouFaceTheCorrectDirectionAfterUTurn1() {
        Player player = new Player("1", new Position(1,1), Direction.NORTH, 1, game.getBoard(), playerActionQueue);

        player.rotate(Rotation.U);
        assertEquals(Direction.SOUTH, player.getDirection());

        player.rotate(Rotation.U);
        assertEquals(Direction.NORTH, player.getDirection());
    }

    @Test
    public void testThatYouFaceTheCorrectDirectionAfterUTurn2() {
        Player player = new Player("1", new Position(1,1), Direction.WEST, 1, game.getBoard(), playerActionQueue);

        player.rotate(Rotation.U);
        assertEquals(Direction.EAST, player.getDirection());

        player.rotate(Rotation.U);
        assertEquals(Direction.WEST, player.getDirection());
    }

    @Test
    public void testThatYourPositionCorrectlyChangesWhenMoving() {

        Player player = new Player("1", new Position(4,3), Direction.SOUTH, 1, game.getBoard(), playerActionQueue);
        for (int i = 0; i < 3; i++) {
            player.move(player.getDirection());
        }
        player.rotate(Rotation.L);

        for (int i = 0; i < 3; i++) {
            player.move(player.getDirection());
        }

        assertEquals(7, player.getPos().getX());
        assertEquals(6, player.getPos().getY());
    }

    @Test
    public void testThatPlayerIsAliveWhenHealthIsAboveZero() {
        Player player = new Player("1", new Position(4,3), Direction.SOUTH, 1, game.getBoard(), playerActionQueue);
        assertTrue(player.isAlive());
    }

    @Test
    public void testThatPlayerIsNotAliveWhenHealthIsZero() {
        Player player = new Player("1", new Position(1,1), Direction.SOUTH, 0, game.getBoard(), playerActionQueue);
        assertFalse(player.isAlive());
    }

    @Test
    public void testThatHealthGoesBackToMaxWhenRepairing() {
        Player player = new Player("1", new Position(1,1), Direction.SOUTH, 9, game.getBoard(), playerActionQueue);

        player.takeDamage(1);
        player.repair();

        assertEquals(9, player.getHealth());
    }
}
