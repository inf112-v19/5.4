package inf112.skeleton.app.Game.board.pieces;

import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.board.IPiece;

public class Laser implements IPiece {

    private Direction direction;
    private LaserShooter laserShooter;

    public Laser(Direction dir, LaserShooter laserShooter){
        this.direction = dir;
        this.laserShooter = laserShooter;
    }

    public LaserShooter getLaserShooter() {
        return laserShooter;
    }

    public int getDamage(){
        return laserShooter.getDamage();
    }
    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Laser";
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
