/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board;

import board.entity.harvest.HarvestAbstract;
import board.tile.TileAbstract;
import board.zone.ZoneAbstract;
import engine.Application;
import files.FileSystem;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import states.board.StateBoardAbstract;
import tools.ArrayListTool;

/**
 *
 * @author Jamie
 */
public abstract class BoardAbstract
{
    // Board
    protected String ref, name;
    protected int sizeW, sizeH, scrollX, scrollY;
    protected StateBoardAbstract state;
    protected Rectangle renderArea;
    
    // Terrain
    protected HashMap<String, TileAbstract> tile;
    protected BufferedImage tileImage;
    
    // Entities
    protected ArrayList<HarvestAbstract> harvest;
    protected ArrayList<ZoneAbstract> zone;
    
    public BoardAbstract(String ref)
    {
        // Board
        this.ref = ref;
        this.name = "BOARD1";
        this.sizeW = 50;
        this.sizeH = 24;
        this.scrollX = 0;
        this.scrollY = 0;
        this.state = null;
        this.renderArea = Application.getAppAreaDraw();
        
        // Terrain
        this.tile = new HashMap();
        this.tileImage = null;
        this.setTileAll(new TileAbstract("resources/gfx/scenery/village1.png", 0, 0));
        //this.setTileAll(new TileAbstract("C:/Users/Jamie/Documents/NetBeansProjects/JTK/Games/Venture/src/resources/gfx/scenery/village1.png", 0, 0));
        
        // Entities
        this.harvest = new ArrayList();
        this.zone = new ArrayList();
    }
    
    public void addHarvest(String ref, int posX, int posY)
    {
        this.harvest.add(new HarvestAbstract(this, ref, posX, posY));
    }
    
    public void addZone(String ref, int posX1, int posY1, int posX2, int posY2, boolean solid)
    {
        this.zone.add(new ZoneAbstract(this, ref, posX1, posY1, posX2, posY2, solid));
    }
    
    private ArrayList<String> getData()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Board Data
        data = ArrayListTool.add(data, this.getDataBoard());
        data.add(">");
        
        // Terrain Data
        data = ArrayListTool.add(data, this.getDataTerrain());
        data.add(">");
        
        // Harvest Data
        data = ArrayListTool.add(data, this.getDataHarvest());
        data.add(">");
        
        // Zone Data
        data = ArrayListTool.add(data, this.getDataZone());
        data.add(">");
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataBoard()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> BOARD DATA");
        data.add(">");
        
        // Name
        data.add(this.name);
        
        // Size
        data.add(this.sizeW + "|" + this.sizeH);
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataHarvest()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> HARVEST DATA");
        data.add(">");
        
        // Count
        data.add("" + this.harvest.size());
        
        // Data
        for(int x = 0; x < this.harvest.size(); x++)
        {
            data.add(this.harvest.get(x).getData());
        }
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataTerrain()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> TERRAIN DATA");
        data.add(">");
        
        // Data
        for(int x = 0; x < this.sizeW; x++)
        {
            for(int y = 0; y < this.sizeH; y++)
            {
                data.add(this.tile.get(getTileRef(x, y)).getData());
            }
        }
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataZone()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> ZONE DATA");
        data.add(">");
        
        // Count
        data.add("" + this.zone.size());
        
        // Data
        for(int x = 0; x < this.zone.size(); x++)
        {
            data.add(this.zone.get(x).getData());
        }
        
        // Return data
        return data;
    }
    
    public HarvestAbstract getHarvestIntersect(Rectangle rect)
    {
        for(int x = 0; x < this.harvest.size(); x++)
        {
            if(this.harvest.get(x).getBoardArea().intersects(rect)) {return this.harvest.get(x);}
        }
        return null;
    }
    
    public int getRenderPosX(int x)
    {
        return x + this.scrollX;
    }
    
    public int getRenderPosY(int y)
    {
        return y + this.scrollY;
    }
    
    public StateBoardAbstract getState()
    {
        return this.state;
    }
    
    protected BufferedImage getTileImage()
    {
        // Prepare a large new image
        BufferedImage imageNew = new BufferedImage(32 * this.sizeW, 32 * this.sizeH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imageNew.createGraphics();
        
        // Render all tiles
        TileAbstract tile;
        for(int x = 0; x < this.sizeW; x++)
        {
            for(int y = 0; y < this.sizeH; y++)
            {
                tile = this.tile.get(this.getTileRef(x, y));
                GFX.drawImage(g2d, tile.sheet, x * 32, y * 32, tile.x * 32, tile.y * 32, 32, 32);
                //GFX.drawImage(g2d, GFX.getImagePath(tile.sheet), x * 32, y * 32, tile.x * 32, tile.y * 32, 32, 32);
                
                // Debug
                //Output.print("");
                //Output.print("TILE AT " + x + "," + y);
                //Output.print(tile.sheet);
            }
        }
        
        // Return the image
        return imageNew;
    }
    
    public String getTileRef(int posX, int posY)
    {
        String refX = "" + posX;
        if(posX < 10) {refX = "00" + posX;}
        else if(posX < 100) {refX = "0" + posX;}
        String refY = "" + posY;
        if(posY < 10) {refY = "00" + posY;}
        else if(posY < 100) {refY = "0" + posY;}
        return "X" + refX + "Y" + refY;
    }
    
    public ZoneAbstract getZoneAtPixel(int posX, int posY)
    {
        for(int x = 0; x < this.zone.size(); x++)
        {
            if(this.zone.get(x).getBoardArea().contains(new Point(posX, posY))) {return this.zone.get(x);}
        }
        return null;
    }
    
    public ZoneAbstract getZoneAtTile(int posX, int posY)
    {
        for(int x = 0; x < this.zone.size(); x++)
        {
            if(this.zone.get(x).getTileArea().contains(new Point(posX, posY))) {return this.zone.get(x);}
        }
        return null;
    }
    
    public ZoneAbstract getZoneIntersect(Rectangle rect)
    {
        for(int x = 0; x < this.zone.size(); x++)
        {
            if(this.zone.get(x).getBoardArea().intersects(rect)) {return this.zone.get(x);}
        }
        return null;
    }
    
    public abstract void inputKey(KeyEvent e);
    
    public void redrawTerrain()
    {
        this.tileImage = this.getTileImage();
    }
    
    public abstract void render(Graphics g);
    
    protected void renderHarvest(Graphics g)
    {
        for(int x = 0; x < this.harvest.size(); x++)
        {
            this.harvest.get(x).render(g);
        }
    }
    
    protected void renderTerrain(Graphics g)
    {
        //GFX.drawRect(g, area, "BKG_GRASS", true);
        int imgX = 32 * this.scrollX;
        int imgY = 32 * this.scrollY;
        GFX.drawImage(g, this.tileImage, this.renderArea.x, this.renderArea.y, imgX, imgY, this.renderArea.width, this.renderArea.height);
    }
    
    public void save()
    {
        FileSystem.createFile("BOARD_DATA", "C:/Users/Jamie/Documents/NetBeansProjects/JTK/Games/Venture/src/resources/board/temp1", Application.getAppTitle(), "Contains board details, terrain, harvest and zone data", this.getData());
    }
    
    public void setRenderArea(Rectangle newArea)
    {
        this.renderArea = newArea;
    }
    
    public void setSize(int width, int height)
    {
        this.sizeW = width;
        this.sizeH = height;
    }
    
    public void setState(StateBoardAbstract newState)
    {
        this.state = newState;
    }
    
    public void setTileAt(int posX, int posY, TileAbstract newTile)
    {
        setTileAt(posX, posY, newTile, false);
    }
    
    public void setTileAt(int posX, int posY, TileAbstract newTile, boolean redraw)
    {
        // Change Tile
        this.tile.put(this.getTileRef(posX, posY), newTile);
        
        // Redraw
        if(redraw)
        {
            // Prepare a large new image
            BufferedImage imageNew = this.tileImage;
            Graphics2D g2d = imageNew.createGraphics();

            // Render the new tile
            GFX.drawImage(g2d, newTile.sheet, posX * 32, posY * 32, newTile.x * 32, newTile.y * 32, 32, 32);

            // Update the terrain image
            this.tileImage = imageNew;
        }
    }
    
    public void setTileAll(TileAbstract newTile)
    {
        for(int x = 0; x < this.sizeW; x++)
        {
            for(int y = 0; y < this.sizeH; y++)
            {
                this.setTileAt(x, y, newTile, false);
            }
        }
    }
    
    public abstract void tick();
    
}