package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.backends.lwjgl.LwjglNativesLoader;
import inf112.skeleton.app.GUI.GUIMain;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;
import inf112.skeleton.app.gameLogic.game.TestGame;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest extends GameTest {

    private Player player;
    private TestGame game;
    private Board board;

    @BeforeEach
    public void setup() {
        LwjglNativesLoader.load();
        Gdx.files = new LwjglFiles();

        System.out.println(new File("DankBoard.json").getAbsoluteFile());
        new LwjglApplication(new GUIMain());
        this.board = new Board("Captain Hook", "DankBoard.json");
        game = new TestGame();
        player = new Player(new Position(7, 7), Direction.NORTH, 3, game.getBoard());

    }
    @Test
    public void sanityTest() {
        RoboRallyGame rrgame = new RoboRallyGame();
        assertEquals(3, rrgame.getTotalPlayers());
        assertEquals(3, rrgame.getStartHealth());
    }

    @Test
    public void test() {
        RoboRallyGame rrgame = new RoboRallyGame();

    }
}
