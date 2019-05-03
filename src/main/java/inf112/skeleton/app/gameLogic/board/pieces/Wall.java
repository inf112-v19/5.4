package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.GUIWall;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class Wall implements IPiece {

    private Direction direction;
    char symbol;
    String name;
    int size;

    public Wall(Direction dir) {
        this.size = 3;
        this.name = "Wall";
        this.symbol = '#';
        this.direction = dir;
    }


    @Override
    public GUIPiece getGUIPiece() {
        return new GUIWall(this.getPieceDirection());
    }
    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public Direction getPieceDirection() {
        return this.direction;
    }


    @Override
    public int getSize() {
        return this.size;
    }
}
