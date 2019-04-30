package inf112.skeleton.app;

import java.net.Socket;

public class RoboClient {

    public void tryConnection(String ip, int port) throws Exception {

        try (Socket roboClient = new Socket(ip, port)) {

            System.out.println("Connection accepted! Do some shit.");

        }
    }
}
