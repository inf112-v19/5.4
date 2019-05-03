package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.pieces.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Laser;
import inf112.skeleton.app.gameLogic.board.pieces.LaserShooter;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
import java.util.List;


public class LaserCalculator {

    private final List<Player> players;
    private List<LaserShooter> laserShooterList;
    private Board board;


    public LaserCalculator(Board board, List<Player> players) {

        this.laserShooterList = new ArrayList<>();
        this.board = board;
        this.players = players;

        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                for (IPiece piece : board.getCellAt(y, x).getPiecesInCell()) {
                    if (piece instanceof LaserShooter) {
                        this.laserShooterList.add((LaserShooter) piece);
                        break;
                    }
                }
            }
        }
    }

    public List<Laser> laserCalculation() {
        List<Laser> lasers = new ArrayList<>();
        for (LaserShooter laserShooter : laserShooterList) {
            tryToPlaceLaser(new Position(laserShooter.getPos().getX(), laserShooter.getPos().getY()), laserShooter.getPieceDirection(), laserShooter, lasers);

        }
        for (Player player : players) {
            int x = player.getPos().getX();
            int y = player.getPos().getY();
            placeLaser(player.getDirection(), x, y, player.getLaserShooter(), lasers);

        }

        // Add lasers to GUI
        return lasers;
    }


    /**
     * @param pos,            the position to place a laser
     * @param dir,            the direction the laser should be placed in
     * @param laserShooter,   the laserShooter that is shooting the laser
     * @param laserPositions, the list of all the positions lasers are placed in
     */
    public void tryToPlaceLaser(Position pos, Direction dir, LaserShooter laserShooter, List<Laser> laserPositions) {
        if (pos.getX() >= board.getBoardHeight() || pos.getY() >= board.getBoardWidth() || pos.getY() < 0 || pos.getX() < 0) {
            return;
        }
        if (board.getCellAt(pos).getPiecesInCell().contains(laserShooter)) {
            laserPositions.add(laserShooter.createNewLaser(pos));
            int x = pos.getX();
            int y = pos.getY();
            placeLaser(dir, x, y, laserShooter, laserPositions);

            return;
        }

        for (IPiece piece : board.getCellAt(pos).getPiecesInCell()) {
            if (piece instanceof Wall && piece.getPieceDirection().oppositeDir() == dir) {
                return;
            }
        }
        for (IPiece piece : board.getCellAt(pos).getPiecesInCell()) {
            if (piece instanceof Player) {
                ((Player) piece).takeDamage(laserShooter.getDamage());
                return;
            }
        }

        for (IPiece piece : board.getCellAt(pos).getPiecesInCell()) {
            if (piece instanceof Wall && piece.getPieceDirection() == dir) {
                laserPositions.add(laserShooter.createNewLaser(pos));
                return;
            }
        }

        laserPositions.add(laserShooter.createNewLaser(pos));
        int x = pos.getX();
        int y = pos.getY();
        placeLaser(dir, x, y, laserShooter, laserPositions);
    }


    public void placeLaser(Direction dir, int x, int y, LaserShooter laserShooter, List<Laser> laserPositions) {
        switch (dir) {
            case NORTH:
                tryToPlaceLaser(new Position(x, y - 1), dir, laserShooter, laserPositions);
                break;
            case EAST:
                tryToPlaceLaser(new Position(x + 1, y), dir, laserShooter, laserPositions);
                break;
            case SOUTH:
                tryToPlaceLaser(new Position(x, y + 1), dir, laserShooter, laserPositions);
                break;
            case WEST:
                tryToPlaceLaser(new Position(x - 1, y), dir, laserShooter, laserPositions);
                break;
        }
    }
}
