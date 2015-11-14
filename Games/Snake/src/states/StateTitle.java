/*
 * Snake
 * Jamie Purchase
 */
package states;

import app.Engine;
import game.ui.UiTools;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import styles.Style;

/**
 *
 * @author Jamie
 */
public class StateTitle extends State
{
    private final Rectangle titleBackground = new Rectangle(0, 125, Engine.getWindowArea().width, 594);
    private final Rectangle menuFrame = new Rectangle(500, 300, Engine.getWindowArea().width - 1000, 300);
    private int cursorOption, cursorTickNow, cursorTickMax, cursorTickFrame;
    private final BufferedImage cursorImage = Drawing.getImageResource("resources/gfx/interface/cursor1.png");
    private ArrayList<String> cursorMenu;
    
    public StateTitle()
    {
        this.cursorOption = 0;
        this.cursorTickNow = 0;
        this.cursorTickMax = 12;
        this.cursorTickFrame = 0;
        this.cursorMenu = new ArrayList();
        this.cursorMenu.add("NEW GAME");
        this.cursorMenu.add("CONTROLS");
        this.cursorMenu.add("ABOUT");
        this.cursorMenu.add("EDITOR");
        this.cursorMenu.add("EXIT ");
    }

    public void inputKeyPress(String key)
    {
        if(key.equals("ENTER") || key.equals("SPACE"))
        {
            if(this.cursorOption == 0) {Engine.setState(new StateGame());}
            if(this.cursorOption == 3) {Engine.setState(new StateEditor());}
            if(this.cursorOption == 4) {System.exit(0);}
        }
        if(key.equals("UP") && this.cursorOption > 0) {this.cursorOption -= 1;}
        if(key.equals("DOWN") && this.cursorOption < this.cursorMenu.size() - 1) {this.cursorOption += 1;}
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        //
    }

    public void inputMouseClickL(MouseEvent e)
    {
        //
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }

    public void render(Graphics g)
    {
        Drawing.fillScreen(g, Color.BLACK);
        this.renderBackground(g);
        this.renderMenu(g);
        this.renderInfo(g);
    }
    
    private void renderBackground(Graphics g)
    {
        Drawing.fillRect(g, this.titleBackground, Style.colour("GRASS1"));
    }
    
    private void renderInfo(Graphics g)
    {
        Text.write(g, "Snake (Version 1.0)", 25, 750, "LEFT", Style.font("STANDARD"), Colour.getColourRGB(255, 255, 255));
    }
    
    private void renderMenu(Graphics g)
    {
        // Menu Frame
        UiTools.drawFrame(g, this.menuFrame);
        
        // Title Text
        Text.writeShadow(g, "SNAKE", Engine.getScreenCenterX(), 100, 2, "CENTER", Style.font("HEADER1"), Colour.getColourRGB(255, 255, 255), Colour.getColourRGB(150, 150, 150));
        
        // Option Text
        for(int x = 0; x < this.cursorMenu.size(); x++)
        {
            if(this.cursorOption == x) {Text.writeShadow(g, this.cursorMenu.get(x), 620, 360 + (x * 50), 1, "LEFT", Style.font("STANDARD"), Colour.getColourRGB(255, 255, 255), Colour.getColourRGB(150, 150, 150));}
            else {Text.write(g, this.cursorMenu.get(x), 620, 360 + (x * 50), "LEFT", Style.font("STANDARD"), Colour.getColourRGB(255, 255, 255));}
        }
        
        // Option Cursor
        BufferedImage image = this.cursorImage.getSubimage(0, 0, 100, 50);
        if(this.cursorTickFrame == 1) {image = this.cursorImage.getSubimage(100, 0, 100, 50);}
        Drawing.drawImage(g, Drawing.resize(image, 50, 25), 545, this.cursorOption * 50 + 340);
    }

    public void tick()
    {
        this.cursorTickNow += 1;
        if(this.cursorTickNow >= this.cursorTickMax)
        {
            this.cursorTickNow = 0;
            this.cursorTickFrame += 1;
            if(this.cursorTickFrame > 1) {this.cursorTickFrame = 0;}
        }
    }

}