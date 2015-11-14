/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.unit;

import battle.BattleAbstract;
import battle.action.damage.DamageType;
import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class UnitAbstract
{
    // Unit
    private BattleAbstract battle;
    private String ref, name;
    private int posX, posY;
    
    // Stats
    private int statHealthNow, statHealthMax, statChargeNow;
    
    // Animation
    private String animIdleSheetF;
    private int animIdleSheetX, animIdleSheetY, animTickNow, animTickMax, animFrameNow, animFrameMax;
    
    // Temp
    public boolean tempAnim;
    
    public UnitAbstract(BattleAbstract battle, String ref, String name, int posX, int posY, String idleSheetF, int idleSheetX, int idleSheetY)
    {
        // Unit
        this.battle = battle;
        this.ref = ref;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        
        // Stats
        this.statHealthNow = 100;
        this.statHealthMax = 100;
        this.statChargeNow = 0;
        
        // Animation
        this.animIdleSheetF = idleSheetF;
        this.animIdleSheetX = idleSheetX;
        this.animIdleSheetY = idleSheetY;
        this.animTickNow = 0;
        this.animTickMax = 0;
        this.animFrameNow = 0;
        this.animFrameMax = 0;
        
        // Temp
        this.tempAnim = false;
    }
    
    public void damage(DamageType type, int amount)
    {
        int damageTotal = amount;
        this.statHealthNow -= amount;
        if(this.statHealthNow < 1) {this.statHealthNow = 0;}
        // note: animate the damage
        this.battle.visualAdd("" + damageTotal, this.posX + 96, this.posY + 64);
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int getStatHealthNow()
    {
        return this.statHealthNow;
    }
    
    public void render(Graphics g)
    {
        // Temp
        if(this.tempAnim)
        {
            GFX.drawImage(g, this.animIdleSheetF, this.posX, this.posY, 192 * this.animFrameNow, 192, 192, 192);
        }
        else {GFX.drawImage(g, this.animIdleSheetF, this.posX, this.posY, this.animIdleSheetX, this.animIdleSheetY, 192, 192);}
    }
    
    public void tick()
    {
        if(this.tempAnim)
        {
            if(this.animTickMax == 0)
            {
                this.animTickMax = 6;
                this.animFrameNow = 0;
                this.animFrameMax = 6;
            }
            this.tickAnim();
        }
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
                // note: continue anim or change to another?
                this.tempAnim = false;
                this.animTickNow = 0;
                this.animTickMax = 0;
                this.animFrameNow = 0;
                this.animFrameMax = 0;
            }
        }
    }
    
}