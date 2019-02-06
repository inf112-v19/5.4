package inf112.skeleton.app.GUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.GUI.Pieces.Laser;
import inf112.skeleton.app.GUI.Pieces.Robot;
import inf112.skeleton.app.GUI.Cards.Deck;

public class Scene2DGangBangChang extends ApplicationAdapter {
	private Stage stage;
	private Skin skin;
	OrthographicCamera camera;
	ExtendViewport viewport;

	@Override
	public void create () {

		// Main stage
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(1200, 1200, camera);
		stage = new Stage(viewport);

		// Main skin
		skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
		skin.getFont("font").getData().setScale(1.6f,1.6f);

		// Main table
		Table game = new Table();
		game.setFillParent(true);
		game.setDebug(true);
		game.top().left();
		//game.defaults().left();

		// Create board
		Board board = new Board(90, 10, 10);
		//board.setDebug(true);
		board.padTop(30);

		// Add some shit
		board.addPiece(3,3, new Robot(1));
		board.addPiece(3,2, new Robot(0));
		board.addPiece(2,3,new Laser());
		board.addPiece(4,3,new Laser());

		// Create cards
		Deck deck = new Deck(skin);

		// Create stat thingy
		Stats stats = new Stats(skin);

		// Add everything to the main table.
		game.add(board).expandX().top().center();
		game.add(stats).expandX().top().left().pad(40);
		game.row();
		game.add(deck).bottom().padBottom(30);

		stage.addActor(game);

		// CARD
		//Card actor = new Card();
		//stage.addActor(actor);

		// BASE ASSET TEST
		//stage.addActor(new BaseAsset());

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);

	}

}
