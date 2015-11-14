/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.entity.harvest;

import board.BoardAbstract;
import board.entity.EntityAbstract;
import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class HarvestAbstract extends EntityAbstract
{
    private boolean harvested;
    
    public HarvestAbstract(BoardAbstract board, String ref, int posX, int posY)
    {
        super(board, ref, posX * 32, posY * 32, 32, 32);
        this.harvested = false;
    }
    
    public String getData()
    {
        return this.getRenderImage() + "|" + this.harvested;
    }
    
    private String getRenderImage()
    {
        return "resources/gfx/harvest/mushroom1.png";
    }
    
    public void render(Graphics g)
    {
        GFX.drawImage(g, this.getRenderImage(), this.posX, this.posY, 0, 0, 32, 32);
    }
    
    public void tick()
    {
        //
    }
    
}