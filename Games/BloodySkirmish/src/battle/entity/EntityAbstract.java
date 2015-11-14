/**
 * Bloody Skirmish Jamie Purchase JTK Framework 07-11-2015
 */
package battle.entity;

import battle.BattleAbstract;
import battle.force.ForceAbstract;
import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class EntityAbstract
{
    // Entity
    private BattleAbstract battle;
    private ForceAbstract force;
    private int posX, posY;
    
    // Charge
    private int chargeWait;
    private boolean chargeReady;
    
    // Animation
    private boolean animAction;
    private int animTickNow, animTickMax, animFrameNow, animFrameMax;
    
    public EntityAbstract(BattleAbstract battle, ForceAbstract force, int posX, int posY)
    {
        // Entity
        this.battle = battle;
        this.force = force;
        this.posX = posX;
        this.posY = posY;
        
        // Charge
        this.chargeWait = 4;
        this.chargeReady = false;
        
        // Animation
        this.animAction = false;
        this.animTickNow = 0;
        this.animTickMax = 12;
        this.animFrameNow = 0;
        this.animFrameMax = 0;
        //this.animFrameMax = 6;
    }
    
    public void action()
    {
        this.animAction = true;
        this.animTickNow = 0;
        this.animTickMax = 12;
        this.animFrameNow = 0;
        this.animFrameMax = 6;
    }
    
    public ForceAbstract getForce()
    {
        return this.force;
    }
    
    private int getRenderPosX()
    {
        return this.posX * 50;
    }
    
    private int getRenderPosY()
    {
        return this.posY * 50;
    }
    
    public boolean isChargeReady()
    {
        return this.chargeReady;
    }
    
    public void render(Graphics g)
    {
        int sheetX = 0;
        int sheetY = 576;
        sheetX = 192 * this.animFrameNow;
        GFX.drawImage(g, "resources/gfx/entity/temp1.png", this.getRenderPosX(), this.getRenderPosY(), sheetX, sheetY, 192, 192);
    }
    
    public void tick()
    {
        this.tickAnim();
        this.tickCharge();
    }
    
    private void tickAnim()
    {
        this.animTickNow += 1;
        if(this.animTickNow >= this.animTickMax)
        {
            this.animTickNow = 0;
            this.animFrameNow += 1;
            if(this.animFrameNow >= this.animFrameMax)
            {
                this.animFrameNow = 0;
                if(this.animAction)
                {
                    this.animAction = false;
                    this.chargeWait = 10;
                    this.battle.setAdvanceActive(true);
                    this.animFrameMax = 0;
                }
            }
        }
    }
    
    private void tickCharge()
    {
        if(!this.chargeReady)
        {
            this.chargeWait -= 1;
            if(this.chargeWait < 1) {this.chargeReady = true;}
        }
    }
    
}