package inf112.skeleton.app;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;
import org.junit.Test;

public class GameLogicTest extends GameTest {

    @Test
    public void setup() {
        MainGameScreen mgs = new MainGameScreen();
        RoboRallyGame game = new RoboRallyGame(mgs);

    }
}
