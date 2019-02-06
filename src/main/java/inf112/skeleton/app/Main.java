package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import inf112.skeleton.app.Game.HelloWorld;
import inf112.skeleton.app.GUI.Scene2DGangBangChang;


public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "hello-world";
        cfg.width = 480;
        cfg.height = 320;

        new LwjglApplication(new com.jarlescene2d.Scene2DGangBangChang(), cfg);
    }
}