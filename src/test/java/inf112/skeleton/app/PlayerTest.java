package inf112.skeleton.app;

import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.Enum.Rotation;
import inf112.skeleton.app.Game.IPlayer;
import inf112.skeleton.app.Game.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testThatYouLoseHealthWhenYouTakeDamage() {
        IPlayer player = new Player(1, 2, Direction.NORTH, 9);
        player.takeDamage();

        assertEquals(8, player.getHealth());

    }
    @Test
    public void testThatYouFaceTheCorrectDirectionAfterRotatingRight() {
        IPlayer player = new Player(4, 3, Direction.NORTH, 1);

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
        IPlayer player = new Player(4, 3, Direction.NORTH,1);

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
        IPlayer player = new Player(4, 3, Direction.NORTH, 1);

        player.rotate(Rotation.U);
        assertEquals(Direction.SOUTH, player.getDirection());

        player.rotate(Rotation.U);
        assertEquals(Direction.NORTH, player.getDirection());
    }

    @Test
    public void testThatYouFaceTheCorrectDirectionAfterUTurn2() {
        IPlayer player = new Player(4, 3, Direction.WEST, 1);

        player.rotate(Rotation.U);
        assertEquals(Direction.EAST, player.getDirection());

        player.rotate(Rotation.U);
        assertEquals(Direction.WEST, player.getDirection());
    }

    @Test
    public void testThatYourPositionCorrectyChangesWhenMoving() {
        IPlayer player = new Player(4, 3, Direction.SOUTH, 1);

        player.move(3);
        player.rotate(Rotation.L);
        player.move(3);

        assertEquals(7, player.getXpos());
        assertEquals(6, player.getYpos());
    }

    @Test
    public void testThatPlayerIsAliveWhenHealthIsAboveZero() {
        IPlayer player = new Player(4, 3, Direction.SOUTH, 1);
        assertTrue(player.isAlive());
    }

    @Test
    public void testThatPlayerIsNotAliveWhenHealthIsZero() {
        IPlayer player = new Player(4, 3, Direction.SOUTH, 0);
        assertFalse(player.isAlive());
    }

    @Test
    public void testThatHealthGoesBackToMaxWhenRepairing() {
        IPlayer player = new Player(4, 3, Direction.SOUTH, 9);

        player.takeDamage();
        player.repair();

        assertEquals(9, player.getHealth());
    }
}
