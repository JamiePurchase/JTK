/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.visual;

import battle.BattleAbstract;
import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class VisualAbstract
{
    private BattleAbstract battle;
    private String caption;
    private int posX, posY, tick;
    
    public VisualAbstract(BattleAbstract battle, String caption, int posX, int posY)
    {
        this.battle = battle;
        this.caption = caption;
        this.posX = posX;
        this.posY = posY;
        this.tick = 120;
    }
    
    public void render(Graphics g)
    {
        GFX.writeShadow(g, this.caption, this.posX, this.posY + (this.tick / 2), 2, "CENTER", "VISUAL_STANDARD", "VISUAL_STANDARD", "VISUAL_SHADOW");
    }
    
    public void tick()
    {
        this.tick -= 1;
        if(this.tick < 1) {this.battle.visualRemove(this);}
    }
    
}