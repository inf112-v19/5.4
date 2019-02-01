package inf112.skeleton.app.board;

public interface IActiveCell extends ICell{

    //TODO Implement Action?
    //Swap from String to Action
    //Action takes a player and "does something" to it
    /**
     * Returns the action this cell does
     * @return Action type of action
     */
    String getAction();


}
