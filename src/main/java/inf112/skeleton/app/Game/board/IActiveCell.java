package inf112.skeleton.app.Game.board;

public interface IActiveCell extends ICell{

    /**
     * Returns the action this cell does
     * @return Action type of action
     */
    String getAction();

}
