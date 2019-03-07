package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.List;

public class Checker {
    private Position position;
    private Action playersAction;
    private List<Action> actionList;

    public Checker(Player player, Action action) {
        this.position = player.getPos();
        this.playersAction = action;

    }

    public boolean canMove(Direction goingDir, ICell currCell, ICell nextCell) {
        List<IPiece> piecesInCurrCell = currCell.getPiecesInCell();
        List<IPiece> piecesInNextCell = nextCell.getPiecesInCell();
        for (IPiece piece : piecesInCurrCell) {
            if (piece instanceof Wall && piece.getRotation() == goingDir) {
                return false;
            }
        }
        Direction oppositeDir = goingDir.oppositeDir(goingDir);
        for (IPiece piece : piecesInNextCell) {
            if (piece instanceof Wall && piece.getRotation() == oppositeDir) {
                if(piece instanceof Player){
                    /*
                    Torjus sin makeRecursiveCollision();
                    For å styre spilleren
                    Player player = (Player) piece;
                    player.move();
                    */
                }
                return false;
            }
        }
        return true;
    }

    public Action tryAction() {
        switch (playersAction.getActionType()) {
            case MOVE:
                tryMoveAction(playersAction);
                break;
            case DAMAGE:
                //denne er egentlig helt unødvendig, hvis en spiller skal ta damage, så er det bare å gjøre det
                tryDamageAction(playersAction);
                break;
            case ROTATE:
                //samme greien her, må egentlig prøve å move
                tryRotateAction(playersAction);
                break;
        }
        return null;
    }

    private void tryMoveAction(Action action) {
    }

    private void tryDamageAction(Action action) {
    }

    private void tryRotateAction(Action action) {
    }

}