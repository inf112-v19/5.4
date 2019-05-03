package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Conveyor;
import inf112.skeleton.app.gameLogic.board.pieces.Gears;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Checker2 {

    private Board board;
    //List<List<PlayerAction>> allActions;

    public Checker2(Board board) {
        this.board = board;
        //this.allActions = new LinkedList<>();
    }

    public List<List<PlayerAction>> doCard(ProgramCard programCard, Player player) {


        List<List<PlayerAction>> allActions = new ArrayList<>();

        Action action = programCard.getCardType().getAction();
        for (int i = 0; i < action.getValue(); i++) {
            PlayerAction currPlayerAction = new PlayerAction( player, programCard.getCardType().getAction(), player.getDirection());
            allActions.add(
                    doAction(currPlayerAction, allActions
            ));
        }

        return allActions;
    }

    public List<PlayerAction> doAction(PlayerAction playerAction, List<List<PlayerAction>> allActions) {
        Action action = playerAction.getAction();
        Player player = playerAction.getPlayer();

        List<PlayerAction> moveActions = new ArrayList<>();

        switch (action.getActionType()) {
            case MOVE:
                if (action == Action.MOVE_BACK) {
                    movePlayer(player, player.getDirection().oppositeDir(), moveActions);
                } else {
                    movePlayer(player, player.getDirection(), moveActions);
                }
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

    public void movePlayer(Player player, Direction direction, List<PlayerAction> moveActions) {
        if (canPlayerMove(player, direction, moveActions)) {
            System.out.println("LETS MOVE THAT SHIT");
            moveActions.add(board.movePlayer(player,direction));
        }
    }

    public boolean canPlayerMove(Player player, Direction direction, List<PlayerAction> moveActions) {
        boolean hasWall = hasWall(player, direction);
        System.out.println("WALL CHECKED");

        boolean hasMovablePlayer = checkForPlayer(player, direction, moveActions);
        System.out.println("PLAYER CHECKED");

        System.out.println(String.format("%s and %s",hasWall,hasMovablePlayer));

        boolean canMove = !hasWall && hasMovablePlayer;
        return canMove;
    }

    public boolean hasWall(Player player, Direction direction) {
        for (IPiece piece : board.getCellAt(player.getPos()).getPiecesInCell()) {

            boolean hasWall = piece instanceof Wall;

            if (hasWall && piece.getPieceDirection() == direction) {
                System.out.println(String.format("IN POS %s, THERE IS A WALL HERE? %s",player.getPos(), hasWall));
                return true;
            }
        }
        System.out.println("NO WALL");
        return false;
    }

    public boolean checkForPlayer(Player player, Direction direction, List<PlayerAction> moveActions) {
        Position playerPos = player.getPos();
        Position nextPos = playerPos.changePos(direction);
        System.out.println(String.format("Player is in pos %s",playerPos));
        List<IPiece> currCellPieces = board.getCellAt(playerPos).getPiecesInCell();
        //List<IPiece> nextCellPieces = board.getNextCell(playerPos,direction).getPiecesInCell();
        List<IPiece> nextCellPieces = board.getCellAt(nextPos).getPiecesInCell();
        //System.out.println(String.format("Curr cell %s pieces: %s \nNext cell %s pieces: %s",playerPos,
        //        currCellPieces, nextPos, nextCellPieces));

        for (IPiece piece : nextCellPieces) {
            if (piece instanceof Player) {
                System.out.println("Found a player");
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

    public void conveyorMove(Player player, Direction conveyorMoveDir, List<PlayerAction> moveActions){

        if(!hasWall(player, conveyorMoveDir)){
            moveActions.add(board.movePlayer(player, conveyorMoveDir));
        }
    }

    public List<PlayerAction> doPiecesMoves(List<Player> players) {
        List<PlayerAction> moveActions = new ArrayList<PlayerAction>();
        List<Player> copyPlayersList = players;

        for(Player player: copyPlayersList){
            List<IPiece> pieces = board.getCellAt(player.getPos()).getPiecesInCell();
            List<IPiece> copyPieceList = new ArrayList<>(pieces);
            for (IPiece piece : copyPieceList) {

                if(piece instanceof Conveyor) {
                    conveyorMove(player, piece.getPieceDirection(), moveActions);
                }
                if (piece instanceof Gears) {
                    moveActions.add(rotatePlayer(player, ((Gears)piece).getAction()));
                }
            }
        }
        return moveActions;
    }

}
