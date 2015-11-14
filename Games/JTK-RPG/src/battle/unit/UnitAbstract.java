/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.unit;

import battle.BattleAbstract;
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
    private int statChargeNow;
    
    // Animation
    private int animTickNow, animTickMax, animFrameNow, animFrameMax;
    
    public UnitAbstract(BattleAbstract battle, String ref, String name, int posX, int posY)
    {
        // Unit
        this.battle = battle;
        this.ref = ref;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        
        // Stats
        this.statChargeNow = 0;
        
        // Animation
        this.animTickNow = 0;
        this.animTickMax = 0;
        this.animFrameNow = 0;
        this.animFrameMax = 0;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void render(Graphics g)
    {
        //
        GFX.drawImage(g, "resources/gfx/character/1_battle.png", this.posX, this.posY, 0, 192, 192, 192);
    }
    
    public void tick()
    {
        //
    }
    
}