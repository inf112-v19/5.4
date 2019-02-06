package inf112.skeleton.app.board.pieces;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.board.IPiece;

public class Laser_1 implements IPiece {

    private Direction direction;
    private int damage = 1;

    public Laser_1(Direction dir){
        this.direction = dir;
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
        return "Laser_1";
    }

    @Override
    public char getSymbol() {
        return '-';
    }

    @Override
    public Direction getRotation() {
        return direction;
    }
}
