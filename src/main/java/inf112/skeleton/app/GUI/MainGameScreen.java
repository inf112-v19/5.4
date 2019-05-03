package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.GUI.board.GUIBoard;
import inf112.skeleton.app.GUI.board.Stats;
import inf112.skeleton.app.GUI.cards.GUIDeck;
import inf112.skeleton.app.GUI.player.MovableGUIRobot;
import inf112.skeleton.app.GUI.stages.GameOverStage;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;

import java.util.List;

public class MainGameScreen implements Screen {

    private GUIBoard piecesBoard;
    private GUIBoard robotsBoard;

    private Stage stage;
    private Skin skin;
    private Stats stats;

    private boolean isPvpMatch;
    OrthographicCamera camera;
    ExtendViewport viewport;
    Music music;

    RoboRallyGame roboRallyGame;
    List<Player> players;

    List<ProgramCard> pgCards;
    MovableGUIRobot currentMovableRobot;

    GUIDeck guiDeck;
    Cell guiDeckCell;

    public MainGameScreen(int numberOfPlayers, boolean isPvpMatch) {
        this.isPvpMatch = isPvpMatch;
        //SoundPlayer.GameSound.PLAY_MUSIC.playSound();

        // Main stage
        camera = new OrthographicCamera();

        viewport = new ExtendViewport(1200, 1200, camera);
        stage = new Stage(viewport);


        // Main skin
        skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
        skin.getFont("font").getData().setScale(1.6f, 1.6f);
        skin.get(Label.LabelStyle.class).fontColor = Color.WHITE;


        this.roboRallyGame = new RoboRallyGame(this, numberOfPlayers);

        // Get the players from the game.
        this.players = roboRallyGame.getPlayers();

        this.piecesBoard = new GUIBoard(this.roboRallyGame.getBoard());
        this.robotsBoard = new GUIBoard(this.roboRallyGame.getBoard());
        this.robotsBoard.makeBoardInvisible();
        //piecesBoard.addPlayers(players);



        addPiecesTest();

        //this.addPlayers(players);
        robotsBoard.addPlayers(players);

        Gdx.input.setInputProcessor(stage);

		piecesBoard.lightUpTile(0,0);
		piecesBoard.resetTileColor(0,0);
		//piecesBoard.lightUpTile(2,0);
		//piecesBoard.lightUpTile(0,1);

        roboRallyGame.prePlay();

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
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

    public void playMusic() {
        // Play music
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/music/Rally_Roller.mp3"));
        //music = Gdx.audio.newMusic(Gdx.files.internal("audio/demoMarbles.mp3"));
        music.setVolume(0.32f);                 // sets the volume to half the maximum volume
        music.setLooping(true);                // will repeat playback until music.stop() is called
        //music.stop();                          // stops the playback
        //music.pause();                         // pauses the playback
        music.play();                          // resumes the playback
        boolean isPlaying = music.isPlaying(); // obvious :)
        boolean isLooping = music.isLooping(); // obvious as well :)
        float position = music.getPosition();  // returns the playback position in seconds
    }

    public void addPiecesTest() {

        // Main table
        Table game = new Table();
        Table topBar = new Table();
        Table bottomBar = new Table();

        game.setFillParent(true);
        //game.setDebug(true);
        game.top().left();

        // Create piecesBoard.
        //piecesBoard piecesBoard = new piecesBoard(90, 10, 10);

        //piecesBoard.setDebug(true);

        //MovableGUIRobot hans = new MovableGUIRobot(1);
        //this.currentMovableRobot = hans;
        //piecesBoard.addGUIPiece(5,5, hans);
        //piecesBoard.addGUIPiece(5,5, new GUIBest());

        // BOARD CREATION AND SETUP

        Stack boards = new Stack();
        boards.add(piecesBoard);
        boards.add(robotsBoard);

        this.stats = new Stats(skin, players.get(0));

        // Add everything to the main table.
        topBar.add().prefWidth(200);
        // piecesBoard add
        topBar.add(boards).top().center().expandX().padTop(30).height(900);
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

        //stage.setKeyboardFocus(hans);

        //pickCardPhase(new ProgramCardDeck().drawXCards(9));
    }

    /**
     * Pick which cards touse
     *
     * @param pgCards cards to pick from, usually 9
     * @return picked cards, usually 5
     */
    public void pickCardPhase(List<ProgramCard> pgCards, Player currentPlayer) {
        this.updateStats(currentPlayer);
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

				List<ProgramCard> playerCards = guiDeck.getPickedProgramCards();
				roboRallyGame.getCardPicker().postPick(playerCards);
			}
		});

    }

    public GUIBoard getGUIBoard() {
        return this.piecesBoard;
    }

    public void addPlayers(Player[] players) {
        for (Player player : players) {
            MovableGUIRobot robot = player.getRobot();
            int playerX = player.getPos().getX();
            int playerY = player.getPos().getY();
            float[] positions = piecesBoard.getPiecePos(playerX, playerY);
            Vector2 coords = piecesBoard.getCoords(playerX, playerY);
            robot.localToStageCoordinates(coords);
            stage.stageToScreenCoordinates(coords);

            robot.setPosition(coords.x, coords.y);

            //robot.setPosition(positions[0], positions[1]);
            stage.addActor(player.getRobot());
        }

    }

    public void updateStats(Player player){
        this.stats.updateStats(player);
    }


    public void gameOver(Player player) {
        this.stage = new GameOverStage(viewport, this.skin ,player);
    }
    public boolean getIsPvpMatch() { return isPvpMatch; }
}
