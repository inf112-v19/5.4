package inf112.skeleton.app;

import inf112.skeleton.app.Enum.CardType;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ProgramCardDeck {
    private Stack<ProgramCard> deck;

    public ProgramCardDeck() {
        deck = generateCards();
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    private Stack<ProgramCard> generateCards() {
        Stack<ProgramCard> temp = new Stack<>();
        int priority = 0;

        for (int i = 0; i < 6; i++) {
            temp.add(new ProgramCard(CardType.ROTATE_U, priority += 10));
        }
        for (int i = 0; i < 18; i++) {
            temp.add(new ProgramCard(CardType.ROTATE_LEFT, priority += 10));
            temp.add(new ProgramCard(CardType.ROTATE_RIGHT, priority += 10));
        }
        for (int i = 0; i < 6; i++) {
            temp.add(new ProgramCard(CardType.BACK_UP, priority += 10));
        }
        for (int i = 0; i < 18; i++) {
            temp.add(new ProgramCard(CardType.MOVE_1, priority += 10));
        }
        for (int i = 0; i < 12; i++) {
            temp.add(new ProgramCard(CardType.MOVE_2, priority += 10));
        }
        for (int i = 0; i < 6; i++) {
            temp.add(new ProgramCard(CardType.MOVE_3, priority += 10));
        }
        return temp;
    }

    public int numCardsLeftInDeck(){
        return deck.size();
    }

    public Stack<ProgramCard> getDeck() {
        return deck;
    }

    private boolean isEmpty() {
        return deck.isEmpty();
    }

    private ProgramCard getTopCard() {
        return isEmpty() ? null : deck.pop();
    }

    public List<ProgramCard> drawXCards(int numberOfCards) {
        List<ProgramCard> PCList = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            PCList.add(getTopCard());
        }
        return PCList;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(ProgramCard currCard : deck){
            sb.append(currCard.getCardType().getDescription())
                    .append(" ")
                    .append(currCard.getPriority())
                    .append("\n");
        }
        return sb.toString();
    }

}
