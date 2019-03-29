package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;

public class RespawnPoint {
    int nextFlag;
    Position pos;

    public RespawnPoint(Position pos, int firstFlag){
        this.pos = pos;
        this.nextFlag = firstFlag;

    }

    public int getNextFlag() {
        return nextFlag;
    }

    public Position getPos() {
        return pos;
    }

    public void setNextFlag() {
        nextFlag++;
    }

    public void setPos(Position pos) {
        this.pos = pos;

    }
}
