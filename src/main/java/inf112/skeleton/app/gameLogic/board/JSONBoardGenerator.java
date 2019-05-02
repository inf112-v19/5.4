package inf112.skeleton.app.gameLogic.board;


import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.board.pieces.*;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.game.FlagOrganizer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JSONBoardGenerator {

    JSONParser parser = new JSONParser();
    ICell[][] jsonBoardPieceList;
    private FlagOrganizer flags = FlagOrganizer.getInstance();

    public ICell[][] generateJsonBoard(String filepath) {
        try {

            System.out.println(new File("xxxxxxxx.").getAbsoluteFile());

            Object boardFile = parser.parse(new FileReader(filepath));

            JSONObject jsonBoardFile = (JSONObject) boardFile;
            int jsonSize = jsonBoardFile.size();
            jsonBoardPieceList = new ICell[jsonSize][jsonSize];
            System.out.println(jsonBoardFile);


            for (int y = 0; y <= jsonSize - 1; y++) {
                for (int x = 0; x <= jsonSize - 1; x++) {
                    Cell tempCell = new Cell();
                    jsonBoardPieceList[y][x] = tempCell;
                }
            }

            // Add all pieces.

            for (int y = 0; y <= jsonSize - 1; y++) {
                for (int x = 0; x <= jsonSize - 1; x++) {
                    ICell tempCell = jsonBoardPieceList[y][x];
                    String intX = Integer.toString(x);
                    String intY = Integer.toString(y);

                    JSONObject yCord = (JSONObject) jsonBoardFile.get(intY);
                    JSONArray xCord = (JSONArray) yCord.get(intX);
                    Iterator<String> iterator = xCord.iterator();

                    while (iterator.hasNext()) {

                        String jsonIterator = iterator.next();
                        System.out.println(jsonIterator);
                        switch (jsonIterator) {
                            case "Hole":
                                System.out.println("Making Hole!");
                                tempCell.addPiece(new Hole());
                                break;

                            case "NorthWall":
                                System.out.println("Making north wall!");
                                tempCell.addPiece(new Wall(Direction.NORTH));
                                addOppositeWall(x, y - 1, Direction.SOUTH);
                                break;

                            case "EastWall":
                                System.out.println("Making east wall!");
                                tempCell.addPiece(new Wall(Direction.EAST));
                                addOppositeWall(x + 1, y, Direction.WEST);
                                break;

                            case "SouthWall":
                                System.out.println("Making south wall!");
                                tempCell.addPiece(new Wall(Direction.SOUTH));
                                addOppositeWall(x, y + 1, Direction.NORTH);
                                break;

                            case "WestWall":
                                System.out.println("Making west wall!");
                                tempCell.addPiece(new Wall(Direction.WEST));
                                addOppositeWall(x - 1, y, Direction.EAST);
                                break;

                            case "ConveyorNorth":
                                System.out.println("Making northfacing conveyor!");
                                tempCell.addPiece(new Conveyor(Direction.NORTH));
                                break;

                            case "ConveyorEast":
                                System.out.println("Making eastfacing conveyor!");
                                tempCell.addPiece(new Conveyor(Direction.EAST));
                                break;

                            case "ConveyorSouth":
                                System.out.println("Making southfacing conveyor!");
                                tempCell.addPiece(new Conveyor(Direction.SOUTH));
                                break;

                            case "ConveyorWest":
                                System.out.println("Making westfacing conveyor!");
                                tempCell.addPiece(new Conveyor(Direction.WEST));
                                break;

                            case "FlagOne":
                                System.out.println("Making flag number 1 " + x + " " + y);
                                tempCell.addPiece(new Flag(1));
                                flags.setFlagAtPos(1, new Position(x, y));
                                break;

                            case "FlagTwo":
                                System.out.println("Making flag number 2");
                                tempCell.addPiece(new Flag(2));
                                flags.setFlagAtPos(2, new Position(x, y));
                                break;

                            case "FlagThree":
                                System.out.println("Making flag number 3");
                                tempCell.addPiece(new Flag(3));
                                flags.setFlagAtPos(3, new Position(x, y));
                                break;

                            case "FlagFour":
                                System.out.println("Making flag number 3");
                                tempCell.addPiece(new Flag(3));
                                flags.setFlagAtPos(4, new Position(x, y));
                                break;

                            case "GearRight":
                                System.out.println("Making gear rotating right");
                                tempCell.addPiece(new Gears(Action.ROTATE_R));
                                break;

                            case "GearLeft":
                                System.out.println("Making gear rotating left");
                                tempCell.addPiece(new Gears(Action.ROTATE_L));
                                break;

                            case "LaserShooterNorth1":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.NORTH, new Position(x, y), 1));
                                break;

                            case "LaserShooterNorth2":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.NORTH, new Position(x, y), 2));
                                break;

                            case "LaserShooterNorth3":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.NORTH, new Position(x, y), 3));
                                break;

                            case "LaserShooterEast1":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.EAST, new Position(x, y), 1));
                                break;

                            case "LaserShooterEast2":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.EAST, new Position(x, y), 2));
                                break;

                            case "LaserShooterEast3":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.EAST, new Position(x, y), 3));
                                break;

                            case "LaserShooterSouth1":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.SOUTH, new Position(x, y), 1));
                                break;

                            case "LaserShooterSouth2":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.SOUTH, new Position(x, y), 2));
                                break;

                            case "LaserShooterSouth3":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.SOUTH, new Position(x, y), 3));
                                break;

                            case "LaserShooterWest1":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.WEST, new Position(x, y), 1));
                                break;

                            case "LaserShooterWest2":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.WEST, new Position(x, y), 2));
                                break;

                            case "LaserShooterWest3":
                                System.out.println("Making northfacing lasershooter");
                                tempCell.addPiece(new LaserShooter(Direction.WEST, new Position(x, y), 3));
                                break;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonBoardPieceList;
    }

    public void addOppositeWall(int newX, int newY, Direction newWallDirection) {
        if (newY < jsonBoardPieceList.length && newY >= 0) {
            if (newX < jsonBoardPieceList[newY].length && newX >= 0) {
                if (jsonBoardPieceList[newY][newX] != null) {
                    ICell newCell = jsonBoardPieceList[newY][newX];
                    if (!containsOppositeWall(newCell, newWallDirection)) {
                        System.out.println("I'M PLACING A " + newWallDirection + " WALL AT " + newX + ", " + newY);
                        newCell.addPiece(new Wall(newWallDirection));
                    }
                }
            }
        }
    }

    public boolean containsOppositeWall(ICell newCell, Direction newWallDirection) {
        // Checks if the new cell has a wall.
        for (IPiece piece : newCell.getPiecesInCell()) {
            if (piece instanceof Wall) {
                if (piece.getPieceDirection() == newWallDirection) {
                    return true;
                }
            }
        }
        return false;
    }

    public FlagOrganizer getFlags() {
        return flags;
    }
}