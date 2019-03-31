package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.*;
import inf112.skeleton.app.gameLogic.board.pieces.*;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;

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

    /*
    public boolean checkPieceInCurrentCell(IPiece checkForPiece) {
        ICell currCell = board.getCellAt(player.getPos());
        if (currCell != null) {
            List<IPiece> piecesInCurrCell = board.getCellAt(player.getPos()).getPiecesInCell();
            for (IPiece piece : piecesInCurrCell) {
                System.out.println(piece.getName() + "-" + piece.getPieceDirection());
                if (piece instanceof Flag) {
                    return true;
                }
                if (piece instanceof Conveyor) {
                    return true;
                }
                if (piece instanceof Gears) {
                    return true;
                }
                if (piece instanceof Hole) {
                    return true;
                }
            }
        }
        return false;
    }
    */


    /**
     * @param numSteps The direction the piece should move
     */

    public void move(Direction movePlayerInDir, int numSteps) {
        System.out.println("Dir: " + player.getDirection() + " Pre: " + player.getPos().getX() + " " + player.getPos().getY());
        for (int i = 0; i < numSteps; i++) {
            if (canMove(movePlayerInDir, board.getCellAt(player.getPos()))) {
                System.out.println("moving");
                //playerActionSequence.add(Action.MOVE_1);
                Position tempPos = player.getPos();

                player.move(movePlayerInDir);

                if (board.getCellAt(tempPos).getPiecesInCell().contains(player)) {
                    board.getCellAt(tempPos).getPiecesInCell().remove(player);
                    board.getNextCell(tempPos, movePlayerInDir).addPiece(player);
                }

                //Move player robot here, player needs to have a GUI robot
                player.getRobot().fullAction(Action.MOVE_1, movePlayerInDir);
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
                    Player otherPlayer = (Player) piece;
                    Checker checker = new Checker(otherPlayer, Action.MOVE_1, board);
                    if (checker.canMove(goingDir, board.getNextCell(otherPlayer.getPos(), goingDir))) {
                        checker.move(goingDir, 1);
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
