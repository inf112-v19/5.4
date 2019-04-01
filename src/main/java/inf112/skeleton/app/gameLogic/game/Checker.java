package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.ActionType;

import java.util.List;

public class Checker {
    private Action playersAction;
    private Board board;
    private Player player;

    public Checker(Player player, Action action, Board board) {
        this.player = player;
        this.playersAction = action;
        this.board = board;
    }

    public void doAction() {
        Action att = playersAction;
        switch (att.getActionType()) {
            case MOVE:
                this.move(player.getDirection(), att.getValue());
                break;
            case ROTATE:
                player.rotate(att.getRotation());
        }
    }

    /**
     * @param numSteps The direction the piece should move
     */

    public void move(Direction movePlayerInDir, int numSteps) {
        System.out.println("Dir: " + player.getDirection() + " Pre: " + player.getPos().getX() + " " + player.getPos().getY());
        for (int i = 0; i < numSteps; i++) {
            if (canMove(movePlayerInDir, board.getCellAt(player.getPos()))) {
                System.out.println("moving");
                player.move(movePlayerInDir);
                //Move player robot here, player needs to have a GUI robot
                player.getRobot().doAction(ActionType.MOVE, movePlayerInDir);
            }
            //Comment this out if you want the tests to work
//            robot.doAction(ActionType.MOVE, dir);
        }
        System.out.println("Post :" + player.getPos().getX() + " " + player.getPos().getY());
    }

    private boolean canMove(Direction goingDir, ICell currCell) {
        //Checks walls in current tile
        if (currCell != null) {
            List<IPiece> piecesInCurrCell = board.getCellAt(player.getPos()).getPiecesInCell();
            for (IPiece piece : piecesInCurrCell) {
                System.out.println(piece.getName() + "-" + piece.getPieceDirection());
                if (piece instanceof Wall && piece.getPieceDirection() == goingDir) {
                    System.out.println("hit wall");
                    return false;
                }
            }
        }

        Direction oppositeDir = goingDir.oppositeDir(goingDir);

        // Checks if player goes outside board, and should die.
        if (!board.insideBoard(player.getPos(), goingDir)) {
            //System.out.println("");
            player.die();
            return false;
        }

        //Checks walls in next tile
        //System.out.println("yeehW " + goingDir );
        if (board.getNextCell(player.getPos(), goingDir) != null) {
            List<IPiece> piecesInNextCell = board.getNextCell(player.getPos(), goingDir).getPiecesInCell();

            for (IPiece piece : piecesInNextCell) {
                if (piece instanceof Wall && piece.getPieceDirection() == oppositeDir) {
                    System.out.println("hit wall");
                    return false;
                }
            }
            //checks for player in next tile
            for (IPiece piece : piecesInNextCell) {
                if (piece instanceof Player) {
                    Player player = (Player) piece;
                    if (canMove(goingDir, board.getNextCell(player.getPos(), goingDir))) {
                        player.move(goingDir);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        System.out.println("can move");
        return true;
    }
}
