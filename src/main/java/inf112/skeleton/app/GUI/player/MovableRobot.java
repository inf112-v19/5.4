package inf112.skeleton.app.GUI.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import inf112.skeleton.app.GUI.pieces.Robot;

public class MovableRobot extends Robot {

   public MovableRobot(int robotnr){

       super(robotnr);

       setBounds(getX(), getY(), getWidth(), getHeight());

       addListener(new InputListener() {
           @Override
           public boolean keyDown(InputEvent event, int keycode) {
               
               setOrigin(getWidth() / 2, getHeight() / 2);
               MoveByAction moveAction = new MoveByAction();
               moveAction.setDuration(0.3f);

               RotateByAction rotateByAction = new RotateByAction();

               // Move sound
               Sound moveSound = Gdx.audio.newSound(Gdx.files.internal("sound/move.mp3"));
               moveSound.play(0.4f,0.5f,1);
               //moveSound.dispose();


                // Set move amounts
               moveAction.setAmount(-getWidth(), 0f);
               moveAction.setInterpolation(Interpolation.pow3);


               switch (keycode) {
                   case Input.Keys.RIGHT:
                       moveAction.setAmount(getWidth(), 0f);

                       break;
                   case Input.Keys.LEFT:
                       moveAction.setAmount(-getWidth(), 0);

                       break;
                   case Input.Keys.DOWN:
                       moveAction.setAmount(0f, -getHeight());


                       break;
                   case Input.Keys.UP:
                       moveAction.setAmount(0f, getHeight());

                       break;


               }
               MovableRobot.this.addAction(moveAction);

               return true;
           }
       });



   }

}
