/*
 * Snake
 * Jamie Purchase
 */
package entities;

import entities.projectile.Projectile;
import game.Game;
import java.awt.Graphics;
import java.util.HashMap;
import world.Location;

/**
 *
 * @author Jamie
 */
public abstract class Entity
{
    public Game game;
    public Location location;
    private HashMap<String, EntityValue> value;
    
    public Entity(Game game, Location location)
    {
        this.game = game;
        this.location = location;
        this.value = new HashMap();
    }
    
    public abstract void collideProjectile(Projectile projectile);
    
    public void createValue(String key, int now, int max)
    {
        this.value.put(key, new EntityValue(now, max));
    }
    
    public int getRenderPosX()
    {
        return this.location.getRenderPosX(this.game);
    }
    
    public int getRenderPosY()
    {
        return this.location.getRenderPosY(this.game);
    }
    
    public EntityValue getValue(String key)
    {
        return this.value.get(key);
    }
    
    public int getValueMax(String key)
    {
        return this.value.get(key).getMax();
    }
    
    public int getValueNow(String key)
    {
        return this.value.get(key).getNow();
    }
    
    public abstract void remove();
    
    public abstract void render(Graphics g);
    
    public void setValue(String key, int amount)
    {
        this.setValue(key, amount, false);
    }
    
    public void setValue(String key, int amount, boolean alter)
    {
        if(alter) {this.value.get(key).alter(amount);}
        else {this.value.get(key).setNow(amount);}
    }
    
    public void setValue(String key, int now, int max)
    {
        this.value.get(key).setNow(now);
        this.value.get(key).setMax(max);
    }
    
    public abstract void tick();
    
}