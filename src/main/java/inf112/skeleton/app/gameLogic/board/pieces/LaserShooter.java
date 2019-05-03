package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUILaserShooter;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.board.IPiece;

import java.io.Serializable;

public class LaserShooter implements IPiece, Serializable {

    private Direction direction;
    private int damage;
    private Position pos;

    public LaserShooter(Direction dir, Position pos, int damage){
        this.direction = dir;
        this.damage = damage;
        this.pos = pos;
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
    public Direction getPieceDirection() {
        return direction;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return new GUILaserShooter(this);
    }
    public Position getPos() {
        return this.pos;
    }

    public Laser createNewLaser(Position pos){
        return new Laser(this.direction, this.damage, pos);
    }
}
