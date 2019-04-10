package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.gameLogic.enums.CardType;
import inf112.skeleton.app.gameLogic.enums.OptionCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class OptionCardDeck {

    private Stack<OptionCards> deck = new Stack<>();

    /**
     * Generates a deck of 84 cards
     */
    public OptionCardDeck() {
        generateDeck();
    }

    /**
     * Shuffles the deck using Collections.shuffle
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * Generates the deck of Program cards
     */
    private void generateDeck() {
        deck.clear();
        int priority = 0;
        // Goes through the different CardTypes
        for(CardType cardType : CardType.values()){

        }
    }

    /**
     * Returns the number of cards left in the deck
     * @return int
     */
    public int numCardsLeftInDeck() {
        return deck.size();
    }

    /**
     * Returns the whole deck as a list
     * @return List ProgramCard
     */
    public List<OptionCards> getDeck() {
        return deck;
    }

    /**
     * Returns true if the deck is empty
     * @return true if empty
     */
    private boolean isEmpty() {
        return deck.isEmpty();
    }

    /**
     * Pops the card at the top of the stack and returns it
     * @return ProgramCard
     */
    public OptionCards getTopCard() {
        return deck.pop();
    }

    /**
     * Returns a specified number of cards as a list
     * @param numberOfCards int
     * @return List of ProgramCard
     */
    public List<OptionCards> drawXCards(int numberOfCards) {
        List<OptionCards> OCList = new ArrayList<>();
        while (numberOfCards-- > 0 && !isEmpty()) {
            OCList.add(getTopCard());
        }
        return OCList;
    }

    /**
     * toString representation of the cards in the deck
     * @return String toString
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OptionCards currCard : deck) {
            sb.append(currCard.getName())
                    .append("\n");
        }
        return sb.toString();
    }

}
