package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.gameLogic.enums.CardType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ProgramCardDeck implements Serializable {

    private Stack<ProgramCard> deck = new Stack<>();

    /**
     * Generates a deck of 84 cards
     */
    public ProgramCardDeck() {
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
            //Skips ROTATE_RIGHT as these cards are added in ROTATE_LEFT
            if(cardType.equals(CardType.ROTATE_RIGHT)){ continue; }
            for(int i = 0; i < cardType.getNumberOfCard(); i++){
                //This is done so that you get every other ROTATE_LEFT and _RIGHT
                if(cardType.equals(CardType.ROTATE_LEFT)){
                    deck.add(new ProgramCard(CardType.ROTATE_LEFT, priority += 10));
                    deck.add(new ProgramCard(CardType.ROTATE_RIGHT, priority += 10));
                } else {
                    deck.add(new ProgramCard(cardType, priority += 10));
                }
            }
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
    public List<ProgramCard> getDeck() {
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
    public ProgramCard getTopCard() {
        return deck.pop();
    }

    /**
     * Returns a specified number of cards as a list
     * @param numberOfCards int
     * @return List of ProgramCard
     */
    public List<ProgramCard> drawXCards(int numberOfCards) {
        List<ProgramCard> PCList = new ArrayList<>();
        while (numberOfCards-- > 0 && !isEmpty()) {
            PCList.add(getTopCard());
        }
        return PCList;
    }

    /**
     * toString representation of the cards in the deck
     * @return String toString
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ProgramCard currCard : deck) {
            sb.append(currCard.toString())
                    .append("\n");
        }
        return sb.toString();
    }

}
