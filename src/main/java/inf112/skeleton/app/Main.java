package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import inf112.skeleton.app.GUI.MainGameScreen;


public class Main {
    public static void main(String[] args) {

        //new Board("Gooblepoops", "C:\\Users\\Morten\\Desktop\\UiB\\INF112\\First Oblig\\TestBoard.json").displayBoard();

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "hello-world";
        cfg.width = 1200;
        cfg.height = 1200;

        new LwjglApplication(new MainGameScreen(), cfg);
    }
}