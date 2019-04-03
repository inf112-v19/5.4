package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIFlag;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.board.IPiece;

import java.io.Serializable;

public class Flag implements IPiece, Serializable {

    private Direction direction = Direction.NORTH;
    private int number;

    public Flag(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public IPiece getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Flag";
    }

    @Override
    public char getSymbol() {
        return 'F';
    }

    @Override
    public Direction getPieceDirection() {
        return direction;
    }

    @Override
    public GUIPiece getGUIPiece() { return new GUIFlag(); }
}
