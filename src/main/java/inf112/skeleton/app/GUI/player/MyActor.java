package inf112.skeleton.app.GUI.player;


public class MyActor{



    /*public MyActor() {

        final player player = new player(new Position(xPos,yPos), Direction.NORTH, 9);

        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                setOrigin(getWidth()/2, getHeight()/2);
                MoveByAction moveByAction = new MoveByAction();
                RotateByAction rotateByAction = new RotateByAction();
                switch (keycode) {
                    case Input.Keys.LEFT:
                        rotateByAction.setAmount(90f);
                        rotateByAction.setDuration(0.3f);

                        MyActor.this.addAction(rotateByAction);
                        player.rotate(Rotation.L);
                        break;
                    case Input.Keys.RIGHT:
                        rotateByAction.setAmount(-90f);
                        rotateByAction.setDuration(0.3f);

                        MyActor.this.addAction(rotateByAction);
                        player.rotate(Rotation.R);
                        break;
                    case Input.Keys.UP:
                        switch (player.getDirection()) {
                            case NORTH:
                                if (player.getPos().getY() == 4){
                                    player.move(-1); break ;
                                }
                                moveByAction.setAmount(0f, 64f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                            case EAST:
                                if (player.getPos().getX() == 7){
                                    player.move(-1); break ;
                                }                                moveByAction.setAmount(64f, 0f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                            case SOUTH:
                                if (player.getPos().getY() == 0){
                                    player.move(-1); break ;
                                }
                                moveByAction.setAmount(0f, -64f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                            case WEST:
                                if (player.getPos().getX() == 0){
                                    player.move(-1); break ;
                                }
                                moveByAction.setAmount(-64f, 0f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                        }
                        player.move(1);
                        break;
                    case Input.Keys.DOWN:
                        switch (player.getDirection()) {
                            case NORTH:
                                if (player.getPos().getY() == 0){
                                    player.move(1);
                                    break ;
                                }
                                moveByAction.setAmount(0f, -64f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                            case EAST:
                                if (player.getPos().getX() == 0){
                                    player.move(1);
                                    break ;
                                }
                                moveByAction.setAmount(-64f, 0f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                            case SOUTH:
                                if (player.getPos().getY() == 4){
                                    player.move(1);
                                    break ;
                                }
                                moveByAction.setAmount(0f, 64f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                            case WEST:
                                if (player.getPos().getX() == 7){
                                    player.move(1);
                                    break ;
                                }
                                moveByAction.setAmount(64f, 0f);
                                moveByAction.setDuration(0.3f);

                                MyActor.this.addAction(moveByAction);
                                break;
                        }
                        player.move(-1);
                        break;
                }
                return true;
            }
        });
    }*/
}
