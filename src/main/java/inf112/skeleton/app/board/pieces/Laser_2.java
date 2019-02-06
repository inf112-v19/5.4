package inf112.skeleton.app.board.pieces;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.board.IPiece;

public class Laser_2 implements IPiece {

    private Direction direction;
    private int damage = 2;

    public Laser_2(Direction dir){
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
        return "Laser_2";
    }

    @Override
    public char getSymbol() {
        return '=';
    }

    @Override
    public Direction getRotation() {
        return direction;
    }
}
