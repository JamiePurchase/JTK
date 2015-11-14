/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.ui;

import battle.BattleAbstract;
import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class InfoParty
{
    private BattleAbstract battle;
    private String styleColour, styleFont, styleShadow;
    
    public InfoParty(BattleAbstract battle)
    {
        this.battle = battle;
        this.styleColour = "UI_OPTION";
        this.styleFont = "UI_OPTION";
        this.styleShadow = "UI_SHADOW";
    }
    
    private String getStyleColour()
    {
        return this.styleColour;
    }
    
    private String getStyleFont()
    {
        return this.styleFont;
    }
    
    private String getStyleShadow()
    {
        return this.styleShadow;
    }
    
    public void render(Graphics g)
    {
        // Background
        
        // Headers
        GFX.writeShadow(g, "CHARACTER", 900, 600, 1, "LEFT", this.getStyleFont(), this.getStyleColour(), this.getStyleShadow());
        GFX.writeShadow(g, "HP", 1100, 600, 1, "LEFT", this.getStyleFont(), this.getStyleColour(), this.getStyleShadow());
        GFX.writeShadow(g, "AP", 1200, 600, 1, "LEFT", this.getStyleFont(), this.getStyleColour(), this.getStyleShadow());
        
        // Info
        for(int x = 0; x < this.battle.getUnitParty().size(); x++)
        {
            GFX.writeShadow(g, this.battle.getUnitParty().get(x).getName(), 900, 630 + (30 * x), 1, "LEFT", this.getStyleFont(), this.getStyleColour(), this.getStyleShadow());
            GFX.writeShadow(g, "1000", 1100, 630 + (30 * x), 1, "LEFT", this.getStyleFont(), this.getStyleColour(), this.getStyleShadow());
            GFX.writeShadow(g, "70", 1200, 630 + (30 * x), 1, "LEFT", this.getStyleFont(), this.getStyleColour(), this.getStyleShadow());
        }
    }

}