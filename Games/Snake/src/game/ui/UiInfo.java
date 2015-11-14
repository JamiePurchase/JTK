/*
 * Snake
 * Jamie Purchase
 */
package game.ui;

import game.Game;
import gfx.Colour;
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
public class UiInfo
{
    private Game game;
    private final Rectangle box = new Rectangle(25, 25, 1316, 60);
    
    public UiInfo(Game game)
    {
        this.game = game;
    }
    
    public void render(Graphics g)
    {
        this.renderBox(g);
        this.renderInfo(g);
    }
    
    private void renderBox(Graphics g)
    {
        Drawing.fillRect(g, RectangleTools.offset(this.box, 5, 5), Color.BLACK);
        Drawing.fillRect(g, this.box, Color.BLACK);
        Drawing.drawRect(g, this.box, Color.WHITE);
    }
    
    private void renderInfo(Graphics g)
    {
        // Score
        Text.write(g, "SCORE: " + this.game.getScore(), 50, this.box.y + 40, "LEFT", Style.font("STANDARD"), Colour.getColourRGB(255, 255, 255));
        
        // Health
        Text.write(g, "HEALTH: " + this.game.snake.getValueNow("HEALTH") + " / " + this.game.snake.getValueMax("HEALTH"), 350, this.box.y + 40, "LEFT", Style.font("STANDARD"), Colour.getColourRGB(255, 255, 255));
        
        // Charge
        Text.write(g, "CHARGE: " + this.game.snake.getValueNow("CHARGE") + "%", 650, this.box.y + 40, "LEFT", Style.font("STANDARD"), Colour.getColourRGB(255, 255, 255));
    }
    
}