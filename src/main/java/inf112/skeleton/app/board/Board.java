package inf112.skeleton.app.board;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.board.activeCells.Conveyor_Straight;
import inf112.skeleton.app.board.pieces.Flag;
import inf112.skeleton.app.board.pieces.Wall;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {


    public Board(String name) {
        String boardName = name;
    }

    public ICell[][] brett;
    public ICell[][] getBrett () {
        return brett;
    }


    public void generateBoard() {

        List<List<ICell>> boardList = new ArrayList<List<ICell>>();

        // Row 0
        ArrayList<ICell> row0c0 = new ArrayList<ICell>();
        row0c0.add(new Conveyor_Straight(Direction.EAST));
        boardList.add(row0c0);

        ArrayList<ICell> row0c1 = new ArrayList<ICell>();
        row0c1.add(new Conveyor_Straight(Direction.NORTH));
        boardList.add(row0c1);

        ArrayList<ICell> row0c2 = new ArrayList<ICell>();
        row0c2.add(new Conveyor_Straight(Direction.WEST));
        boardList.add(row0c2);

        ArrayList<ICell> row0c3 = new ArrayList<ICell>();
        row0c3.add(new Conveyor_Straight(Direction.SOUTH));
        boardList.add(row0c3);

        ArrayList<ICell> row0c4 = new ArrayList<ICell>();
        row0c4.add(new Conveyor_Straight(Direction.NORTH));
        boardList.add(row0c4);


        // Row 1

        // Row 2

        // row 3

    }







/*    public void generateBoard(int height, int width) {

        ICell[][] brett = new ICell[height][width];
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett.length; j++) {
                brett[i][j] = new Conveyor_Straight("Straight_conveyor");
            }
        }
        for (int i = 1; i < brett.length-1; i++) {
            for (int j = 1; j < brett.length-1; j++) {
                brett[i][j] = 'X';
            }
        }
        this.brett = brett;
    }
  */

    public void displayBoard(char[][] brett) {
        for (int i = 0; i < brett.length; i++) {
            System.out.println();
            for (int j = 0; j < brett[i].length; j++) {
                System.out.print(" | " + brett[i][j]);
            }
            System.out.print(" | ");
            System.out.println();
        }
    }
}
