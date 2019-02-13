package inf112.skeleton.app;

import inf112.skeleton.app.Game.Enum.CardType;
import inf112.skeleton.app.Game.ProgramCard;
import inf112.skeleton.app.Game.ProgramCardDeck;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    private ProgramCardDeck deck;

    @Before
    public void initialize(){
        deck = new ProgramCardDeck();
    }

    @Test
    public void testGetANumberOfCards1(){
        List<ProgramCard> tempDeck = deck.drawXCards(1);
        assertEquals(1, tempDeck.size());
    }

    @Test
    public void testGetANumberOfCards2() {
        List<ProgramCard> tempDeck = deck.drawXCards(2);
        assertEquals(2, tempDeck.size());
    }

    @Test
    public void testDeckSizeAfterDrawing1Card() {
        int before = deck.numCardsLeftInDeck();
        deck.drawXCards(1);
        assertEquals(before - 1, deck.numCardsLeftInDeck());
    }

    @Test
    public void testDeckSizeAfterDrawing84Cards() {
        deck.drawXCards(84);
        assertEquals(0, deck.numCardsLeftInDeck());
    }

    @Test
    public void testCheckFirstCard(){
        List<ProgramCard> tempDeck = deck.drawXCards(84);
        assertEquals(CardType.MOVE_3, tempDeck.get(0).getCardType());
    }

    @Test
    public void testShuffle(){
        ProgramCardDeck preShuffleDeck = new ProgramCardDeck();
        ProgramCardDeck postShuffleDeck = new ProgramCardDeck();
        assertEquals(preShuffleDeck.getTopCard().getPriority()
                , postShuffleDeck.getTopCard().getPriority());
        postShuffleDeck.shuffleDeck();
        assertNotEquals(preShuffleDeck.getTopCard().getPriority()
                , postShuffleDeck.getTopCard().getPriority());
    }

    @Test
    public void testToString(){
        System.out.println(deck.toString());
    }
}
