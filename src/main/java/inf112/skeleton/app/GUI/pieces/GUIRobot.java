package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class    GUIRobot extends GUIPiece {
        String path = "bots/";
        Texture danceSheet;

        public GUIRobot(int robotNumber){
                System.out.println("YEEEHAAAW");
                //System.out.println(Gdx.files.getLocalStoragePath());
                //danceSheet = new Texture(Gdx.files.internal("board/dancething.png"));
                danceSheet = new Texture(Gdx.files.internal("board/dancething.png"));

//                switch (robotNumber){
//                        case 0:
//                                path += "rolobot-alpha/rolobot-alpha.png";
//                                break;
//                        case 1:
//                                path += "sputnik/yellow_robot.png";
//                                break;
//                }
//
//                sprite = new Sprite(new Texture(Gdx.files.internal(path)));

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


        private static final int FRAME_COLS = 1, FRAME_ROWS = 94;

        TextureRegion[] walkFrames;
        Animation<TextureRegion> laserAnimation;
        float animationTime;
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

        /*@Override
        public void draw(Batch batch, float parentAlpha) {
           //batch.enableBlending();
            batch.draw(sprite, getX(),getY(), getWidth(), getHeight());
        }*/
}
