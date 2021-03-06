package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.gameLogic.enums.CardType;

import java.io.Serializable;

public class ProgramCard implements Comparable<ProgramCard>, Serializable {
    private CardType cardType;
    private int priority;

    ProgramCard(CardType cardType, int priority) {
        this.cardType = cardType;
        this.priority = priority;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return cardType.getDescription() + " " + priority;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public int compareTo(ProgramCard otherCard){
        Integer int1 = this.getPriority();
        Integer int2 = otherCard.getPriority();
        return int1.compareTo(int2);
    }

}
