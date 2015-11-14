/*
 * Snake
 * Jamie Purchase
 */
package world;

import file.FileService;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jamie
 */
public class World
{
    private String key;
    private int worldW, worldH;
    private HashMap<String, WorldTile> tiles;
    private BufferedImage terrain;
    
    public World(String key, int worldW, int worldH, WorldTerrain terrain, Solidity solidity)
    {
        this.key = key;
        this.worldW = worldW;
        this.worldH = worldH;
        this.tiles = new HashMap();
        this.terrain = null;
        this.buildTiles(terrain, solidity);
    }
    
    public void buildTiles(WorldTerrain terrain, Solidity solidity)
    {
        Location location;
        for(int x = 0; x < this.worldW; x++)
        {
            for(int y = 0; y < this.worldH; y++)
            {
                location = new Location(x, y);
                this.tiles.put(location.asKey(), new WorldTile(location, terrain, solidity));
            }
        }
    }
    
    public void fileSave()
    {
        ArrayList<String> data = new ArrayList();
        data.add("WORLD~");
        data.add("KEY~" + this.key);
        data.add("SIZE~" + this.worldW + "|" + this.worldH);
        Location location;
        for(int x = 0; x < this.worldW; x++)
        {
            for(int y = 0; y < this.worldH; y++)
            {
                location = new Location(x, y);
                data.add("TILE~" + this.tiles.get(location.asKey()).asData());
            }
        }
        data.add("end~");
        FileService.saveFile(data, "C:/Users/Jamie/Documents/Snake.jtk");
    }
    
    public BufferedImage getTerrain()
    {
        if(this.terrain == null) {this.terrain = this.getTerrainImage();}
        return this.terrain;
    }
    
    public BufferedImage getTerrainArea(Graphics g, Rectangle drawRect, int scrollX, int scrollY)
    {
        return this.getTerrain().getSubimage(scrollX * 50, scrollY * 50, drawRect.width, drawRect.height);
    }
    
    private BufferedImage getTerrainImage()
    {
        BufferedImage terrain = new BufferedImage(this.worldW * 50, this.worldH * 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gfx = terrain.createGraphics();
        this.renderTerrain(gfx);
        return terrain;
    }
    
    public void output()
    {
        Location location;
        for(int x = 0; x < this.worldW; x++)
        {
            for(int y = 0; y < this.worldH; y++)
            {
                location = new Location(x, y);
                System.out.println(location.asKey() + " : " + this.tiles.get(location.asKey()).terrain.getKey() + " (" + this.tiles.get(location.asKey()).solidity.name() + ")");
            }
        }
    }
    
    public void renderTerrain(Graphics gfx)
    {
        Location location;
        for(int x = 0; x < this.worldW; x++)
        {
            for(int y = 0; y < this.worldH; y++)
            {
                location = new Location(x, y);
                Drawing.drawImage(gfx, this.tiles.get(location.asKey()).terrain.getImage(), x * 50, y * 50);
            }
        }
    }
    
    public void setTile(Location location, WorldTile tile)
    {
        this.tiles.put(location.asKey(), tile);
        this.terrain = null;
    }
    
    public void setTileTerrain(Location location, WorldTerrain terrain)
    {
        WorldTile tileOld = this.tiles.get(location.asKey());
        this.tiles.put(location.asKey(), new WorldTile(tileOld.location, terrain, tileOld.solidity));
        this.terrain = null;
    }
    
}