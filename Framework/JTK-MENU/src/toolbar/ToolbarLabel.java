/**
 * JTK-MENU Jamie Purchase 07/11/2015
 */
package toolbar;

import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class ToolbarLabel
{
    private ToolbarAbstract toolbar;
    private String caption;
    private int posX, posY;
    
    public ToolbarLabel(ToolbarAbstract toolbar, String caption, int posX)
    {
        this.toolbar = toolbar;
        this.caption = caption;
        this.posX = this.toolbar.getArea().x + posX;
        this.posY = this.toolbar.getArea().y + 20;
    }
    
    public void render(Graphics g)
    {
        GFX.write(g, this.caption, this.posX, this.posY, "LEFT", this.toolbar.getLabelFont(), this.toolbar.getLabelColour());
    }

}