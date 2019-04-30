package inf112.skeleton.app.GUI;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GUIMain extends Game {

    OrthographicCamera camera;
    ExtendViewport viewport;

    @Override
    public void create() {
        //camera = new OrthographicCamera();
        //viewport = new ExtendViewport(1200, 1200, camera);
        setScreen(new LoadScreen(this));
    }

    public void render() {
        super.render();
    }
}
