package inf112.skeleton.app.GUI.player;

public class Position {
    private final int xPos;
    private final int yPos;


    public int getX(){
        return xPos;
    }
    public int getY(){
        return yPos;
    }

    public Position(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public Position north() {
        return new Position(xPos, yPos-1);
    }
    public Position south() {
        return new Position(xPos, yPos+1);
    }
    public Position west() {
        return new Position(xPos-1, yPos);
    }
    public Position east() {
        return new Position(xPos+1, yPos);
    }
}
