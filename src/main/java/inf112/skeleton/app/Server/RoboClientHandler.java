package inf112.skeleton.app.Server;

import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class RoboClientHandler extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ProgramCardDeck serverDeck;

    public RoboClientHandler(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;

        //The server card stack
        serverDeck = new ProgramCardDeck();
    }
    @Override
    public void run(){
        while (true)
        {
            try {
                Boolean gameOver = false;
                while (!gameOver) {
                    // Write server board to client(s)
                    oos.writeObject(serverDeck);

                    Boolean roundOver = false;
                    while (!roundOver) {

                        serverDeck = (ProgramCardDeck) ois.readObject();

                        System.out.println(serverDeck);
                        roundOver = true;
                    }
                    gameOver = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            /**
            try {
                this.ois.close();
                this.oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
             */
        }
    }
}
