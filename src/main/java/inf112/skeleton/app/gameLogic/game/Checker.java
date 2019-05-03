/*
package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.SoundPlayer;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.pieces.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Conveyor;
import inf112.skeleton.app.gameLogic.board.pieces.Gears;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
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

        for (int i = 0; i < att.getValue(); i++) {
            switch (att.getActionType()) {
                case MOVE:
                    if (att.getDescription().equals(Action.MOVE_BACK.getDescription())) {
                        allActions.add(move(player.getDirection().oppositeDir(), player));
                    } else {
                        allActions.add(move(player.getDirection(), player));
                    }
                    break;
                case ROTATE:
                    allActions.add(this.rotate(player, att));
                    break;
            }
        }
        return allActions;
    }

    public List<PlayerAction> rotate(Player player, Action att) {
        player.rotate(att.getRotation());
        return new LinkedList<PlayerAction>() {{
            add(new PlayerAction(player, att, player.getDirection()));
        }};
    }

    public List<PlayerAction> move(Direction playerMoveDir, Player player) {
        List<PlayerAction> calculatedMoves = new LinkedList<>();
        calculatedMoves.addAll(calculateMoves(playerMoveDir, board.getCellAt(player.getPos()), player));
        /*
        if (calculatedMoves.size() > 0) {
            //Position tempPos = player.getPos();
            board.movePlayer(player, playerMoveDir);
            return calculatedMoves;
        }*/
/*
        return calculatedMoves;
    }

    private List<PlayerAction> calculateMoves(Direction goingDir, ICell currCell, Player player) {
        List<PlayerAction> moveActions = new LinkedList<>();
        //Checks walls in current tile
        if (currCell != null) {
            List<IPiece> piecesInCurrCell = board.getCellAt(player.getPos()).getPiecesInCell();
            for (IPiece piece : piecesInCurrCell) {
                ///System.out.println(piece.getName() + "-" + piece.getPieceDirection());
                if (piece instanceof Wall && piece.getPieceDirection() == goingDir) {
                    System.out.println("hit wall");
                    return moveActions;
                }
            }
        }

        if(willCrashWithWall(player, goingDir, currCell)){
            System.out.println(player.getPos().toString());
            System.out.println("oh shit im crashin");
            return moveActions;
        }

        Direction oppositeDir = goingDir.oppositeDir();

        // Checks if player goes outside board, and should die.
        if (!board.insideBoard(player) {
            player.die();
            return moveActions;
        }

        //Checks walls in next tile
        if (board.getNextCell(player.getPos(), goingDir) != null) {
            List<IPiece> piecesInNextCell = board.getNextCell(player.getPos(), goingDir).getPiecesInCell();

            for (IPiece piece : piecesInNextCell) {
                if (piece instanceof Wall && piece.getPieceDirection() == oppositeDir) {
                    System.out.println("hit wall");
                    return moveActions;
                }
            }
            //checks for player in next tile
            for (IPiece piece : piecesInNextCell) {
                if (piece instanceof Player) {
                    Player otherPlayer = (Player) piece;
                    moveActions.addAll(move(goingDir,otherPlayer));
                    /*
                    if(moveActions.size() > 0){
                        //moveActions.add(player.move(goingDir));
                    }*/
/*
                    return moveActions;
                }
            }
        }
        return new LinkedList<PlayerAction>() {{
            add(board.movePlayer(player, goingDir));
        }};
    }

    public void checkForFlag(Player player) {
        //checks if the players position is the same as the flag the player is looking for
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

    public PlayerAction conveyorMove(Player player, Direction conveyorMoveDir){

        if(willCrashWithWall(player,conveyorMoveDir, board.getCellAt(player.getPos()))){
            return null;
        }
        else {
            return board.movePlayer(player, conveyorMoveDir);
        }
    }

    public List<PlayerAction> doPiecesMoves(List<Player> players) {
        List<PlayerAction> allActions = new LinkedList<>();

        List<Player> copyPlayersList = new ArrayList<>(players);
        for(Player player: copyPlayersList){
            System.out.println(player);
            List<IPiece> pieces = board.getCellAt(player.getPos()).getPiecesInCell();
            List<IPiece> copyPieceList = new ArrayList<>(pieces);
            for (IPiece piece : copyPieceList) {
                if(piece instanceof Conveyor) {
                    allActions.addAll(move(piece.getPieceDirection(), player));
                }
                if (piece instanceof Gears) {
                    allActions.addAll(this.rotate(player, ((Gears) piece).getAction()));
                }
            }
        }
        return allActions;
    }

    public boolean willCrashWithWall(Player player, Direction goingDir, ICell currCell){

        Direction oppositeDir = goingDir.oppositeDir();

        if (currCell != null) {
            List<IPiece> piecesInCurrCell = board.getCellAt(player.getPos()).getPiecesInCell();
            for (IPiece piece : piecesInCurrCell) {
                //System.out.println(piece.getName() + "-" + piece.getPieceDirection());
                if (piece instanceof Wall && piece.getPieceDirection() == goingDir) {
                    System.out.println("hit wall");
                    return true;
                }
            }
        }

        if (board.getNextCell(player.getPos(), goingDir) != null) {
            List<IPiece> piecesInNextCell = board.getNextCell(player.getPos(), goingDir).getPiecesInCell();

            for (IPiece piece : piecesInNextCell) {
                if (piece instanceof Wall && piece.getPieceDirection() == oppositeDir) {
                    System.out.println("hit wall");
                    return true;
                }
            }
        }

        return false;

    }
}
*/