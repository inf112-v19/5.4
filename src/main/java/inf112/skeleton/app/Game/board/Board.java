/**package inf112.skeleton.app.Game.board;

import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.board.activeCells.Conveyor;
import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {

    String boardName;
    List<List<IPiece>> board;

    public Board(String name, String path) {
        boardName = name;
        List<List<IPiece>> boardList = new JSONBoardGenerator().generateJsonBoard(path);
        board = boardList;
    }

    public void displayBoard() {

        double boardSizeDouble = board.size();
        int boardSize = (int) boardSizeDouble;
        int boardSide = (int)Math.sqrt(boardSizeDouble);

        int counter = 0;
        for (int i = 0; i<= boardSize; i++) {
            System.out.print(board.get(i));
            counter++;
            if (counter==boardSize/boardSide){
                System.out.println();
                counter = 0;
            }
        }
    }
    public String getBoardName() {
        return boardName;
    }
    public List<List<IPiece>> getBoard() {
        return board;
    }
}
*/