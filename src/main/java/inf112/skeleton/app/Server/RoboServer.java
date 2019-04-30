package inf112.skeleton.app.Server;

import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class RoboServer {

    private ServerSocket roboServer;
    private Socket roboSocket;
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
    }

    /**
     * Method for running the server
     */
    public void runServer() throws IOException {
        while (true) {
            roboSocket = null;
            try {
                //Listeners for the clients / players
                System.out.println("Waiting for client");
                roboSocket = roboServer.accept();
                System.out.println("Client connected");

                oos = new ObjectOutputStream(roboSocket.getOutputStream());
                ois = new ObjectInputStream(roboSocket.getInputStream());

                System.out.println("Assigning thread to client");

                Thread t = new RoboClientHandler(roboSocket, ois, oos);
                t.start();


                //roboServer.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out.");
                break;
            }
            catch (Exception e) {
                roboSocket.close();
                e.printStackTrace();
            }
        }
    }
    public static void main (String[] args) throws IOException {

        RoboServer roboServer = new RoboServer(8000);
        roboServer.runServer();
    }
}
