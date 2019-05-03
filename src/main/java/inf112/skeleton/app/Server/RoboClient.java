package inf112.skeleton.app.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RoboClient {

    private int playerID;
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Scanner scn;

    public RoboClient() {
        System.out.println("--- Client ---");
        try {
            Scanner scn = new Scanner(System.in);
            Scanner msg = new Scanner(System.in);
            System.out.println("Enter the server ip and port");
            System.out.println("ip:");
            String ip = scn.nextLine();
            System.out.println("Port:");
            int port = scn.nextInt();

            clientSocket = new Socket(ip, port);
            System.out.println("Connection accepted! Do some shit.");

            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());

            playerID = (int) ois.readObject();
            System.out.println("Your playerID is " + playerID + "!");

            while (true) {

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getPlayerID() {
        return playerID;
    }

    public static void main(String[] args) throws IOException {
        RoboClient rc = new RoboClient();
    }
}

