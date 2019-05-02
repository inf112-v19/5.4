package inf112.skeleton.app.Server;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.GUI.GUIMain;
import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RoboClient {

    public static void main(String[] args) throws IOException {

        try {

            LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
            cfg.title = "Robo Rally Rampage";
            cfg.width = 1200;
            cfg.height = 800;

            new LwjglApplication(new GUIMain(), cfg);

            Scanner scn = new Scanner(System.in);
            Scanner msg = new Scanner(System.in);
            System.out.println("Enter the server ip and port");
            System.out.println("ip:");
            String ip = scn.nextLine();
            System.out.println("Port:");
            int port = scn.nextInt();


            Socket roboClient = new Socket(ip, port);
            System.out.println("Connection accepted! Do some shit.");

            ObjectInputStream ois = new ObjectInputStream(roboClient.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(roboClient.getOutputStream());

            System.out.println("Enter your name: ");
            String playername = msg.nextLine();
            oos.writeObject(playername);

            while (true) {



            }
        }
        catch (IOException e) {
            System.out.println(e);
        }  //catch (ClassNotFoundException e) {
            //e.printStackTrace();
    }
}

