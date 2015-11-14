/*
 * Snake
 * Jamie Purchase
 */
package world;

import gfx.Drawing;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jamie
 */
public class WorldTerrain
{
    private String sheetFile;
    private int sheetX, sheetY;
    
    public WorldTerrain(String file, int x, int y)
    {
        this.sheetFile = file;
        this.sheetX = x;
        this.sheetY = y;
    }
    
    public BufferedImage getImage()
    {
        return Drawing.getImageResource("resources/gfx/nature/" + this.sheetFile + ".png").getSubimage(this.sheetX * 50, this.sheetY * 50, 50, 50);
    }
    
    public String getKey()
    {
        return this.sheetFile + "[" + this.sheetX + "-" + this.sheetY + "]";
    }
    
}