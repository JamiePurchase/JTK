/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board;

import board.entity.character.CharacterAbstract;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Jamie
 */
public class BoardData extends BoardAbstract
{
    // Entities
    private CharacterAbstract player;
    
    public BoardData(String ref)
    {
        // Board
        super(ref);
        
        // Entities
        this.player = new CharacterAbstract(this, "PLAYER", 18, 10, Direction.SOUTH);
    }
    
    public void inputKey(KeyEvent e)
    {
        // Arrow: Up
        if(e.getKeyCode() == KeyEvent.VK_UP) {this.player.walk(Direction.NORTH);}
        
        // Arrow: Down
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {this.player.walk(Direction.SOUTH);}
        
        // Arrow: Left
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {this.player.walk(Direction.WEST);}
        
        // Arrow: Right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {this.player.walk(Direction.EAST);}
    }
    
    public void render(Graphics g)
    {
        // Terrain
        if(this.tileImage == null) {this.tileImage = this.getTileImage();}
        this.renderTerrain(g);
        
        // Player
        this.player.render(g);
        
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
        // Player
        this.player.tick();
    }
    
}