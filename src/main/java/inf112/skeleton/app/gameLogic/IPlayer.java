package inf112.skeleton.app.gameLogic;


import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.Rotation;


interface IPlayer {

    /**
     * Add programCards to the player
     * @param programCard The card to be added
     */
    void addProgramCard(ProgramCard programCard);

    /**
     * @param steps
     *          Times the player moves in the direction it's facing
     */
    void move(int steps);



    /**
     * Player gets X amount of damageTokens
     * @param amountOfDamage the amount of damageTokens the player recieves
     */
    void takeDamage(int amountOfDamage);



    /**
     * Player loses a damageToken
     */
    void repair();



    /**
     * Player rotates right or left
     * @param rot
     *         the direction to turn
     */
    void rotate(Rotation rot);



    /**
     * @return this player's health
     */
    int getHealth();


    /**
     * @return this player's damageTokens
     */
    int getDamageTokens();



    /**
     * @return This player's current facing direction
     */
    Direction getDirection();

    /**
     * @return
     *      This player's current position
     */
    Position getPos();


    /**
     * @return true if player's health is above 0, otherwise false
     */
    boolean isAlive();


}
