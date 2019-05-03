package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;

import java.util.ArrayList;
import java.util.List;

public class CardPicker {

    private final RoboRallyGame rbg;
    List<Player> players;
    List<List<PlayerAndProgramCard>> allPhaseCards;
    Player currentPlayer;
    int counter;
    ProgramCardDeck deck;
    List<List<PlayerAndProgramCard>> allPlayerCards;
    int numberOfPhases;
    int cardsToDraw = 9;

    public CardPicker(List<Player> players, RoboRallyGame rbg) {

        this.players = players;
        this.rbg = rbg;
        this.allPhaseCards = new ArrayList<>();
        this.allPlayerCards = new ArrayList<>();
        this.counter = 0;

        this.numberOfPhases = 5;

        resetCards();

    }

    public void startCardPicking() {
        this.counter = 0;
        this.resetCards();
        this.deck = new ProgramCardDeck();
        this.deck.shuffleDeck();
        this.pickPlayerCards();
    }

    public void pickPlayerCards() {
        this.currentPlayer = players.get(counter);
        cardsToDraw -= currentPlayer.getDamageTokens();

        if (this.currentPlayer.getAi() == false) {
            List<ProgramCard> cards = deck.drawXCards(cardsToDraw);
            this.rbg.guiScreen.pickCardPhase(cards, currentPlayer);
        } else {
            cardsToDraw = 5;
            List<ProgramCard> cards = deck.drawXCards(cardsToDraw);
            postPick(cards);
        }
    }

    public void postPick(List<ProgramCard> pickedProgramCards) {
        this.counter++;
        List<PlayerAndProgramCard> convertedPGCards = new ArrayList<>();
        for (ProgramCard card : pickedProgramCards) {
            PlayerAndProgramCard playerAndProgramCard = new PlayerAndProgramCard(card, currentPlayer);
            convertedPGCards.add(playerAndProgramCard);
        }

        this.allPlayerCards.add(convertedPGCards);

        if (this.counter >= players.size()) {
            this.fromPlayertoPhaseCards();
        } else {
            pickPlayerCards();
        }

    }

    public void fromPlayertoPhaseCards() {
        int numberOfPhases = 5;
        for (List<PlayerAndProgramCard> playerCards : allPlayerCards) {
            for (int i = 0; i < numberOfPhases; i++) {
                if (!(i >= playerCards.size())) {
                    PlayerAndProgramCard tempCard = playerCards.get(i);
                    allPhaseCards.get(i).add(tempCard);
                }
            }
        }
        rbg.executeCards(this.allPhaseCards);

    }

    public void resetCards() {
        allPhaseCards = new ArrayList<>();
        allPlayerCards = new ArrayList<>();
        for (int i = 0; i < numberOfPhases; i++) {
            this.allPhaseCards.add(new ArrayList<>());
        }
    }
}
