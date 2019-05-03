package inf112.skeleton.app.GUI;


import com.badlogic.gdx.Game;

public class GUIMain extends Game {

    @Override
    public void create() {
        setScreen(new LoadScreen(this));
    }

    public void render() {
        super.render();
    }
}
