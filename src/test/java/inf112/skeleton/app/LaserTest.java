package inf112.skeleton.app;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.LaserCalculator;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.pieces.Laser;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.game.TestGame;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class LaserTest extends GameTest {

    private TestGame game;

    @Before
    public void setupActionTest() {
        game = new TestGame();
    }

    @Test
    public void testThatTheNumberOfLasersPlacedIsCorrectFromTopToBottomOfMap() {
        Player player = new Player("Player", new Position(0, 0), Direction.SOUTH, 3, false);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        LaserCalculator laserCalculator = new LaserCalculator(game.getBoard(), playerList);

        List<Laser> laserPositionsList = laserCalculator.laserCalculation();

        assertEquals(11, laserPositionsList.size());
    }

    @Test
    public void testThatTheNumberOfLasersPlacedIsCorrectFromTopToWall() {
        Player player = new Player("Player", new Position(0, 0), Direction.SOUTH, 3, false);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        game.getBoard().getCellAt(0, 5).addPiece(new Wall(Direction.SOUTH));
        LaserCalculator laserCalculator = new LaserCalculator(game.getBoard(), playerList);

        List<Laser> laserPositionsList = laserCalculator.laserCalculation();

        assertEquals(5, laserPositionsList.size());
    }

    @Test
    public void testThatTheNumberOfLasersPlacedIsCorrectFromTopToPlayer() {
        Player player = new Player("Player", new Position(0, 0), Direction.SOUTH, 3, false);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        Player player2 = new Player("Player2", new Position(0, 7), Direction.SOUTH, 3, false);

        game.getBoard().getCellAt(0, 7).addPiece(player2);


        LaserCalculator laserCalculator = new LaserCalculator(game.getBoard(), playerList);

        List<Laser> laserPositionsList = laserCalculator.laserCalculation();

        assertEquals(6, laserPositionsList.size());
    }
}
