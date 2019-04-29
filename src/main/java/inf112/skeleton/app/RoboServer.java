package inf112.skeleton.app;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class RoboServer extends Thread {

    private ServerSocket roboServer;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ProgramCardDeck serverDeck;
    private Board serverBoard;

    /**
     * Constructor for creating server
     * @param port The port to run the server on
     * @throws IOException
     */
    public RoboServer (int port) throws IOException {

        roboServer = new ServerSocket(port);
        roboServer.setSoTimeout(100000);

        //The server card stack
        serverDeck = new ProgramCardDeck();

        // The server board
        serverBoard = new Board("serverBoard" , "C:\\Users\\Morten\\IdeaProjects\\BeTheBee\\src\\main\\java\\inf112\\skeleton\\app\\assets\\DankBoard.json");
    }

    /**
     * Method for running the server
     */
    public void runServer() {
        while (true) {
            try {

                System.out.println("Waiting for client");
                Socket roboSocket = roboServer.accept();
                System.out.println("Client connected");

                oos = new ObjectOutputStream(roboSocket.getOutputStream());
                ois = new ObjectInputStream(roboSocket.getInputStream());

                Boolean gameOver = false;

                while (!gameOver) {
                    // Write server deck and board to client(s)
                    oos.writeObject(serverDeck);
                    oos.writeObject(serverBoard);

                    Boolean roundOver = false;
                    while (!roundOver) {

                        serverDeck = (ProgramCardDeck) ois.readObject();
                        serverBoard = (Board) ois.readObject();

                        System.out.println(serverDeck);
                        System.out.println(serverBoard);
                        roundOver = true;
                    }
                    gameOver = true;
                }

                //System.out.println(in.readUTF());

                //out.writeUTF("This be from dat server fam.");

                roboServer.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out.");
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }

            catch (ClassNotFoundException c) {
                c.printStackTrace();
                break;
            }

        }
    }
    public static void main (String[] args) throws IOException {

        RoboServer roboServer = new RoboServer(8000);
        roboServer.runServer();

    }
}
