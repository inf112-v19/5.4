package inf112.skeleton.app.gameLogic.enums;

public enum CardType {
    ROTATE_U("U-Turn", Action.ROTATE_U, 6),
    ROTATE_LEFT("Rotate Left", Action.ROTATE_L, 18),
    ROTATE_RIGHT("Rotate Right", Action.ROTATE_R, 18),
    BACK_UP("Back Up", Action.MOVE_BACK, 6),
    MOVE_1("Move 1", Action.MOVE_1, 18),
    MOVE_2("Move 2", Action.MOVE_2, 12),
    MOVE_3("Move 3", Action.MOVE_3, 6);


    private String description;
    private int numberOfCard;
    private Action action;

    CardType(String description, Action action, int numOfCard) {
        this.description = description;
        this.numberOfCard = numOfCard;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public Action getAction() {
        return action;
    }
}
