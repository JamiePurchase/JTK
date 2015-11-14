/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package ui;

import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class OptionButton
{
    private String ref, caption;
    private Rectangle area;
    
    public OptionButton(String ref, String caption, int posX, int posY)
    {
        this.ref = ref;
        this.caption = caption;
        this.area = new Rectangle(posX, posY, 100, 30);
    }
    
    public Rectangle getArea()
    {
        return this.area;
    }
    
    public void render(Graphics g)
    {
        if(this.area.contains(Application.getMousePoint())) {GFX.drawRect(g, this.area, "OPTION_BKG_HOVER", true);}
        else {GFX.drawRect(g, this.area, "OPTION_BKG", true);}
        GFX.drawRect(g, this.area, "OPTION_BORDER", false);
        GFX.write(g, this.caption, (int) this.area.getCenterX(), this.area.y + 24, "CENTER", "OPTION_STANDARD", "OPTION_TEXT");
    }
    
}