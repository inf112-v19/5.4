package inf112.skeleton.app.gameLogic.enums;

import inf112.skeleton.app.gameLogic.board.pieces.Conveyor;
import inf112.skeleton.app.gameLogic.board.pieces.Gears;
import inf112.skeleton.app.gameLogic.board.pieces.Hole;
import inf112.skeleton.app.gameLogic.board.pieces.Laser;

public enum PiecesToCheckFor {
    CONVEYOR(Conveyor.class),
    GEAR(Gears.class),
    HOLE(Hole.class),
    LASER(Laser.class);


    private Class pieceClass;

    PiecesToCheckFor(Class pieceClass) {
        this.pieceClass = pieceClass;
    }

    public Class getPieceClass() {
        return pieceClass;
    }
}
