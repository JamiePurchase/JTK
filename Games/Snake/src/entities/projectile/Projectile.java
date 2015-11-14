/*
 * Snake
 * Jamie Purchase
 */
package entities.projectile;

import entities.Direction;
import entities.Entity;
import entities.status.Status;
import game.Game;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import maths.Maths;
import world.Location;

/**
 *
 * @author Jamie
 */
public class Projectile extends Entity
{
    private Direction direction;
    private boolean player;
    private BufferedImage image;
    private ProjectileType type;
    private int strength;
    private Status statusType;
    private int statusChance;
    private int tickNow, tickMax, tickFrame, tickTime, tickExpire;
    
    public Projectile(Game game, Location location, Direction direction, boolean player, String image, ProjectileType type, int strength, int expire)
    {
        super(game, location);
        this.init(direction, player, image, type, strength, expire);
        this.statusType = null;
        this.statusChance = 0;
    }
    
    public Projectile(Game game, Location location, Direction direction, boolean player, String image, ProjectileType type, int strength, int expire, Status statusType, int statusChance)
    {
        super(game, location);
        this.init(direction, player, image, type, strength, expire);
        this.statusType = statusType;
        this.statusChance = statusChance;
    }
    
    public void collideProjectile(Projectile projectile)
    {
        //
    }
    
    private void destroy()
    {
        this.game.projectile.remove(this);
    }
    
    public BufferedImage getRenderImage()
    {
        return this.image.getSubimage(this.tickFrame * 50, 0, 50, 50);
    }
    
    public int getStrength()
    {
        return this.strength;
    }
    
    public Status getStatus()
    {
        if(this.statusType != null)
        {
            if(Maths.randomInt(100) <= this.statusChance) {return this.statusType;}
        }
        return null;
    }
    
    public ProjectileType getType()
    {
        return this.type;
    }
    
    public boolean hasDamage()
    {
        if(this.strength > 0) {return true;}
        return false;
    }
    
    public boolean hasStatus()
    {
        if(this.statusType != null) {return true;}
        return false;
    }
    
    private void init(Direction direction, boolean player, String image, ProjectileType type, int strength, int expire)
    {
        this.direction = direction;
        this.player = player;
        this.image = Drawing.getImageResource("resources/gfx/projectile/" + image + ".png");
        this.type = type;
        this.strength = strength;
        this.statusType = null;
        this.statusChance = 0;
        this.tickNow = 0;
        this.tickMax = 12;
        this.tickFrame = 0;
        this.tickTime = 0;
        this.tickExpire = expire;
    }
    
    private void move()
    {
        // Target Location
        Location pushLocation = this.location.getAdjacent(this.direction);
        
        // Console
        this.game.console("Projectile -> push(" + this.direction.name() + ")");
        this.game.console(" current: " + this.location.asString());
        this.game.console(" target: " + pushLocation.asString());
        
        // Update Location
        this.game.console(" moved to location: " + pushLocation.asString());
        this.location = pushLocation;
    }
    
    public void remove()
    {
        this.game.projectile.remove(this);
    }
    
    public void render(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImage(), this.getRenderPosX(), this.getRenderPosY());
    }
    
    public void tick()
    {
        // Movement
        this.tickMove();
        
        // Collision
        this.tickCollision();
    }
    
    private void tickCollision()
    {
        Entity entity = this.game.getEntityAt(this.location);
        if(entity != null)
        {
            entity.collideProjectile(this);
            this.destroy();
        }
    }
    
    private void tickMove()
    {
        this.tickNow += 1;
        if(this.tickNow >= this.tickMax)
        {
            this.tickNow = 0;
            this.tickFrame += 1;
            if(this.tickFrame >= 4) {this.tickFrame = 0;}
            this.tickTime += 1;
            if(this.tickTime >= this.tickExpire) {this.remove();}
            this.move();
        }
    }
    
}