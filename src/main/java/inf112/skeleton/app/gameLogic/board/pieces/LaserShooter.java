package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.board.IPiece;

public class LaserShooter implements IPiece {

    private Direction direction;
    private int damage;

    public LaserShooter(Direction dir, int damage){
        this.direction = dir;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "LaserShooter";
    }

    @Override
    public char getSymbol() {
        return '>';
    }

    @Override
    public Direction getRotation() {
        return direction;
    }
}
