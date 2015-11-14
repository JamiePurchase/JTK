/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import states.board.StateBoardAbstract;

/**
 *
 * @author Jamie
 */
public class BoardEdit extends BoardAbstract
{
    // Options
    public boolean optionGrid;
    
    public BoardEdit(String ref, StateBoardAbstract state)
    {
        // Board
        super(ref);
        this.state = state;
        
        // ??
        //
    }
    
    public void inputKey(KeyEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        // Terrain
        if(this.tileImage == null) {this.tileImage = this.getTileImage();}
        this.renderTerrain(g);
        
        // Entities / NPCs?
        //
        this.renderHarvest(g);
        
        // Animated Scenery?
        //
        
        // Temp zones
        /*for(int x = 0; x < this.zone.size(); x++)
        {
            GFX.drawRect(g, this.zone.get(x).getRenderArea(), "WHITE", false);
        }*/
    }
    
    public void tick()
    {
        //
    }
    
}