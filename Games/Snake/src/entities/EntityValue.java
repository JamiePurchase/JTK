/*
 * Snake
 * Jamie Purchase
 */
package entities;

import maths.Maths;

/**
 *
 * @author Jamie
 */
public class EntityValue
{
    private int now, max;
    
    public EntityValue(int now, int max)
    {
        this.now = now;
        this.max = max;
    }
    
    public void alter(int amount)
    {
        this.now += amount;
        if(this.now > this.max) {this.now = this.max;}
        if(this.now < 0) {this.now = 0;}
    }
    
    public String asString()
    {
        return "VALUE: " + this.now + " / " + this.max + " (" + this.getPercent() + "%)";
    }
    
    public int getMax()
    {
        return this.max;
    }
    
    public int getNow()
    {
        return this.now;
    }
    
    public float getPercent()
    {
        return Maths.percent(this.now, this.max);
    }
    
    public boolean isMax()
    {
        if(this.now == this.max) {return true;}
        return false;
    }
    
    public void setMax(int amount)
    {
        this.max = amount;
    }
    
    public void setNow(int amount)
    {
        this.now = amount;
    }
    
}