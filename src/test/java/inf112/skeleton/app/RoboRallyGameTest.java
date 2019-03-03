package inf112.skeleton.app;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoboRallyGameTest {
    private RoboRallyGame game;

    @BeforeEach
    void setup() {
        this.game = new RoboRallyGame();
    }


    @Test
    void checkSetup() {
        assertEquals(2, game.players.length);
        assertEquals(new Position(0, 0), game.players[0].getPos());
        assertEquals(new Position(1,0), game.players[1].getPos());
        assertEquals(84, game.deck.numCardsLeftInDeck());
    }

    @Test
    void prePlayTest() {
        Player currentPlayer = game.players[0];
        assertEquals(0, currentPlayer.getPlayerDeckSize());
        assertTrue(currentPlayer.isAlive());
    }
}