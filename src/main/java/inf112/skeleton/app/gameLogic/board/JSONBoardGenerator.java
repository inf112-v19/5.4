package inf112.skeleton.app.gameLogic.board;


import inf112.skeleton.app.gameLogic.board.pieces.Flag;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
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

        ICell[][] jsonBoardPieceList2 = null;

        try {
            Object boardFile = parser.parse(new FileReader(filepath));
            JSONObject jsonBoardFile = (JSONObject) boardFile;
            int jsonSize = jsonBoardFile.size();
            int jsonSide = jsonSize / jsonSize;
            jsonBoardPieceList2 = new ICell[jsonSide][jsonSide];
            System.out.println(jsonBoardFile);
            for (int x = 0; x <= jsonSide; x++) {
                for (int y = 0; y <= jsonSide; y++) {

                    String intX = Integer.toString(x);
                    String intY = Integer.toString(y);

                    JSONObject xCord = (JSONObject) jsonBoardFile.get(intX);
                    JSONArray yCord = (JSONArray) xCord.get(intY);
                    Iterator<String> iterator = yCord.iterator();
                    while (iterator.hasNext()) {
                        Cell tempCell = new Cell();
                        jsonBoardPieceList2[x][y] = tempCell;
                        String jsonIterator = iterator.next();
                        System.out.println(jsonIterator);
                        switch (jsonIterator) {
                            case "Hole":
                                System.out.println("Making Hole!");
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
                            /**
                             case "ConveyorNorth":
                             System.out.println("Making northfacing conveyor!");
                             new Conveyor(Direction.NORTH, 1);
                             break;
                             case "ConveyorEast":
                             System.out.println("Making eastfacing conveyor!");
                             new Conveyor(Direction.EAST, 1);
                             break;
                             case "ConveyorSouth":
                             System.out.println("Making southfacing conveyor!");
                             new Conveyor(Direction.SOUTH, 1);
                             break;
                             case "ConveyorWest":
                             System.out.println("Making westfacing conveyor!");
                             new Conveyor(Direction.WEST, 1);
                             break;
                             */
                            case "FlagOne":
                                System.out.println("Making flag number 1");
                                tempCell.addPiece(new Flag(1));
                                break;
                            case "FlagTwo":
                                System.out.println("Making flag number 2");
                                new Flag(2);
                                break;
                            case "FlagThree":
                                System.out.println("Making flag number 3");
                                new Flag(3);
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
        return jsonBoardPieceList2;
    }
}