package inf112.skeleton.app;

import inf112.skeleton.app.Enum.CardType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

    //TODO
    //Find a way to test ShuffleDeck() not sure if it works atm.

}
