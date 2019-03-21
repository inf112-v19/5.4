package inf112.skeleton.app;

import java.net.ServerSocket;
import java.net.Socket;

public class RoboServer {

    void main(String[] args) throws Exception {

        ServerSocket roboServer;
        int port = 8000;

            roboServer = new ServerSocket(port);
            System.out.println("Listening for connection on port " + port + "...");

            while (true) {
                try (Socket roboClient = roboServer.accept()) {
                    System.out.println("Connection request accepted");
                }
            }
        }
    }
