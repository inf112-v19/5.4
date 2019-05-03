package inf112.skeleton.app.gameLogic.board.pieces;

import inf112.skeleton.app.GUI.pieces.GUIFlag;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.io.Serializable;

public class Flag implements IPiece, Serializable {

    private Direction direction = Direction.NORTH;
    private int number;

    public enum FlagEnum {
        FLAG_1(1),
        FLAG_2(2),
        FLAG_3(3),
        FLAG_4(4);

        int flagNr;

        FlagEnum(int flagNr) {
            this.flagNr = flagNr;
        }
    }

    public Flag(int number) {
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
    public GUIPiece getGUIPiece() {
        return new GUIFlag(this.number);
    }

    @Override
    public int getSize() {
        return 1;
    }

    public boolean isNextFlag(Player player) {
        return player.getRespawnPoint().getNextFlag() == number;
    }

    public boolean isLastFlag(Player player, int numberOfFlags) {
        return isNextFlag(player) && number == numberOfFlags;
    }

}
