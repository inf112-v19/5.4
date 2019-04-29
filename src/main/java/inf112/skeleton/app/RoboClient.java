package inf112.skeleton.app;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.io.*;
import java.net.Socket;

public class RoboClient {

    private ProgramCardDeck clientDeck;
    private Board clientBoard;
    private Player clientPlayer;
    private Socket roboClient;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;


    /**
     * Constructor for opening a client and connecting it to a server
     * @param ip The IP you want to connect to e.g "localhost"
     * @param port The port you want to connect to e.g 8000
     */
    public RoboClient( String ip, int port){
        try {
            System.out.println("Attempting connection to " + ip + " on port " + port);
            roboClient = new Socket(ip, port);
            System.out.println("Connection accepted! Do some shit.");


            ois = new ObjectInputStream(roboClient.getInputStream());
            oos = new ObjectOutputStream(roboClient.getOutputStream());
            //Board clientBoard = new Board("nutBoard", "DankBoard.json");

            Boolean gameOver = false;

            while (!gameOver) {
                // Receive deck and board from server
                clientDeck = (ProgramCardDeck) ois.readObject();
                clientBoard = (Board) ois.readObject();
                System.out.println(clientBoard);
                System.out.println(clientDeck);

                Boolean roundOver = false;
                while (!roundOver) {
                    oos.writeObject(clientDeck);
                    oos.writeObject(clientBoard);
                    roundOver = true;
                }
            }

            //out.writeUTF("Schmell from client");


            //System.out.println("The server sends " + in.readUTF());
            //roboClient.close();
        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
