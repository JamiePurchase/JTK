/*
 * Snake
 * Jamie Purchase
 */
package entities.notify;

import entities.Entity;
import entities.projectile.Projectile;
import game.Game;
import gfx.Colour;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import world.Location;

/**
 *
 * @author Jamie
 */
public class Notify extends Entity
{
    private String text;
    private final Font font = new Font("Courier New", Font.PLAIN, 14);
    private int offsetY;
    private int tickNow, tickMax, tickFrame;
    
    public Notify(Game game, Location location, String text, int offsetY)
    {
        super(game, location);
        this.text = text;
        this.offsetY = offsetY;
        this.tickNow = 0;
        this.tickMax = 6;
    }
    
    public void collideProjectile(Projectile projectile)
    {
        //
    }
    
    public void remove()
    {
        this.game.notify.remove(this);
    }
    
    public void render(Graphics g)
    {
        Text.writeShadow(g, this.text, this.location.getRenderPosX(this.game) + 25, this.location.getRenderPosY(this.game) - this.tickFrame + (this.offsetY * 16), 2, "CENTER", this.font, Color.BLACK, Colour.getColourRGB(150, 150, 150));
    }
    
    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow >= this.tickMax)
        {
            this.tickNow = 0;
            this.tickFrame += 1;
        }
    }
    
}