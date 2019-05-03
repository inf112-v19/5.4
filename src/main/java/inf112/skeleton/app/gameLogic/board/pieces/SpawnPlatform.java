package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIConveyor;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class SpawnPlatform implements IPiece {

    private int platformNumber;
    private Direction direction;
    private Position position;

    public SpawnPlatform(int platformNumber, Position position) {
        this.platformNumber = platformNumber;
        this.direction = Direction.NORTH;
        this.position = position;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "SpawnPlatform";
    }

    @Override
    public char getSymbol() {
        return '@';
    }

    public Direction getPieceDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }

    public int getPlatformNumber() {
        return platformNumber;
    }

    public Action getAction() {
        return null;
    }

    @Override
    public GUIPiece getGUIPiece() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

}
