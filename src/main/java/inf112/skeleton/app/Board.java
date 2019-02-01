package inf112.skeleton.app;

public class Board implements IBoard {


    public Board(String name) {
        String boardName = name;

    }

    public char[][] brett;
    public char[][] getBrett () {
        return brett;
    }

    public void generateBoard(int height, int width) {

        char[][] brett = new char[height][width];
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett.length; j++) {
                brett[i][j] = '0';
            }
        }
        for (int i = 1; i < brett.length-1; i++) {
            for (int j = 1; j < brett.length-1; j++) {
                brett[i][j] = 'X';
            }
        }
        this.brett = brett;
    }

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
