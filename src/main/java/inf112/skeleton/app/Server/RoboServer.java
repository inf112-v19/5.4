package inf112.skeleton.app.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RoboServer {

    private ServerSocket roboServer;
    private int playerNumber;
    private ServerSideConnection player1;
    private ServerSideConnection player2;
    private ServerSideConnection player3;

    public static void main(String[] args) {
        RoboServer rs = new RoboServer();
        rs.acceptConnections();
    }

    public RoboServer() {
        System.out.println("--- Server for RoboRally ---");
        playerNumber = 0;
        try {
            roboServer = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");
            while (playerNumber < 3) {
                Socket ssocket = roboServer.accept();
                playerNumber++;
                System.out.println("Player connected nr. " + playerNumber + " joined.");
                ServerSideConnection ssc = new ServerSideConnection(ssocket, playerNumber);
                if (playerNumber == 1) {
                    player1 = ssc;
                } else if (playerNumber == 2) {
                    player2 = ssc;
                } else {
                    player3 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();
            }
            System.out.println("RoboRally server is not longer accepting connections.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServerSideConnection implements Runnable {

        private Socket socket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private int playerID;

        public ServerSideConnection(Socket s, int id) {
            socket = s;
            playerID = id;
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                System.out.println("We're inside run fam");
                oos.writeObject(playerID);
                oos.flush();

                while (true) {

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
