/*
 * Snake
 * Jamie Purchase
 */
package entities.nature;

import entities.Entity;
import entities.projectile.Projectile;
import game.Game;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import world.Location;
import world.LocationArea;

/**
 *
 * @author Jamie
 */
public class Nature extends Entity
{
    public LocationArea area;
    private BufferedImage image;
    
    public Nature(Game game, Location location, String image)
    {
        super(game, location);
        this.area = new LocationArea(location.x, location.y, 1, 1);
        this.image = Drawing.getImageResource("resources/gfx/nature/" + image + ".png");
    }
    
    public Nature(Game game, LocationArea area, String image)
    {
        super(game, area.getCorner());
        this.area = area;
        this.image = Drawing.getImageResource("resources/gfx/nature/" + image + ".png");
    }
    
    public void collideProjectile(Projectile projectile)
    {
        // is this projectile able to damage this object?
    }
    
    public void remove()
    {
        this.game.nature.remove(this);
    }
    
    public void render(Graphics g)
    {
        Drawing.drawImage(g, this.image, this.getRenderPosX(), this.getRenderPosY());
    }
    
    public void tick()
    {
        //
    }
    
}