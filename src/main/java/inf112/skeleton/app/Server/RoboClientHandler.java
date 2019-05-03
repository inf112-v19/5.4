package inf112.skeleton.app.Server;

import inf112.skeleton.app.gameLogic.ProgramCardDeck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RoboClientHandler extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ProgramCardDeck serverDeck;
    private String playername;

    public RoboClientHandler(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;

        //The server card stack
        serverDeck = new ProgramCardDeck();
    }

    @Override
    public void run() {
        while (true) {
            try {

                playername = (String) ois.readObject();
                System.out.println(playername + " has joined the game.");
                serverDeck.shuffleDeck();
                oos.writeObject(serverDeck);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                this.ois.close();
                this.oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }
}
