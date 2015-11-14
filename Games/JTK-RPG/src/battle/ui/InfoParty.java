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
    private String styleColour, styleFont;
    
    public InfoParty(BattleAbstract battle)
    {
        this.battle = battle;
        this.styleColour = "WHITE";
        this.styleFont = "MENU";
    }
    
    private String getStyleColour()
    {
        return this.styleColour;
    }
    
    private String getStyleFont()
    {
        return this.styleFont;
    }
    
    public void render(Graphics g)
    {
        // Background
        
        // Headers
        GFX.write(g, "CHARACTER", 900, 600, "LEFT", this.getStyleFont(), this.getStyleColour());
        GFX.write(g, "HP", 1100, 600, "LEFT", this.getStyleFont(), this.getStyleColour());
        GFX.write(g, "AP", 1200, 600, "LEFT", this.getStyleFont(), this.getStyleColour());
        
        // Info
        for(int x = 0; x < this.battle.getUnitParty().size(); x++)
        {
            GFX.write(g, this.battle.getUnitParty().get(x).getName(), 900, 630 + (30 * x), "LEFT", this.getStyleFont(), this.getStyleColour());
            GFX.write(g, "1000", 1100, 630 + (30 * x), "LEFT", this.getStyleFont(), this.getStyleColour());
            GFX.write(g, "70", 1200, 630 + (30 * x), "LEFT", this.getStyleFont(), this.getStyleColour());
        }
    }

}