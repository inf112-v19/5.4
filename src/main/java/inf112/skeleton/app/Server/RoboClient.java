package inf112.skeleton.app.Server;


import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RoboClient {

    public static void main(String[] args) throws IOException
    {
        try {

            Socket roboClient = new Socket("localhost", 8000);
            System.out.println("Connection accepted! Do some shit.");

            ObjectInputStream ois = new ObjectInputStream(roboClient.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(roboClient.getOutputStream());
            Scanner scn = new Scanner(System.in);

            String message = scn.nextLine();

            oos.writeObject(message);
            ProgramCardDeck clientDeck = (ProgramCardDeck)ois.readObject();
            System.out.println(clientDeck);
            oos.writeObject(clientDeck);


        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
