package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.pieces.IPiece;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.game.FlagOrganizer;
import inf112.skeleton.app.gameLogic.game.PlayerAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board implements IBoard {

    String boardName;
    ICell[][] board;
    private FlagOrganizer flags;

    // Assuming all boards are square for ease of use.
    // In the future we might make a MegaBoard or something.
    public int boardWidth;
    public int boardHeight;

    List<Player> deadPlayers;

    public Board(String name, String path) {
        flags = FlagOrganizer.getInstance();
        boardName = name;
        JSONBoardGenerator jsonBoardGenerator = new JSONBoardGenerator();
        board = jsonBoardGenerator.generateJsonBoard(path);
        flags = jsonBoardGenerator.getFlags();
        // Again, assuming it's square. Might break in the future.
        boardWidth = board.length;
        boardHeight = board[0].length;
        deadPlayers = new ArrayList<>();
    }

    public FlagOrganizer getFlags() {
        return flags;
    }

    /**
     * Method for displaying the cells in the GUIBoard
     */
    public void displayBoard() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                System.out.print("| ");
                if (board[i][j] == null) {
                    System.out.print("empty");
                } else {
                    for (IPiece p : board[i][j].getPiecesInCell()) {
                        System.out.print(p.getName() + " " /*+ "-" + p.getPieceDirection() + " "*/);
                    }
                }
            }
            System.out.print(" | ");
            System.out.println();
        }
    }

    @Override
    public String getBoardName() {
        return boardName;
    }

    @Override
    public ICell[][] getBoard() {
        return board;
    }

    /**
     * moves player lol
     *
     * @param player
     * @param dir
     * @return
     */
    public PlayerAction movePlayer(Player player, Direction dir) {

        Position tempPos = player.getPos();
        Position nextPos = tempPos.changePos(dir);

        this.changePlayerPos(player, nextPos);

        System.out.println(player.getPos());

        System.out.println();
        return new PlayerAction(player, Action.MOVE_1, dir);
    }


    @Override
    /**
     * Method for retrieving a Cell from a specific coordinate on the board
     */
    public ICell getCellAt(int x, int y) {
        return board[y][x];
    }

    public void addPiece(int x, int y, IPiece piece) {
        board[y][x].addPiece(piece);
    }

    public void addPiece(Position pos, IPiece piece) {
        this.addPiece(pos.getX(), pos.getY(), piece);
    }

    public ICell getCellAt(Position pos) {
        if (insideBoard(pos)) {
            return board[pos.getY()][pos.getX()];
        }
        return null;
    }



    public IPiece cellContainsClass(Position pos, Class piece) {
        for (IPiece currPiece : getCellAt(pos).getPiecesInCell()) {
            if (currPiece.getClass() == piece) {
                return currPiece;
            }
        }
        return null;
    }


    public ICell getNextCell(Position pos, Direction dir) {
        ICell cell = new Cell();
        int posX = pos.getX();
        int posY = pos.getY();
        switch (dir) {

            case NORTH:
                cell = board[Math.max(posY - 1, 0)][posX];
                break;
            case SOUTH:
                cell = board[Math.min(posY + 1, board.length - 1)][posX];
                break;
            case EAST:
                cell = board[posY][Math.min(posX + 1, board[posY].length - 1)];
                break;
            case WEST:
                cell = board[posY][Math.max(posX - 1, 0)];
                break;
        }
        return cell;
    }

    public boolean containsPieceDir(Position pos, Direction dir, Class piece) {
        for (IPiece currCell : this.getCellAt(pos).getPiecesInCell()) {
            if (currCell.getPieceDirection().equals(dir) && currCell.getClass().isInstance(piece)) {
                return true;
            }
        }
        return false;
    }

    public boolean cointainsPiece(Position pos, IPiece piece) {
        return this.getCellAt(pos).getPiecesInCell().contains(piece);
    }

    public boolean insideBoard(Position playerPos) {
        return insideX(playerPos) && insideY(playerPos);
    }

    private boolean insideY(Position playerPos) {
        return (playerPos.getY() < boardWidth && playerPos.getY() >= 0);
    }

    private boolean insideX(Position playerPos) {
        return (playerPos.getX() < boardHeight && playerPos.getX() >= 0);
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void killPlayer(Player player) {
        this.deadPlayers.add(player);
        player.die();
    }

    public List<Player> getDeadPlayers() {
        return this.deadPlayers;
    }

    public void changePlayerPos(Player player, Position newPos) {

        Position currPlayerPos = player.getPos();
        ICell currentCells = getCellAt(currPlayerPos);
        if(currentCells != null){
            getCellAt(currPlayerPos).removePlayer(player);
        }
        ICell nextCells = getCellAt(newPos);
        if (nextCells != null) {
            nextCells.addPiece(player);
        }

        player.changePlayerPos(newPos);

    }

    public void sortBoard() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                Collections.sort(board[x][y].getPiecesInCell(), (iPiece, t1) -> {
                    if (iPiece.getSize() == t1.getSize()) {
                        return 0;
                    } else if (iPiece.getSize() < t1.getSize()) {
                        return -1;
                    }
                    return 1;
                });
            }
        }
    }

}
