/*
 * Snake
 * Jamie Purchase
 */
package editor.input;

import app.Engine;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import shapes.RectangleTools;
import styles.Style;

/**
 *
 * @author Jamie
 */
public class InputButton extends Input
{
    private Rectangle parent;
    private String label;
    private BufferedImage icon;
    
    public InputButton(String key, Rectangle parent, int posX, int posY, int posW, int posH, String label)
    {
        super(key, RectangleTools.inside(parent, new Rectangle(posX, posY, posW, posH)));
        this.label = label;
        this.icon = null;
    }
    
    public InputButton(String key, Rectangle parent, int posX, int posY, String icon)
    {
        super(key, RectangleTools.inside(parent, new Rectangle(posX, posY, 40, 40)));
        this.label = "";
        this.icon = Drawing.getImageResource("resources/gfx/interface/editor/" + icon + ".png");
    }
    
    public void render(Graphics g)
    {
        if(this.zone.contains(Engine.getMousePoint())) {Drawing.fillRect(g, this.zone, Style.colour("TOOL_INPUT_HOVER"));}
        else {Drawing.fillRect(g, this.zone, Style.colour("TOOL_INPUT_FILL"));}
        if(!this.label.isEmpty()) {Text.write(g, this.label, (int) this.zone.getCenterX(), this.zone.y + 23, "CENTER", Style.font("TOOL"), Color.BLACK);}
        if(this.icon != null) {Drawing.drawImage(g, this.icon, this.zone.x + 5, this.zone.y + 5);}
        Drawing.drawRect(g, this.zone, Color.BLACK);
    }

}