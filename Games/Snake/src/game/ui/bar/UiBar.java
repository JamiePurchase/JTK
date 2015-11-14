/*
 * Snake
 * Jamie Purchase
 */
package game.ui.bar;

import entities.EntityValue;
import game.Game;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import maths.Maths;

/**
 *
 * @author Jamie
 */
public class UiBar
{
    private Game game;
    private EntityValue value;
    private Rectangle zone;
    private Color color;
    
    //public UiBar(Game game, EntityValue value, Rectangle zone, Color color)
    public UiBar(Game game, Rectangle zone, Color color)
    {
        this.game = game;
        //this.value = value;
        this.zone = zone;
        this.color = color;
    }
    
    //private Rectangle getRenderBar(float percent)
    private Rectangle getRenderBar(float percent)
    {
        // DEBUG
        float width = (percent * this.zone.width) / 100;
        System.out.println("UiBar -> getRenderBar(" + percent + ")");
        System.out.println(" zone width = " + this.zone.width);
        System.out.println(" fill width = " + width);
        
        //return new Rectangle(this.zone.x, this.zone.y, (int) Maths.percent((int) this.value.getPercent(), this.zone.width), this.zone.height);
        return new Rectangle(this.zone.x, this.zone.y, (int) width, this.zone.height);
        //return new Rectangle(this.zone.x, this.zone.y, value.getNow(), this.zone.height);
    }
    
    private Rectangle getRenderZone(int value)
    {
        return new Rectangle(this.zone.x, this.zone.y, value, this.zone.height);
    }
    
    //public void render(Graphics g, float percent)
    public void render(Graphics g, EntityValue value)
    {
        // Background
        Drawing.fillRect(g, this.zone, Color.BLACK);
        //Drawing.fillRect(g, this.getRenderZone(value.getMax() * 2), Color.BLACK);
        
        // Fill
        Drawing.fillRect(g, this.getRenderBar(value.getPercent()), this.color);
        
        // Border
        Drawing.drawRect(g, this.zone, Color.BLACK);
        //Drawing.drawRect(g, this.getRenderZone(value.getMax() * 2), Color.BLACK);
        
        // DEBUG
        System.out.println("UiBar -> render(" + value.asString() + ")");
    }
    
}