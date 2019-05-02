package inf112.skeleton.app.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RoboServer {
    public static void main (String[] args) throws IOException {

        ServerSocket roboServer = new ServerSocket(8000);

        while (true) {
            Socket roboSocket = null;
            try {
                //Listeners for the clients / players
                System.out.println("Waiting for client");
                roboSocket = roboServer.accept();
                System.out.println("Client connected");



                ObjectOutputStream oos = new ObjectOutputStream(roboSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(roboSocket.getInputStream());

                System.out.println("Assigning thread to client");
                Thread t = new RoboClientHandler(roboSocket, ois, oos);
                t.start();
                System.out.println("Thread created. ID: " + t.getId());
                System.out.println(Thread.activeCount());
                // roboServer.close();
            }
            catch (Exception e) {
                roboSocket.close();
                e.printStackTrace();
                break;
            }
        }
    }
}
