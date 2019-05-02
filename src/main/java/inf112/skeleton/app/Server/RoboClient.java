package inf112.skeleton.app.Server;


import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RoboClient {

    public static void main(String[] args) throws IOException
    {
        try {
            Scanner scn = new Scanner(System.in);
            Scanner msg = new Scanner(System.in);
            System.out.println("Enter the server ip and port");
            System.out.println("ip:");
            String ip = scn.nextLine();
            System.out.println("Port:");
            int port = scn.nextInt();


            Socket roboClient = new Socket(ip, 8000);
            System.out.println("Connection accepted! Do some shit.");

            ObjectInputStream ois = new ObjectInputStream(roboClient.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(roboClient.getOutputStream());

            RoboRallyGame clientGame = new RoboRallyGame()
            /**
            System.out.println("Type some shit to the server");
            String message = msg.nextLine();
            oos.writeObject(message);
            ProgramCardDeck clientDeck = (ProgramCardDeck)ois.readObject();
            System.out.println(clientDeck);
            oos.writeObject(clientDeck);
             */
        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
