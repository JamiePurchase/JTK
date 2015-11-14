/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package ui;

import game.GameData;
import game.entity.EntityAbstract;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class InfoSelect
{
    private GameData game;
    //private final Rectangle area = new Rectangle(0, 628, 1366, 140);
    private final Rectangle area = new Rectangle(20, 608, 600, 140);

    public InfoSelect(GameData game)
    {
        this.game = game;
    }
    
    public Rectangle getArea()
    {
        return this.area;
    }
    
    public void render(Graphics g)
    {
        // Shadow
        GFX.drawRect(g, new Rectangle(this.area.x + 3, this.area.y + 3, this.area.width, this.area.height), "BLACK", true);
        
        // Background
        GFX.drawRect(g, this.area, "INFO_BKG", true);
        
        // Border
        GFX.drawRect(g, this.area, "INFO_BORDER", false);
        
        // Temp Selection
        ArrayList<EntityAbstract> selection = this.game.getSelectEntity();
        String select = "NOTHING SELECTED";
        if(this.game.getSelectEntity().size() > 0)
        {
            if(selection.size() == 1) {select = selection.get(0).getName() + " (" + selection.get(0).getRef() + ")";}
            else {select = "MULTIPLE ENTITIES SELECTED (" + selection.size() + ")";}
        }
        GFX.write(g, select, this.area.x + 50, this.area.y + 25, "LEFT", "INFO_STANDARD", "INFO_TEXT");
    }

}