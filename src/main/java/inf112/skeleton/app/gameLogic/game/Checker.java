package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.*;
import inf112.skeleton.app.gameLogic.board.pieces.*;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.SoundPlayer;

import java.util.LinkedList;
import java.util.List;

public class Checker {
    private Board board;


    public Checker(Board board) {
        this.board = board;
    }

    public List<List<PlayerAction>> doAction(final Action att, final Player player) {

        // This list returns all moves that are generated from that one action/card.
        // Each nested list are all moves that are to be done in parallel.
        List<List<PlayerAction>> allActions = new LinkedList<>();

        for(int i = 0; i < att.getValue(); i++){
            switch (att.getActionType()) {
                case MOVE:
                    List<PlayerAction> moveActions = new LinkedList<>();
                    this.move(player.getDirection(), player, allActions, moveActions);
                    allActions.add(moveActions);
                    break;
                case ROTATE:
                    player.rotate(att.getRotation());
                    allActions.add(new LinkedList<PlayerAction>() {{add(new PlayerAction(player, att, player.getDirection()));}});
                    break;
            }
        }
        return allActions;
    }

    public void move(Direction playerMoveDir, Player player, List<List<PlayerAction>> allActions, List<PlayerAction> moveActions) {
        System.out.println("Dir: " + player.getDirection() + " Pre: " + player.getPos().getX() + " " + player.getPos().getY());
            if (canMove(playerMoveDir, board.getCellAt(player.getPos()), player, allActions, moveActions)) {
                //playerActionSequence.add(Action.MOVE_1);
                Position tempPos = player.getPos();
                player.move(playerMoveDir);
                moveActions.add(new PlayerAction(player, Action.MOVE_1, playerMoveDir));

                if (board.getCellAt(tempPos).getPiecesInCell().contains(player)) {
                    board.getCellAt(tempPos).getPiecesInCell().remove(player);
                    board.getNextCell(tempPos, playerMoveDir).addPiece(player);
                }
            }
        System.out.println("Post :" + player.getPos().getX() + " " + player.getPos().getY());
    }

    private boolean canMove(Direction goingDir, ICell currCell, Player player, List<List<PlayerAction>> allActions, List<PlayerAction> moveActions) {
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

        Direction oppositeDir = goingDir.oppositeDir();

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
                    //Checker checker = new Checker(otherPlayer, board, playerActionQueue);
                    if (canMove(goingDir, board.getNextCell(otherPlayer.getPos(), goingDir), otherPlayer, allActions, moveActions)) {
                        move(goingDir, otherPlayer, allActions, moveActions);
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

    public void checkForFlag(Player player) {
        //checks if the players position is the same as the flag the player is looking for
        //System.out.println("Looking for flag " + player.getRespawnPoint().getNextFlag());
        System.out.println("Player: " + player.getPos() + " Flag: " + player.getRespawnPoint().getNextFlag() + " " + board.getFlags().getFlagPos(player.getRespawnPoint().nextFlag));
        if (player.getPos().equals(board.getFlags().getFlagPos(player.getRespawnPoint().nextFlag))) {
            System.out.println("Found flag " + player.getRespawnPoint().getNextFlag());
            SoundPlayer.GameSound.FLAG_PICKUP.playSound();
            if (player.getRespawnPoint().nextFlag == board.getFlags().getNumberOfFlags()) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("GOT THE LAST FLAG!!! Flag: " + player.getRespawnPoint().getNextFlag());
                }
            } else {
                player.setNextFlag();
                System.out.println("Next Flag is " + player.getRespawnPoint().getNextFlag());
            }
        }
    }


}
