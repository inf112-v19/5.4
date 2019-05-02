package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GUILaser extends GUIPiece {

    Texture laserSheet = new Texture(Gdx.files.internal("board/lasersheet.png"));

    private static final int FRAME_COLS = 1, FRAME_ROWS = 5;

    TextureRegion[] walkFrames;
    Animation<TextureRegion> laserAnimation;
    float animationTime;

    public GUILaser() {

        TextureRegion[][] tmp = TextureRegion.split(laserSheet,
                laserSheet.getWidth() / FRAME_COLS,
                laserSheet.getHeight() / FRAME_ROWS);


        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        laserAnimation = new Animation<TextureRegion>(0.1f, walkFrames);

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
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animationTime += delta;
    }
}
