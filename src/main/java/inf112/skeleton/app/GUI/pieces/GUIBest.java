package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GUIBest extends GUIPiece {

    Texture danceSheet = new Texture(Gdx.files.internal("board/dancething.png"));

    private static final int FRAME_COLS = 1, FRAME_ROWS = 94;

    TextureRegion[] walkFrames;
    Animation<TextureRegion> laserAnimation;
    float animationTime;

    public GUIBest() {

        TextureRegion[][] tmp = TextureRegion.split(danceSheet,
                danceSheet.getWidth() / FRAME_COLS,
                danceSheet.getHeight() / FRAME_ROWS);


        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        laserAnimation = new Animation<TextureRegion>(0.07f, walkFrames);

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        animationTime = 0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = laserAnimation.getKeyFrame(animationTime, true);
        batch.draw(currentFrame, getX(),getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animationTime += delta;
    }
}
