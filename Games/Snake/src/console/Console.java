/*
 * Snake
 * Jamie Purchase
 */
package console;

import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class Console
{
    private final Rectangle box = new Rectangle(951, 15, 400, 738);
    private ArrayList<String> output;
    private final Font fontHeader = new Font("Vanthian Ragnarok", Font.PLAIN, 22);
    private final Font fontText = new Font("Courier New", Font.PLAIN, 14);
    private final int lines = 34;
    
    public Console()
    {
        this.output = new ArrayList();
    }
    
    public void output(String text)
    {
        // In-game Console
        this.output.add(text);
        if(this.output.size() > this.lines) {this.output.remove(0);}
        
        // System Console
        System.out.println(text);
    }
    
    public void render(Graphics g)
    {
        this.renderBox(g);
        if(this.output.size() > 0) {this.renderText(g);}
    }
    
    private void renderBox(Graphics g)
    {
        Drawing.fillRect(g, this.box, Color.BLACK);
        Drawing.drawRect(g, this.box, Color.WHITE);
        Text.writeShadow(g, "DEVELOPMENT CONSOLE", (int) this.box.getCenterX(), this.box.y + 30, 2, "CENTER", this.fontHeader, Color.WHITE, Colour.getColourRGB(100, 100, 100));
    }
    
    private void renderText(Graphics g)
    {
        for(int x = 0; x < this.output.size(); x++)
        {
            Text.write(g, this.output.get(x), this.box.x + 15, this.box.y + 60 + (x * 20), "LEFT", this.fontText, Color.WHITE);
        }
    }
    
}