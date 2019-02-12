package inf112.skeleton.app;

import inf112.skeleton.app.GUI.Player.Position;
import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.Enum.Rotation;
import inf112.skeleton.app.Game.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testThatYouLoseHealthWhenYouTakeDamage() {
        Player player = new Player(new Position(1, 1), Direction.NORTH, 3);
        player.takeDamage(1);
        assertEquals(1, player.getDamageTokens());

    }
    @Test
    public void testThatYouFaceTheCorrectDirectionAfterRotatingRight() {
        Player player = new Player(new Position(1,1), Direction.NORTH, 1);

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
        Player player = new Player(new Position(1,1), Direction.NORTH,1);

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
        Player player = new Player(new Position(1,1), Direction.NORTH, 1);

        player.rotate(Rotation.U);
        assertEquals(Direction.SOUTH, player.getDirection());

        player.rotate(Rotation.U);
        assertEquals(Direction.NORTH, player.getDirection());
    }

    @Test
    public void testThatYouFaceTheCorrectDirectionAfterUTurn2() {
        Player player = new Player(new Position(1,1), Direction.WEST, 1);

        player.rotate(Rotation.U);
        assertEquals(Direction.EAST, player.getDirection());

        player.rotate(Rotation.U);
        assertEquals(Direction.WEST, player.getDirection());
    }

    @Test
    public void testThatYourPositionCorrectlyChangesWhenMoving() {
        Player player = new Player(new Position(4,3), Direction.SOUTH, 1);

        player.move(3);
        player.rotate(Rotation.L);
        player.move(3);

        assertEquals(7, player.getPos().getX());
        assertEquals(0, player.getPos().getY());
    }

    @Test
    public void testThatPlayerIsAliveWhenHealthIsAboveZero() {
        Player player = new Player(new Position(4,3), Direction.SOUTH, 1);
        assertTrue(player.isAlive());
    }

    @Test
    public void testThatPlayerIsNotAliveWhenHealthIsZero() {
        Player player = new Player(new Position(1,1), Direction.SOUTH, 0);
        assertFalse(player.isAlive());
    }

    @Test
    public void testThatHealthGoesBackToMaxWhenRepairing() {
        Player player = new Player(new Position(1,1), Direction.SOUTH, 9);

        player.takeDamage(1);
        player.repair();

        assertEquals(9, player.getHealth());
    }
}
