package inf112.skeleton.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class RoboServer extends Thread {

    private ServerSocket roboServer;
    private DataInputStream in;
    private DataOutputStream out;

    public RoboServer (int port) throws IOException {
        roboServer = new ServerSocket(port);
        roboServer.setSoTimeout(100000);
    }
    public void runServer() {
        while (true) {
            try {

                System.out.println("Waiting for client");
                Socket roboSocket = roboServer.accept();
                System.out.println("Client connected");

                in = new DataInputStream(roboSocket.getInputStream());
                out = new DataOutputStream(roboSocket.getOutputStream());

                System.out.println(in.readUTF());

                out.writeUTF("This be from dat server fam.");

                roboServer.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out.");
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main (String[] args) throws IOException {
        RoboServer roboServer = new RoboServer(8000);
        roboServer.runServer();
    }
}
