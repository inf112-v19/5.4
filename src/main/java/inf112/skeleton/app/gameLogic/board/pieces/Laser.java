package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUILaser;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Laser implements IPiece {

    private Direction direction;
    private LaserShooter laserShooter;

    public Laser(Direction dir, LaserShooter laserShooter) {
        this.direction = dir;
        this.laserShooter = laserShooter;
    }

    @SuppressWarnings("all")
    public void shootLaser(Position pos, Direction dir, Board board) {
        List<Laser> laserList = new ArrayList<>();
    }

    public LaserShooter getLaserShooter() {
        return laserShooter;
    }

    public int getDamage() {
        return laserShooter.getDamage();
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "GUILaser";
    }

    @Override
    public char getSymbol() {
        return '-';
    }

    @Override
    public Direction getPieceDirection() {
        return direction;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return new GUILaser();
    }
}
