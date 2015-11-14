/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game.entity;

import game.force.ForceAbstract;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author Jamie
 */
public abstract class EntityAbstract
{
    // Entity
    protected ForceAbstract force;
    protected String ref, name;
    
    // Position
    protected int posX, posY;
    // note: int[] nexus points (relative to posX and posY)
    
    // Animation
    protected int animTickNow, animTickMax, animFrameNow, animFrameMax;
    
    public EntityAbstract(ForceAbstract force, String ref, String name, int posX, int posY)
    {
        // Entity
        this.force = force;
        this.ref = ref;
        this.name = name;
        
        // Position
        this.posX = posX;
        this.posY = posY;
        
        // Animation
        this.animTickNow = 0;
        this.animTickMax = 0;
        this.animFrameNow = 0;
        this.animTickMax = 3;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Polygon getNexus()
    {
        // note: use nexus points
        return new Polygon(new int[] {this.posX, this.posX + 64, this.posX + 64, this.posX}, new int[] {this.posY, this.posY, this.posY + 96, this.posY + 96}, 4);
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    protected int getRenderPosX()
    {
        return this.force.getGame().getRenderPosX(this.posX);
    }
    
    protected int getRenderPosY()
    {
        return this.force.getGame().getRenderPosY(this.posY);
    }
    
    public Polygon getRenderNexus()
    {
        // Get render x points
        int[] polygonX = new int[this.getNexus().xpoints.length];
        for(int x = 0; x < this.getNexus().xpoints.length; x++)
        {
            polygonX[x] = this.force.getGame().getRenderPosX(this.getNexus().xpoints[x]);
        }
        
        // Get render y points
        int[] polygonY = new int[this.getNexus().ypoints.length];
        for(int y = 0; y < this.getNexus().ypoints.length; y++)
        {
            polygonY[y] = this.force.getGame().getRenderPosY(this.getNexus().ypoints[y]);
        }
        
        // Return polygon
        return new Polygon(polygonX, polygonY, polygonX.length);
    }
    
    public boolean isRef(String compare)
    {
        return this.ref.equals(compare);
    }
    
    public abstract void render(Graphics g);
    
    public void tick()
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
    
}