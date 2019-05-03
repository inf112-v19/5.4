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
import java.util.LinkedList;
import java.util.List;

public class JSONBoardGenerator {

    JSONParser parser = new JSONParser();
    ICell[][] jsonBoardPieceList;
    private FlagOrganizer flags = FlagOrganizer.getInstance();
    private List<SpawnPlatform> spawnPlatforms = new LinkedList<>();

    public ICell[][] generateJsonBoard(String filepath) {
        try {

            System.out.println(new File("xxxxxxxx.").getAbsoluteFile());

            Object boardFile = parser.parse(new FileReader(filepath));

            JSONObject jsonBoardFile = (JSONObject) boardFile;
            JSONObject sizeboi = (JSONObject) jsonBoardFile.get("-1");

            //System.out.println(sizeboi.values());
            Object[] objects = sizeboi.values().toArray();
            int width = Integer.parseInt(objects[0].toString());
            int height = Integer.parseInt(objects[1].toString());
            jsonBoardPieceList = new ICell[height][width];
            //System.out.println(jsonBoardFile);


            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Cell tempCell = new Cell();
                    jsonBoardPieceList[y][x] = tempCell;
                }
            }

            // Add all pieces.

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    ICell tempCell = jsonBoardPieceList[y][x];
                    String intY = Integer.toString(y);
                    String intX = Integer.toString(x);

                    JSONObject yCord = (JSONObject) jsonBoardFile.get(intY);
                    JSONArray xCord = (JSONArray) yCord.get(intX);
                    Iterator<String> iterator = xCord.iterator();

                    while (iterator.hasNext()) {

                        String jsonIterator = iterator.next();
                        System.out.println(jsonIterator);
                        switch (jsonIterator) {
                            case "SpawnPlatform1":
                                SpawnPlatform temp = new SpawnPlatform(1, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "SpawnPlatform2":
                                temp = new SpawnPlatform(2, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "SpawnPlatform3":
                                temp = new SpawnPlatform(3, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "SpawnPlatform4":
                                temp = new SpawnPlatform(4, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "SpawnPlatform5":
                                temp = new SpawnPlatform(5, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "SpawnPlatform6":
                                temp = new SpawnPlatform(6, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "SpawnPlatform7":
                                temp = new SpawnPlatform(7, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "SpawnPlatform8":
                                temp = new SpawnPlatform(8, new Position(x,y));
                                tempCell.addPiece(temp);
                                spawnPlatforms.add(temp);
                                break;
                            case "Repair":
                                tempCell.addPiece(new Repair());
                                break;
                            case "LeftGear":
                                tempCell.addPiece(new Gears(Action.ROTATE_L));
                                break;
                            case "RightGear":
                                tempCell.addPiece(new Gears(Action.ROTATE_R));
                                break;
                            case "Hole":
                                tempCell.addPiece(new Hole());
                                break;

                            case "NorthWall":
                                tempCell.addPiece(new Wall(Direction.NORTH));
                                addOppositeWall(x, y - 1, Direction.SOUTH);
                                break;

                            case "EastWall":
                                tempCell.addPiece(new Wall(Direction.EAST));
                                addOppositeWall(x + 1, y, Direction.WEST);
                                break;

                            case "SouthWall":
                                tempCell.addPiece(new Wall(Direction.SOUTH));
                                addOppositeWall(x, y + 1, Direction.NORTH);
                                break;

                            case "WestWall":
                                tempCell.addPiece(new Wall(Direction.WEST));
                                addOppositeWall(x - 1, y, Direction.EAST);
                                break;

                            case "ConveyorNorth":
                                tempCell.addPiece(new Conveyor(Direction.NORTH, 1));
                                break;

                            case "ConveyorEast":
                                tempCell.addPiece(new Conveyor(Direction.EAST, 1));
                                break;

                            case "ConveyorSouth":
                                tempCell.addPiece(new Conveyor(Direction.SOUTH, 1));
                                break;

                            case "ConveyorWest":
                                tempCell.addPiece(new Conveyor(Direction.WEST, 1));
                                break;
                            case "ConveyorNorth2":
                                tempCell.addPiece(new Conveyor(Direction.NORTH, 2));
                                break;

                            case "ConveyorEast2":
                                tempCell.addPiece(new Conveyor(Direction.EAST, 2));
                                break;

                            case "ConveyorSouth2":
                                tempCell.addPiece(new Conveyor(Direction.SOUTH, 2));
                                break;

                            case "ConveyorWest2":
                                tempCell.addPiece(new Conveyor(Direction.WEST, 2));
                                break;

                            case "FlagOne":
                                tempCell.addPiece(new Flag(1));
                                flags.setFlagAtPos(1, new Position(x, y));
                                break;

                            case "FlagTwo":
                                tempCell.addPiece(new Flag(2));
                                flags.setFlagAtPos(2, new Position(x, y));
                                break;

                            case "FlagThree":
                                tempCell.addPiece(new Flag(3));
                                flags.setFlagAtPos(3, new Position(x, y));
                                break;

                            case "FlagFour":
                                tempCell.addPiece(new Flag(4));
                                flags.setFlagAtPos(4, new Position(x, y));
                                break;
                            case "FlagFive":
                                tempCell.addPiece(new Flag(5));
                                flags.setFlagAtPos(5, new Position(x, y));
                                break;
                            case "FlagSix":
                                tempCell.addPiece(new Flag(6));
                                flags.setFlagAtPos(6, new Position(x, y));
                                break;

                            case "GearRight":
                                tempCell.addPiece(new Gears(Action.ROTATE_R));
                                break;

                            case "GearLeft":
                                tempCell.addPiece(new Gears(Action.ROTATE_L));
                                break;

                            case "LaserShooterNorth1":
                                tempCell.addPiece(new LaserShooter(Direction.NORTH, new Position(x, y), 1));
                                break;

                            case "LaserShooterNorth2":
                                tempCell.addPiece(new LaserShooter(Direction.NORTH, new Position(x, y), 2));
                                break;

                            case "LaserShooterNorth3":
                                tempCell.addPiece(new LaserShooter(Direction.NORTH, new Position(x, y), 3));
                                break;

                            case "LaserShooterEast1":
                                tempCell.addPiece(new LaserShooter(Direction.EAST, new Position(x, y), 1));
                                break;

                            case "LaserShooterEast2":
                                tempCell.addPiece(new LaserShooter(Direction.EAST, new Position(x, y), 2));
                                break;

                            case "LaserShooterEast3":
                                tempCell.addPiece(new LaserShooter(Direction.EAST, new Position(x, y), 3));
                                break;

                            case "LaserShooterSouth1":
                                tempCell.addPiece(new LaserShooter(Direction.SOUTH, new Position(x, y), 1));
                                break;

                            case "LaserShooterSouth2":
                                tempCell.addPiece(new LaserShooter(Direction.SOUTH, new Position(x, y), 2));
                                break;

                            case "LaserShooterSouth3":
                                tempCell.addPiece(new LaserShooter(Direction.SOUTH, new Position(x, y), 3));
                                break;

                            case "LaserShooterWest1":
                                tempCell.addPiece(new LaserShooter(Direction.WEST, new Position(x, y), 1));
                                break;

                            case "LaserShooterWest2":
                                tempCell.addPiece(new LaserShooter(Direction.WEST, new Position(x, y), 2));
                                break;

                            case "LaserShooterWest3":
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

    public List<SpawnPlatform> getSpawnPlatforms(){
        return spawnPlatforms;
    }
}