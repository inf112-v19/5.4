package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.BaseAsset;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class LaserShooter implements IPiece {

    private Direction direction;
    private int damage;

    public LaserShooter(Direction dir, int damage) {
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
    public Direction getPieceDirection() {
        return direction;
    }

    @Override
    public GUIPiece getGUIPiece() {
        // PLACEHOLDER
        return new BaseAsset();
    }
}
