package inf112.skeleton.app;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.Board;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class RoboServer extends Thread {

    private ServerSocket roboServer;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectInputStream ois;

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
    public void runServer() {
        while (true) {
            try {

                System.out.println("Waiting for client");
                Socket roboSocket = roboServer.accept();
                System.out.println("Client connected");

                in = new DataInputStream(roboSocket.getInputStream());
                out = new DataOutputStream(roboSocket.getOutputStream());
                ois = new ObjectInputStream(roboSocket.getInputStream());

                //System.out.println(in.readUTF());

                //out.writeUTF("This be from dat server fam.");

                Board serverBoard = (Board)ois.readObject();
                System.out.println(serverBoard);
                System.out.println(serverBoard.getBoardHeight());
                System.out.println(serverBoard.getCellAt(1,1).getPiecesInCell());

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
