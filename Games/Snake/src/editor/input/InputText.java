/*
 * Snake
 * Jamie Purchase
 */
package editor.input;

import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import shapes.RectangleTools;
import styles.Style;

/**
 *
 * @author Jamie
 */
public class InputText extends Input
{
    private String value;
    private int maximum;
    private boolean active;
    
    public InputText(String key, Rectangle parent, int posX, int posY, int posW, int posH, String value, int maximum)
    {
        super(key, RectangleTools.inside(parent, new Rectangle(posX, posY, posW, posH)));
        this.value = "";
        this.maximum = 20;
        this.active = false;
    }
    
    public String getValue()
    {
        return this.value;
    }
    
    public void render(Graphics g)
    {
        Drawing.fillRect(g, this.zone, Color.WHITE);
        Drawing.drawRect(g, this.zone, Color.BLACK);
        Text.write(g, this.value, this.zone.x, this.zone.y + 23, "CENTER", Style.font("TOOL_INPUT"), Color.BLACK);
    }
    
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    public void valueWrite(String write)
    {
        if(this.value.length() < this.maximum) {this.value += write;}
    }
    
}