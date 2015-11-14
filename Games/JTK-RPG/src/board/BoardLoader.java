/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board;

import board.tile.TileAbstract;
import files.FileSystem;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class BoardLoader
{
    
    public static BoardData load(String path)
    {
        // Load the data file
        ArrayList<String> data = FileSystem.loadFile(path, true);
        
        // Create an empty board
        BoardData board = new BoardData(data.get(0));
        int sizeW = Integer.parseInt(data.get(1).split("\\|")[0]);
        int sizeH = Integer.parseInt(data.get(1).split("\\|")[1]);
        board.setSize(sizeW, sizeH);
        
        // Terrain
        String[] dataTile;
        int dataLine = 2;
        for(int tileX = 0; tileX < sizeW; tileX++)
        {
            for(int tileY = 0; tileY < sizeH; tileY++)
            {
                dataTile = data.get(dataLine).split("\\|");
                board.setTileAt(tileX, tileY, new TileAbstract(dataTile[0], Integer.parseInt(dataTile[1]), Integer.parseInt(dataTile[2])));
                dataLine += 1;
            }
        }
        
        // Harvest
        
        // Zones
        
        // Return the board
        return board;
    }

}