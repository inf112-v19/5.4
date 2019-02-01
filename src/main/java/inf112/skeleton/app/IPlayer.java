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
     * Piece's health goes back to max
     */
    void repair();



    /**
     * Piece rotates right or left
     * @param r
     */
    void rotate(Rotation r);



    /**
     * @return this piece's health
     */
    int getHealth();



    /**
     * @return This piece's current facing direction
     */
    Direction getDirection();



    /**
     * @return This piece's current X position
     */
    int getXpos();



    /**
     * @return This piece's current Y position
     */
    int getYpos();



    /**
     * @return true if piece's health is above 0, otherwise false
     */
    boolean isAlive();

}
