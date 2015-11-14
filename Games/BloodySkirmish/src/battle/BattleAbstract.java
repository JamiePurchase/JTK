/**
 * Bloody Skirmish Jamie Purchase JTK Framework 07-11-2015
 */
package battle;

import battle.entity.EntityAbstract;
import battle.force.ForceAbstract;
import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class BattleAbstract
{
    private boolean advanceActive, advanceCommand;
    private int advanceTick1, advanceTick2;
    private EntityAbstract advanceEntity;
    private ForceAbstract forceHost, forceGuest;
    private ArrayList<EntityAbstract> entity;
    
    public BattleAbstract()
    {
        this.advanceActive = true;
        this.advanceCommand = false;
        this.advanceEntity = null;
        this.advanceTick1 = 0;
        this.advanceTick2 = 0;
        
        this.forceHost = new ForceAbstract(this);
        this.forceGuest = new ForceAbstract(this);
        this.entity = new ArrayList();
        
        // temp
        this.entity.add(new EntityAbstract(this, this.forceHost, 1, 1));
    }
    
    private EntityAbstract getEntityReady()
    {
        for(int x = 0; x < this.entity.size(); x++)
        {
            if(this.entity.get(x).isChargeReady()) {return this.entity.get(x);}
        }
        return null;
    }
    
    public void input(KeyEvent e)
    {
        if(this.advanceCommand)
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                this.advanceEntity.action();
                this.advanceCommand = false;
                this.advanceEntity = null;
            }
        }
    }
    
    public void render(Graphics g)
    {
        this.renderBackground(g);
        this.renderEntity(g);
        this.renderInfo(g);
        if(this.advanceCommand) {this.renderCommand(g);}
    }
    
    private void renderBackground(Graphics g)
    {
        GFX.drawRect(g, Application.getAppArea(), "BKG_GRASS", true);
    }
    
    private void renderCommand(Graphics g)
    {
        GFX.drawRect(g, new Rectangle(50, 500, 300, 200), "BLACK", true);
    }
    
    private void renderEntity(Graphics g)
    {
        for(int x = 0; x < this.entity.size(); x++)
        {
            this.entity.get(x).render(g);
        }
    }
    
    private void renderInfo(Graphics g)
    {
        //
    }
    
    public void setAdvanceActive(boolean active)
    {
        this.advanceActive = active;
    }
    
    public void tick()
    {
        if(this.advanceActive)
        {
            this.advanceTick1 += 1;
            if(this.advanceTick1 >= 12)
            {
                this.advanceTick1 = 0;
                this.tickEntity();
                // temp
                EntityAbstract entity = this.getEntityReady();
                if(entity != null)
                {
                    this.advanceActive = false;
                    this.advanceEntity = entity;
                    if(entity.getForce() == this.forceHost)
                    {
                        this.advanceCommand = true;
                    }
                }
            }
        }
    }
    
    private void tickEntity()
    {
        for(int x = 0; x < this.entity.size(); x++)
        {
            this.entity.get(x).tick();
        }
    }
    
}