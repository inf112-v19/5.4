package inf112.skeleton.app;

import inf112.skeleton.app.Enum.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ProgramCardDeck {

    private Stack<ProgramCard> deck = new Stack<>();
    private int priority = 0;

    public ProgramCardDeck() {
        generateDeck();
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void generateDeck() {
        int NUM_ROTATE_U = 6,
                NUM_ROTATE_LEFT_RIGHT = 18,
                NUM_BACK_UP = 6,
                NUM_MOVE_1 = 18,
                NUM_MOVE_2 = 12,
                NUM_MOVE_3 = 6;
        generatePartOfDeck(CardType.ROTATE_U, NUM_ROTATE_U);
        while(NUM_ROTATE_LEFT_RIGHT-- > 0){
            deck.add(new ProgramCard(CardType.ROTATE_LEFT, priority += 10));
            deck.add(new ProgramCard(CardType.ROTATE_RIGHT, priority += 10)); }
        generatePartOfDeck(CardType.BACK_UP, NUM_BACK_UP);
        generatePartOfDeck(CardType.MOVE_1, NUM_MOVE_1);
        generatePartOfDeck(CardType.MOVE_2, NUM_MOVE_2);
        generatePartOfDeck(CardType.MOVE_3, NUM_MOVE_3);
    }

    private void generatePartOfDeck(CardType type, int numberOfCard){
        while(numberOfCard-- > 0) { deck.add(new ProgramCard(type, priority += 10)); }
    }

    public int numCardsLeftInDeck() {
        return deck.size();
    }

    public Stack<ProgramCard> getDeck() {
        return deck;
    }

    private boolean isEmpty() {
        return deck.isEmpty();
    }

    public ProgramCard getTopCard() {
        return deck.pop();
    }

    public List<ProgramCard> drawXCards(int numberOfCards) {
        List<ProgramCard> PCList = new ArrayList<>();
        while (numberOfCards-- > 0 && !isEmpty()) {
            PCList.add(getTopCard());
        }
        return PCList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ProgramCard currCard : deck) {
            sb.append(currCard.toString())
                    .append("\n");
        }
        return sb.toString();
    }

}
