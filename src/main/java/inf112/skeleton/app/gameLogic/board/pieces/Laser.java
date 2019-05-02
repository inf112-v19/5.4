package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUILaser;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.LaserGUI;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Laser implements IPiece {

    private final Position position;
    private final GUIPiece guiPiece;
    private Direction direction;
    private int damage;

    public Laser(Direction dir, int damage, Position position) {
        this.direction = dir;
        this.damage = damage;
        this.position = position;
        this.guiPiece = new LaserGUI(dir);
    }

    @SuppressWarnings("all")
    public void shootLaser(Position pos, Direction dir, Board board) {
        List<Laser> laserList = new ArrayList<>();
    }


    public int getDamage() {
        return this.damage;
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
    public GUIPiece getGUIPiece() {return this.guiPiece;
    }

    public Position getPosition() {
        return this.position;
    }
}
