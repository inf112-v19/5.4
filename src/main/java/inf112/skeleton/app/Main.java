package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.GUI.GUIMain;


public class Main {
    public static void main(String[] args) throws Exception {

        // Do we have the board file somewhere?
        //new GUIBoard("Gooblepoops", "C:\\Users\\Morten\\Desktop\\UiB\\INF112\\First Oblig\\TestBoard.json").displayBoard();

        /**
         RoboClient roboClient = new RoboClient();
         roboClient.tryConnection("localhost", 8000);
         */
        //

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Robo Rally Rampage";
        cfg.width = 1200;
        cfg.height = 800;
        //cfg.fullscreen = true;

        new LwjglApplication(new GUIMain(), cfg);

    }
}