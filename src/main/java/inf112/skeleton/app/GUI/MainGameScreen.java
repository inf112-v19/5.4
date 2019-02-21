package inf112.skeleton.app.GUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.GUI.board.Board;
import inf112.skeleton.app.GUI.board.Stats;
import inf112.skeleton.app.GUI.pieces.Laser;
import inf112.skeleton.app.GUI.pieces.Robot;
import inf112.skeleton.app.GUI.cards.Deck;
import inf112.skeleton.app.GUI.player.MovableRobot;

public class MainGameScreen implements Screen {
	private Stage stage;
	private Skin skin;
	OrthographicCamera camera;
	ExtendViewport viewport;
	Music music;

	public MainGameScreen(){


		playMusic();

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
		//game.setDebug(true);
		game.top().left();

		// Create board.
		Board board = new Board(90, 10, 10);
		//board.setDebug(true);
		board.padTop(30);

		// Add some pieces to the board.
		board.addPiece(3,3, new Robot(1));
		board.addPiece(3,2, new Robot(0));
		board.addPiece(2,3, new Laser());
		board.addPiece(4,3, new Laser());
		board.addPiece(7,7, new Laser());
		MovableRobot hans = new MovableRobot(1);
		board.addPiece(5,5, hans);


		// Create cards
		Deck deck = new Deck(skin);

		// Create stat thingy
		Stats stats = new Stats(skin);

		// Add everything to the main table.
		game.add(board).expandX().top().center();
		game.add(stats).expandX().top().left().pad(40);
		game.row();
		game.add(deck).bottom().padBottom(30);

        // BASE ASSET TEST
        game.addActor(new BaseAsset());

		// Add the main table - Game - to the stage.
		stage.addActor(game);

		Gdx.input.setInputProcessor(stage);
		stage.setKeyboardFocus(hans);
	}

	@Override
	public void show() {
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());

	}

	@Override
	public void dispose() {
		stage.dispose();
		music.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	public void playMusic(){
		// Play music
		//music = Gdx.audio.newMusic(Gdx.files.internal("sound/Rally_Roller.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/demoMarbles.mp3"));
		music.setVolume(0.32f);                 // sets the volume to half the maximum volume
		music.setLooping(true);                // will repeat playback until music.stop() is called
		//music.stop();                          // stops the playback
		//music.pause();                         // pauses the playback
		music.play();                          // resumes the playback
		boolean isPlaying = music.isPlaying(); // obvious :)
		boolean isLooping = music.isLooping(); // obvious as well :)
		float position = music.getPosition();  // returns the playback position in seconds
	}

}
