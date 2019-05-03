package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.SoundPlayer;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.pieces.Conveyor;
import inf112.skeleton.app.gameLogic.board.pieces.Gears;
import inf112.skeleton.app.gameLogic.board.pieces.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Checker2 {

    private Board board;

    public Checker2(Board board) {
        this.board = board;
    }

    public List<List<PlayerAction>> doCard(ProgramCard programCard, Player player) {
        List<List<PlayerAction>> allActions = new ArrayList<>();

        Action action = programCard.getCardType().getAction();
        for (int i = 0; i < action.getValue(); i++) {
            PlayerAction currPlayerAction = new PlayerAction(player, programCard.getCardType().getAction(), player.getDirection());
            allActions.add(doAction(currPlayerAction));
        }
        return allActions;
    }

    public List<PlayerAction> doAction(PlayerAction playerAction) {
        List<PlayerAction> moveActions = new ArrayList<>();

        Action action = playerAction.getAction();
        Player player = playerAction.getPlayer();

        switch (action.getActionType()) {
            case MOVE:
                tryToMovePlayer(
                        player,
                        action == Action.MOVE_BACK ?
                                player.getDirection().oppositeDir() : player.getDirection(),
                        moveActions);
                break;
            case ROTATE:
                moveActions.add(rotatePlayer(player, action));
                break;
        }
        return moveActions;
    }

    public PlayerAction rotatePlayer(Player player, Action action) {

        player.rotate(action.getRotation());
        return new PlayerAction(player, action, player.getDirection());
    }

    public void tryToMovePlayer(Player player, Direction direction, List<PlayerAction> moveActions) {
        if (canPlayerMove(player, direction, moveActions)) {
            movePlayer(player, direction, moveActions);
        }
    }

    public void movePlayer(Player player, Direction direction, List<PlayerAction> moveActions) {

        if (!board.insideBoard(player.getPos().changePos(direction)) || !board.insideBoard(player.getPos())) {
            this.board.killPlayer(player);
        }

        moveActions.add(board.movePlayer(player, direction));
    }

    public boolean canPlayerMove(Player player, Direction direction, List<PlayerAction> moveActions) {
        return !hasWall(player, direction)
                && checkForPlayer(player, direction, moveActions);
    }

    public boolean hasWall(Player player, Direction direction) {

        ICell nextCell = board.getCellAt(player.getPos().changePos(direction));
        if (nextCell == null) {
            return false;
        }

        List<IPiece> pieces = board.getCellAt(player.getPos()).getPiecesInCell();

        for (IPiece piece : pieces) {
            if (piece instanceof Wall && piece.getPieceDirection() == direction) {
                return true;
            }
        }
        return false;
    }

    public boolean checkForPlayer(Player player, Direction direction, List<PlayerAction> moveActions) {
        Position playerPos = player.getPos();
        Position nextPos = playerPos.changePos(direction);
        ICell nextCell = board.getCellAt(nextPos);
        if (nextCell == null) {
            return true;
        }

        List<IPiece> nextCellPieces = board.getCellAt(nextPos).getPiecesInCell();

        for (IPiece piece : nextCellPieces) {
            if (piece instanceof Player) {
                Player otherPlayer = (Player) piece;
                if (canPlayerMove(otherPlayer, direction, moveActions)) {
                    movePlayer(otherPlayer, direction, moveActions);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void conveyorMove(Player player, Direction conveyorMoveDir, List<PlayerAction> moveActions) {
        if (!hasWall(player, conveyorMoveDir)) {
            movePlayer(player, conveyorMoveDir, moveActions);
        }
    }

    public List<PlayerAction> doPiecesMoves(List<Player> players) {
        List<PlayerAction> moveActions = new ArrayList<>();
        List<Player> copyPlayersList = players;

        for (Player player : copyPlayersList) {
            ICell cell = board.getCellAt(player.getPos());
            List<IPiece> pieces;
            if (cell != null) {
                pieces = cell.getPiecesInCell();
                List<IPiece> copyPieceList = new ArrayList<>(pieces);
                for (IPiece piece : copyPieceList) {

                    if (piece instanceof Conveyor) {
                        conveyorMove(player, piece.getPieceDirection(), moveActions);
                    }
                    if (piece instanceof Gears) {
                        moveActions.add(rotatePlayer(player, ((Gears) piece).getAction()));
                    }
                }
            }
        }
        return moveActions;
    }

    public Player someoneHasWon(List<Player> players) {
        Player maybeWonPlayer = checkForFlag(players);
        if (checkForFlag(players) != null) {
            return maybeWonPlayer;
        }
        return null;
    }

    public Player checkForFlag(List<Player> players) {
        //checks if the players position is the same as the flag the player is looking for
        for (Player player : players) {
            if (player.getPos().equals(board.getFlags().getFlagPos(player.getRespawnPoint().nextFlag))) {
                System.out.println("Found flag " + player.getRespawnPoint().getNextFlag());
                SoundPlayer.GameSound.FLAG_PICKUP.playSound();
                if (player.getRespawnPoint().nextFlag == board.getFlags().getNumberOfFlags()) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("GOT THE LAST FLAG!!! Flag: " + player.getRespawnPoint().getNextFlag());
                        return player;
                    }
                } else {
                    player.setNextFlag();
                    System.out.println("Next Flag is " + player.getRespawnPoint().getNextFlag());
                }
            }
        }
        return null;
    }

}
