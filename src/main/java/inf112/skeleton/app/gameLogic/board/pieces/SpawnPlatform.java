package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.GUISpawnPlatform;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class SpawnPlatform implements IPiece {

    private int platformNumber;

    public SpawnPlatform(int platformNumber) {
        this.platformNumber = platformNumber;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public char getSymbol() {
        return 0;
    }

    @Override
    public Direction getPieceDirection() {
        return null;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return new GUISpawnPlatform(platformNumber);
    }

    @Override
    public int getSize() {
        return 0;
    }
}
