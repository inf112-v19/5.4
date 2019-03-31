package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.*;
import inf112.skeleton.app.gameLogic.board.pieces.*;
import inf112.skeleton.app.gameLogic.enums.Action;
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

    public Checker(Player player, Board board) {
        this.player = player;
        this.playersAction = null;
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
            }
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
    public void lookForFlag(){
        System.out.println("Looking for flag " + player.getRespawnPoint().getNextFlag());
        for(IPiece piece : board.getCellAt(player.getPos()).getPiecesInCell()){
            if(piece instanceof Flag){
                Flag flag = (Flag) piece;
                if(player.isLastFlag(flag, 3)){
                    System.out.println("GOT THE LAST FLAG!!! " + player.getRespawnPoint().getNextFlag());
                    System.exit(0);
                }
                if(player.isNextFlag(flag)){
                    System.out.println("Found flag " + player.getRespawnPoint().getNextFlag());
                    player.setNextFlag();
                    System.out.println("Next Flag is " + player.getRespawnPoint().getNextFlag());
                }


            }
        }
    }

}
