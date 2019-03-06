package inf112.skeleton.app.gameLogic.board;


import inf112.skeleton.app.gameLogic.board.pieces.*;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction ;
import org.json.simple.JSONArray;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONBoardGenerator {

    JSONParser parser = new JSONParser();

    public ICell[][] generateJsonBoard(String filepath) {

        ICell[][] jsonBoardPieceList = null;

        try {
            Object boardFile = parser.parse(new FileReader(filepath));
            JSONObject jsonBoardFile = (JSONObject) boardFile;
            int jsonSize = jsonBoardFile.size();
            int jsonSide = jsonSize / jsonSize;
            jsonBoardPieceList = new ICell[10][10];
            System.out.println(jsonBoardFile);
            for (int x = 0; x <= 9; x++) {
                for (int y = 0; y <= 9; y++) {
                    Cell tempCell = new Cell();
                    String intX = Integer.toString(x);
                    String intY = Integer.toString(y);

                    JSONObject xCord = (JSONObject) jsonBoardFile.get(intX);
                    JSONArray yCord = (JSONArray) xCord.get(intY);
                    Iterator<String> iterator = yCord.iterator();
                    while (iterator.hasNext()) {

                        jsonBoardPieceList[x][y] = tempCell;
                        String jsonIterator = iterator.next();
                        System.out.println(jsonIterator);
                        switch (jsonIterator) {
                            case "Hole":
                                System.out.println("Making Hole!");
                                tempCell.addPiece(new Hole());
                                break;

                            case "NorthWall":
                                System.out.println("Making northfacing wall!");
                                tempCell.addPiece(new Wall(Direction.NORTH));
                                break;

                            case "EastWall":
                                System.out.println("Making eastfacing wall!");
                                tempCell.addPiece(new Wall(Direction.EAST));
                                break;

                            case "SouthWall":
                                System.out.println("Making southfacing wall!");
                                tempCell.addPiece(new Wall(Direction.SOUTH));
                                break;

                            case "WestWall":
                                System.out.println("Making westfacing wall!");
                                tempCell.addPiece(new Wall(Direction.WEST));
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
                                System.out.println("Making flag number 1");
                                tempCell.addPiece(new Flag(1));
                                break;

                            case "FlagTwo":
                                System.out.println("Making flag number 2");
                                tempCell.addPiece(new Flag(2));
                                break;

                            case "FlagThree":
                                System.out.println("Making flag number 3");
                                tempCell.addPiece(new Flag(3));
                                break;

                            case "GearRight":
                                System.out.println("Making gear rotating right");
                                tempCell.addPiece(new Gears(Action.ROTATE_R));
                                break;

                            case "GearLeft":
                                System.out.println("Making gear rotating left");
                                tempCell.addPiece(new Gears(Action.ROTATE_L));
                                break;

                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonBoardPieceList;
    }
}