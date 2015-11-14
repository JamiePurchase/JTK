/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.entity.character;

import board.BoardAbstract;
import board.Direction;
import board.entity.EntityAbstract;
import board.entity.harvest.HarvestAbstract;
import board.zone.ZoneAbstract;
import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class CharacterAbstract extends EntityAbstract
{
    
    private Direction direction;
    private boolean actionIdle, actionWalk;
    private int actionTickNow, actionTickMax;
    private int animTickNow, animTickMax, animFrameNow, animFrameMax;
    
    public CharacterAbstract(BoardAbstract board, String ref, int posX, int posY, Direction direction)
    {
        super(board, ref, posX * 32, posY * 32, 64, 64);
        this.direction = direction;
        this.actionIdle = true;
        this.actionWalk = false;
        this.actionTickNow = 0;
        this.actionTickMax = 0;
        this.animTickNow = 0;
        this.animTickMax = 1;
        this.animFrameNow = 0;
        this.animFrameMax = 0;
    }
    
    private Rectangle getAdjacentArea()
    {
        // Calculate Adjacent
        int newX = posX;
        int newY = posY;
        if(this.direction == Direction.EAST) {newX += 32;}
        if(this.direction == Direction.NORTH) {newY -= 32;}
        if(this.direction == Direction.SOUTH) {newY += 32;}
        if(this.direction == Direction.WEST) {newX -= 32;}
        
        // Return Area
        return new Rectangle(newX, newY, 64, 64);
    }
    
    private Rectangle getCollisionArea()
    {
        return new Rectangle(this.posX, this.posY, 64, 64);
    }
    
    private String getRenderImage()
    {
        return "resources/gfx/character/1.png";
    }
    
    public void render(Graphics g)
    {
        int sheetX = 0;
        int sheetY = 0;
        if(this.actionWalk) {sheetX = 64 * (this.animFrameNow + 1);}
        if(this.direction == Direction.EAST) {sheetY = 704;}
        if(this.direction == Direction.NORTH) {sheetY = 512;}
        if(this.direction == Direction.SOUTH) {sheetY = 640;}
        if(this.direction == Direction.WEST) {sheetY = 576;}
        GFX.drawImage(g, this.getRenderImage(), this.posX, this.posY, sheetX, sheetY, 64, 64);
        
        // temp collision
        //GFX.drawRect(g, this.getCollisionArea(), "WHITE", false);
    }
    
    public void tick()
    {
        this.tickAnim();
        if(this.actionWalk) {this.tickWalk();}
    }
    
    private void tickAnim()
    {
        this.animTickNow += 1;
        if(this.animTickNow >= this.animTickMax)
        {
            this.animTickNow = 0;
            this.animFrameNow += 1;
            if(this.animFrameNow >= this.animFrameMax)
            {
                this.animFrameNow = 0;
            }
        }
    }
    
    private void tickWalk()
    {
        // Calculate destination (one step)
        int newX = posX;
        int newY = posY;
        if(this.direction == Direction.EAST) {newX += 4;}
        if(this.direction == Direction.NORTH) {newY -= 4;}
        if(this.direction == Direction.SOUTH) {newY += 4;}
        if(this.direction == Direction.WEST) {newX -= 4;}
        boolean newSolid = false;
        
        // Check for zones
        ZoneAbstract zone = this.board.getZoneIntersect(new Rectangle(newX, newY, 64, 64));
        if(zone != null && zone.isSolid()) {newSolid = true;}
        
        // Check for harvest
        HarvestAbstract harvest = this.board.getHarvestIntersect(new Rectangle(newX, newY, 64, 64));
        if(harvest != null) {newSolid = true;}
        
        // Move player (one step)
        if(!newSolid)
        {
            this.posX = newX;
            this.posY = newY;
        }
        
        // Action advance
        this.actionTickNow += 1;
        if(this.actionTickNow >= this.actionTickMax)
        {
            // Action Done
            this.actionTickNow = 0;
            
            // Check for Harvest (TEMP)
            this.board.getState().interactHint();
            HarvestAbstract harvestAdjacent = this.board.getHarvestIntersect(new Rectangle(newX, newY, 64, 64));
            if(harvestAdjacent != null)
            {
                this.board.getState().interactHint("HARVEST");
            }
            
            // Still holding the key?
            String code = "KEY|38";
            if(this.direction == Direction.EAST) {code = "KEY|39";}
            if(this.direction == Direction.SOUTH) {code = "KEY|40";}
            if(this.direction == Direction.WEST) {code = "KEY|37";}
            if(!Application.getKeyboard().holdListContains(code))
            {
                this.actionIdle = true;
                this.actionWalk = false;
                this.animFrameMax = 0;
            }
        }
    }
    
    public void walk(Direction d)
    {
        if(this.actionIdle)
        {
            this.direction = d;
            this.actionIdle = false;
            this.actionWalk = true;
            this.actionTickNow = 0;
            this.actionTickMax = 8;
            this.animTickNow = 0;
            this.animFrameNow = 0;
            this.animFrameMax = 8;
        }
    }
    
}