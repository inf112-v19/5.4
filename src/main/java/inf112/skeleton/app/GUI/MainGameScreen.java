package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.GUI.board.GUIBoard;
import inf112.skeleton.app.GUI.board.Stats;
import inf112.skeleton.app.GUI.cards.GUIDeck;
import inf112.skeleton.app.GUI.player.MovableGUIRobot;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;

import java.util.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MainGameScreen implements Screen {
	private Stage stage;
	private Skin skin;
	OrthographicCamera camera;
	ExtendViewport viewport;
	Music music;

	RoboRallyGame roboRallyGame;

	List<ProgramCard> pgCards;
	MovableGUIRobot overallHans;

	GUIDeck guiDeck;
	Cell guiDeckCell;

	public MainGameScreen(){

		playMusic();

		// Main stage
		camera = new OrthographicCamera();

        viewport = new ExtendViewport(1200, 1200, camera);
		stage = new Stage(viewport);

		// Main skin
		skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
		skin.getFont("font").getData().setScale(1.6f,1.6f);
		skin.get(Label.LabelStyle.class).fontColor = Color.WHITE;


		this.roboRallyGame = new RoboRallyGame(this);
		roboRallyGame.playGame();

		addPiecesTest();
		Gdx.input.setInputProcessor(stage);

		roboRallyGame.prePlay();
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

	public void addPiecesTest(){

		// Main table
		Table game = new Table();
		Table topBar = new Table();
		Table bottomBar = new Table();

		game.setFillParent(true);
		//game.setDebug(true);
		game.top().left();

		// Create GUIBoard.
		//GUIBoard GUIBoard = new GUIBoard(90, 10, 10);
		Board board = new Board("etamat", "DankBoard.json");
		board.displayBoard();
		int testBoardHeightUnitSize = board.getBoardHeight();
		GUIBoard GUIBoard = new GUIBoard(board);
		//GUIBoard.setDebug(true);

		// Add some pieces to the GUIBoard.
		//GUIBoard.addGUIPiece(3,3, new GUIRobot(1));
//		GUIBoard.addGUIPiece(3,3, new GUIRobot(0));
//		GUIBoard.addGUIPiece(1,3, new GUILaser());
//		GUIBoard.addGUIPiece(2,3, new GUILaser());
//		GUIBoard.addGUIPiece(4,3, new GUILaser());
//		GUIBoard.addGUIPiece(5,3, new GUILaser());
//
//
//		GUIBoard.addGUIPiece(1, 2, new GUIWall(Direction.WEST));
//		GUIBoard.addGUIPiece(1, 3, new GUIWall(Direction.NORTH));
//		GUIBoard.addGUIPiece(1, 4, new GUIWall(Direction.EAST));
//		GUIBoard.addGUIPiece(1, 5, new GUIWall(Direction.SOUTH));
//		GUIBoard.addGUIPiece(0,0, new GUIHole());
//		GUIBoard.addGUIPiece(1,0, new GUIHole());
//		GUIBoard.addGUIPiece(6,2, new GUIHole());
//		GUIBoard.addGUIPiece(8, 3, new GUIConveyor(Direction.NORTH));
//		GUIBoard.addGUIPiece(8, 4, new GUIConveyor(Direction.EAST));
//		GUIBoard.addGUIPiece(8, 5, new GUIConveyor(Direction.SOUTH));
//		GUIBoard.addGUIPiece(8, 6, new GUIConveyor(Direction.WEST));
//		GUIBoard.addGUIPiece(7, 6, new GUIFlag());
//		GUIBoard.addGUIPiece(5, 7, new GUIRepair());
//		GUIBoard.addGUIPiece(6, 8, new GUIGear(Rotation.R));
//		GUIBoard.addGUIPiece(8, 9, new GUIGear(Rotation.L));

		MovableGUIRobot hans = new MovableGUIRobot(1);
		this.overallHans = hans;
		GUIBoard.addGUIPiece(5,5, hans);




		// BOARD CREATION AND SETUP

		Stats stats = new Stats(skin);

		// Add everything to the main table.
		topBar.add().prefWidth(200);
		// GUIBoard add
		topBar.add(GUIBoard).top().center().expandX().padTop(30);
		// Stat add
		topBar.add(stats).top().left().pad(70);

		game.add(topBar).expandX();
		game.row();

		// Add the bottom bar and GUIDeck
		this.guiDeckCell = bottomBar.add().bottom().padBottom(30).padLeft(50).colspan(3);
		game.add(bottomBar).expand().fillY();

		// BASE ASSET TEST
		//game.addActor(new BaseAsset());

		// Add the main table - RoboRallyGame - to the stage.
		stage.addActor(game);

		stage.setKeyboardFocus(hans);

		//testMoveStuff(hans, GUIDeck);

		//pickCardPhase(new ProgramCardDeck().drawXCards(9));
	}

    /**
     *
     * Pick which cards touse
     * @param pgCards cards to pick from, usually 9
     * @return picked cards, usually 5
     */
    public void pickCardPhase(List<ProgramCard> pgCards){
    	Button doneButton = new TextButton("DONE", skin);
        this.guiDeck = new GUIDeck(skin, pgCards, doneButton);
        this.guiDeck.setProgramCards(pgCards);
		this.guiDeckCell.setActor(guiDeck);
		addPostPickListener(doneButton);

        this.guiDeck.pickCardsSetup();

    }


	private void addPostPickListener(Button doneButton) {
		doneButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {

				roboRallyGame.postPick(guiDeck.getPickedProgramCards());
			}
		});

	}

	public MovableGUIRobot gimmeRobotTest(){
    	return this.overallHans;
	}

}
