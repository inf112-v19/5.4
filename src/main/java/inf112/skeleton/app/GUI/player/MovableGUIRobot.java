package inf112.skeleton.app.GUI.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import inf112.skeleton.app.GUI.SoundPlayer;
import inf112.skeleton.app.GUI.pieces.GUIRobot;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class MovableGUIRobot extends GUIRobot {

    TextureAtlas textureAtlas;
    Sprite newSprite;
    Direction robotFacingDir;

    public MovableGUIRobot(int robotnr) {

        super(robotnr);
        setBounds(getX(), getY(), getWidth(), getHeight());
        this.robotFacingDir = Direction.NORTH;

        textureAtlas = new TextureAtlas("bots/yellowBot/yellow_bot_sprites.txt");
        changeSprite(this.robotFacingDir);
    }

    public com.badlogic.gdx.scenes.scene2d.Action getGUIAction(ActionType actionType, final Direction faceDir) {
        setOrigin(getWidth() / 2, getHeight() / 2);

        ParallelAction parallelAction = new ParallelAction();

        switch (actionType) {
            case MOVE:

                parallelAction.addAction(new RunnableAction() {
                    @Override
                    public void run() {
                        SoundPlayer.GameSound.MOVE.playSound();
                    }
                });

                // The move audio
                MoveByAction moveAction = new MoveByAction();
                moveAction.setDuration(0.3f);
                moveAction.setInterpolation(Interpolation.pow3);

                switch (faceDir) {
                    case NORTH:
                        moveAction.setAmount(0f, getHeight());
                        break;
                    case EAST:
                        moveAction.setAmount(getWidth(), 0f);
                        break;
                    case WEST:
                        moveAction.setAmount(-getWidth(), 0);
                        break;
                    case SOUTH:
                        moveAction.setAmount(0f, -getHeight());
                        break;

                }

                parallelAction.addAction(moveAction);
                return parallelAction;

            case ROTATE:


                parallelAction.addAction(new RunnableAction() {
                    @Override
                    public void run() {
                        //SoundPlayer.GameSound.ROTATE.playSound();
                    }
                });

                final Direction innerDir = faceDir;

                parallelAction.addAction(new RunnableAction() {
                    @Override
                    public void run() {
                        changeSprite(innerDir);
                    }
                });

                return parallelAction;

        }
        return null;
    }

    public void changeSprite(Direction facingDir) {
        String spriteString = "yellow_robot_";
        switch (facingDir) {
            case NORTH:
                spriteString += "back";
                break;
            case EAST:
                spriteString += "right";
                break;
            case WEST:
                spriteString += "left";
                break;
            case SOUTH:
                spriteString += "front";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }

}
