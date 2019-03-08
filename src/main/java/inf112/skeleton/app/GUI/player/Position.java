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
        return new Position(xPos, yPos+1);
    }
    public Position south() {
        return new Position(xPos, yPos-1);
    }
    public Position west() {
        return new Position(xPos-1, yPos);
    }
    public Position east() {
        return new Position(xPos+1, yPos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (xPos != position.xPos) return false;
        return yPos == position.yPos;
    }

    @Override
    public int hashCode() {
        int result = xPos;
        result = 31 * result + yPos;
        return result;
    }
}
