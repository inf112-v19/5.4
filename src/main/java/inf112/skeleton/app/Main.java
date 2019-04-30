package inf112.skeleton.app;

import inf112.skeleton.app.Server.RoboClient;


public class Main {
    public static void main(String[] args) throws Exception{

        // Do we have the board file somewhere?
        //new GUIBoard("Gooblepoops", "C:\\Users\\Morten\\Desktop\\UiB\\INF112\\First Oblig\\TestBoard.json").displayBoard();


        RoboClient roboClient = new RoboClient("localhost", 8000);



        /**
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Robo Rally Rampage";
        cfg.width = 1200;
        cfg.height = 800;

        new LwjglApplication(new GUIMain(), cfg);
        */
    }
}