/*
 * Snake
 * Jamie Purchase
 */
package entities.fruit;

import entities.Entity;
import entities.projectile.Projectile;
import game.Game;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import world.Location;

/**
 *
 * @author Jamie
 */
public class Fruit extends Entity
{
    private BufferedImage image;
    
    public Fruit(Game game, Location location, String image)
    {
        super(game, location);
        this.image = Drawing.getImageResource("resources/gfx/fruit/" + image + ".png");
    }
    
    public void collideProjectile(Projectile projectile)
    {
        this.game.createNotify(this.location, "DESTROYED");
        this.remove();
    }
    
    public void eat()
    {
        this.game.scoreAdd(50);
        this.game.fruit.remove(this);
        this.game.createNotify(this.location, "+50");
        this.game.sfx("ERROR1");
    }
    
    public void remove()
    {
        this.game.fruit.remove(this);
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