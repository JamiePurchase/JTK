/*
 * Snake
 * Jamie Purchase
 */
package entities.enemies;

import entities.Direction;
import entities.Entity;
import entities.enemies.ai.AiTarget;
import entities.projectile.Projectile;
import entities.projectile.ProjectileType;
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
public class Enemy extends Entity
{
    private Direction direction;
    private BufferedImage imageStandard, imageFrozen;
    private AiTarget aiTarget;
    private Status statusType;
    private int statusTick;
    private int rewardScore;
    private int tickNow, tickMax, tickFrame;
    
    public Enemy(Game game, Location location, Direction direction, String image, int health)
    {
        super(game, location);
        this.direction = direction;
        this.imageFrozen = Drawing.getImageResource("resources/gfx/enemy/" + image + "frozen.png");
        this.imageStandard = Drawing.getImageResource("resources/gfx/enemy/" + image + ".png");
        this.aiTarget = new AiTarget(this.game, this, new Location(1, 6));
        this.statusType = null;
        this.statusTick = 0;
        this.rewardScore = 80;
        this.tickNow = 0;
        this.tickMax = 30;
        this.tickFrame = 0;
        this.createValue("HEALTH", health, health);
    }
    
    private void aiThink()
    {
        this.game.console("Enemy -> aiThink()");
        if(this.location.isAt(1, 6))
        {
            this.aiTarget.location = new Location(5, 6);
            this.direction = Direction.EAST;
        }
        else
        {
            this.aiTarget.location = new Location(1, 6);
            this.direction = Direction.WEST;
        }
    }
    
    public void collide()
    {
        this.game.scoreSubtract(50);
        this.game.enemy.remove(this);
        this.game.createNotify(this.location, "COLLISION");
    }
    
    public void collideProjectile(Projectile projectile)
    {
        // Damage
        if(projectile.hasDamage()) {this.damage(projectile.getStrength(), projectile.getType());}
        
        // Status
        if(projectile.hasStatus())
        {
            Status statusType = projectile.getStatus();
            if(statusType != null)
            {
                // NOTE: status class?
                int statusTick = 30;
                this.status(statusType, statusTick);
            }
        }
    }
    
    private void damage(int amount, ProjectileType type)
    {
        // Basic Damage
        int amountTotal = amount;
        
        // Weakness
        boolean weak = this.damageWeakness(type);
        if(weak) {amountTotal = amountTotal * 2;}
        
        // Bonus Damage
        amountTotal += Maths.randomInt(10);
        
        // Inflict Damage
        this.setValue("HEALTH", -amountTotal, true);
        if(this.getValueNow("HEALTH") < 1) {this.destroy(true);}
        
        // Notify
        this.game.createNotify(this.location, "" + amountTotal);
        if(weak) {this.game.createNotify(this.location, "CRITICAL", 1);}
    }
    
    private boolean damageWeakness(ProjectileType type)
    {
        if(type == ProjectileType.VENOM) {return true;}
        return false;
    }
    
    private void destroy(boolean reward)
    {
        // Reward Player
        if(reward)
        {
            this.game.createNotify(this.location, "+" + this.rewardScore);
            this.game.scoreAdd(this.rewardScore);
        }
        
        // Destroy Enemy
        this.remove();
    }
    
    private BufferedImage getRenderImage()
    {
        // Standard
        BufferedImage renderImage = this.imageStandard;
        
        // Status
        if(this.statusType == Status.FROZEN) {renderImage = this.imageFrozen;}
        
        // Subimage
        if(this.direction == Direction.EAST) {return renderImage.getSubimage(50, 0, 50, 50);}
        if(this.direction == Direction.WEST) {return renderImage.getSubimage(0, 0, 50, 50);}
        return null;
    }
    
    private void move()
    {
        Location move = this.location.getAdjacent(this.direction);
        
        // Console
        this.game.console("Enemy -> move(" + direction.name() + ")");
        this.game.console(" current: " + this.location.asString());
        this.game.console(" target: " + move.asString());
        
        // Update Location
        this.location = move;
    }
    
    public void remove()
    {
        this.game.enemy.remove(this);
    }
    
    public void render(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImage(), this.getRenderPosX(), this.getRenderPosY());
    }
    
    private void status()
    {
        this.statusType = null;
        this.statusTick = 0;
        // NOTE: reconfigure AI?
    }
    
    private void status(Status type, int tick)
    {
        this.statusType = type;
        this.statusTick = tick;
        this.game.createNotify(this.location, type.name(), -1);
    }
    
    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow >= this.tickMax)
        {
            this.tickNow = 0;
            
            // Movement
            if(this.location.isAt(this.aiTarget.location)) {this.aiThink();}
            else {this.move();}
        }
        
        // Status
        if(this.statusType != null && this.statusTick > 0)
        {
            this.statusTick -= 1;
            if(this.statusTick <= 0) {this.status();}
        }
    }
    
}