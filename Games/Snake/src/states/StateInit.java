/*
 * Snake
 * Jamie Purchase
 */
package states;

import app.Engine;
import game.gateway.GameGateway;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import styles.Style;
import world.Solidity;
import world.World;
import world.WorldTerrain;

/**
 *
 * @author Jamie
 */
public class StateInit extends State
{
    private int tickNow, tickMax;
    private boolean preload;
    
    public StateInit()
    {
        this.tickNow = 0;
        this.tickMax = 12;
        this.preload = false;
        
        // TEMP
        //Engine.setAppVariable("PRELOAD_GAME", GameGateway.load("tutorial1"));
    }

    public void inputKeyPress(String key)
    {
        //
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
        // Background
        Drawing.fillScreen(g, Color.BLACK);
        
        // Loading Text
        Text.writeShadow(g, "LOADING", Engine.getScreenCenterX(), Engine.getScreenCenterY(), 2, "CENTER", Style.font("HEADER1"), Colour.getColourRGB(255, 255, 255), Colour.getColourRGB(150, 150, 150));
    }

    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow >= this.tickMax)
        {
            if(this.preload == false)
            {
                this.tickNow = 0;
                this.tickPreload();
            }
            else {Engine.setState(new StateTitle());}
        }
    }
    
    private void tickPreload()
    {
        // TEMP
        //World preloadWorld = new World("TEST1", 100, 100, new WorldTerrain("grass1", 0, 0), Solidity.STANDARD);
        
        //
        BufferedImage terrain = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gfx = terrain.createGraphics();
        Drawing.fillRect(gfx, 0, 0, 5000, 5000, Style.colour("GRASS1"));
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/cave2.png").getSubimage(0, 0, 200, 200), 0 * 50, 10 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/cave2.png").getSubimage(0, 0, 300, 200), 4 * 50, 8 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/cave2.png"), 10 * 50, 6 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/cave2.png"), 22 * 50, 8 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/cave2.png"), 34 * 50, 6 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/house1.png"), 24 * 50, 12 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/river1.png"), 0 * 50, 24 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/river1.png"), 10 * 50, 22 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/river1.png"), 16 * 50, 22 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/river2.png").getSubimage(0, 0, 400, 200), 22 * 50, 22 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/river2.png"), 36 * 50, 20 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/river2.png"), 44 * 50, 20 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/cave2.png").getSubimage(0, 0, 200, 200), 46 * 50, 8 * 50);
        Drawing.drawImage(gfx, Drawing.getImageResource("resources/gfx/nature/cave1.png"), 50 * 50, 8 * 50);
        Engine.setAppVariable("PRELOAD_WORLD1", terrain);
        //
        
        //Engine.setAppVariable("PRELOAD_WORLD1", preloadWorld.getTerrain());
        //Engine.setAppVariable("PRELOAD_WORLD1", );
        this.preload  = true;
    }

}