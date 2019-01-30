package inf112.skeleton.app;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.Enum.Rotation;

public interface IPlayer {

    /**
     * Moves piece i times in the direction it's facing
     * @param i
     */
    void move(int i);

    /**
     * Piece loses 1 health
     */
    void takeDamage();


    /**
     * Piece rotates right or left
     * @param r
     */
    void rotate(Rotation r);

    /**
     * Piece fires in the direction it's facing
     */
    void fire();

    /**
     * @return this piece's health
     */
    int getHealth();

    /**
     * @return This piece's current facing direction
     */
    Direction direction();


}
