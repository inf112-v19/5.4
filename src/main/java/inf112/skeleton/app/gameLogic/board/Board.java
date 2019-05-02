package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.game.FlagOrganizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board implements IBoard {

    String boardName;
    ICell[][] board;
    private FlagOrganizer flags;

    // Assuming all boards are square for ease of use.
    // In the future we might make a MegaBoard or something.
    private int boardWidth;
    private int boardHeight;

    public Board(String name, String path) {
        flags = FlagOrganizer.getInstance();
        boardName = name;
        JSONBoardGenerator jsonBoardGenerator = new JSONBoardGenerator();
        board = jsonBoardGenerator.generateJsonBoard(path);
        flags = jsonBoardGenerator.getFlags();
        // Again, assuming it's square. Might break in the future.
        boardHeight = board.length;
        boardWidth = board[0].length;
    }

    public void generateBoard() {

    }

    public FlagOrganizer getFlags() {
        return flags;
    }

    /**
     * Method for displaying the cells in the GUIBoard
     */
    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" | ");
                if (board[i][j] == null) {
                    System.out.print("empty");
                } else {
                    for (IPiece p : board[i][j].getPiecesInCell()) {
                        System.out.print(p.getName() + "-" + p.getPieceDirection() + " ");
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

    @Override
    /**
     * Method for retrieving a Cell from a specific coordinate on the board
     */
    public ICell getCellAt(int x, int y) {
        return board[y][x];
    }

    public void addPiece(int x, int y, IPiece piece){
        board[y][x].addPiece(piece);
    }
    public void addPiece(Position pos, IPiece piece){
        this.addPiece(pos.getX(), pos.getY(), piece);
    }

    /*
    public ICell getNextCell(int x, int y, Direction dir) {

        ICell cell = new Cell();
        switch (dir) {
            case NORTH:
                cell = board[x][y + 1];
                break;
            case SOUTH:
                cell = board[x][y - 1];
                break;
            case EAST:
                cell = board[x + 1][y];
                break;
            case WEST:
                cell = board[x - 1][y];
                break;
        }
        return cell;
    } */

    public ICell getCellAt(Position pos) {
        //Byttet om disse, var board[pos.getX()][pos.getY()];
        return board[pos.getY()][pos.getX()];
    }

    public ICell getNextCell(Position pos, Direction dir) {
        ICell cell = new Cell();
        int posX = pos.getX();
        int posY = pos.getY();
        System.out.println("yeehaw: " + posX + " -- " + posY);
        switch (dir) {

            case NORTH:
                cell = board[Math.max(posY - 1,0)][posX];
                break;
            case SOUTH:
                cell = board[Math.min(posY + 1,board.length-1)][posX];
                break;
            case EAST:
                cell = board[posY][Math.min(posX + 1, board[posY].length-1)];
                break;
            case WEST:
                cell = board[posY][Math.max(posX - 1,0)];
                break;
        }
        return cell;
    }

    public boolean containsPieceDir(Position pos, Direction dir, Class piece){
        for(IPiece currCell : this.getCellAt(pos).getPiecesInCell()){
            if(currCell.getPieceDirection().equals(dir) && currCell.getClass().isInstance(piece)){
                return true;
            }
        }
        return false;
    }

    public boolean cointainsPiece(Position pos, IPiece piece){
        return this.getCellAt(pos).getPiecesInCell().contains(piece);
    }

    public boolean insideBoard(Position playerPos, Direction playerDir){
        Position posAfterMove = playerPos.changePos(playerDir);
        System.out.println("Pos after theoretical move " + posAfterMove.toString());
        if(posAfterMove.getY() >= boardHeight || posAfterMove.getY() < 0){
            return false;
        }
        if (posAfterMove.getX() >= boardWidth || posAfterMove.getX() < 0){
            return false;
        }
        return true;
    }

    public int getBoardWidth(){
        return boardWidth;
    }

    public int getBoardHeight(){
        return boardHeight;
    }

    public void sortBoard() {

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                List<IPiece> currPieces = board[x][y].getPiecesInCell();
                int j = currPieces.size()-1;
                for (int i = currPieces.size()-1; i >= 0 ; i--) {
                    if (currPieces.get(i) instanceof Wall) {
                        if (i == j) continue;
                        IPiece wall = currPieces.get(i);
                        IPiece otherObject = currPieces.get(j);
                        currPieces.remove(wall);
                        currPieces.remove(otherObject);
                        currPieces.add(i, otherObject);
                        currPieces.add(j, wall);
                        j--;
                    }
                }
            }
        }
    }
}
