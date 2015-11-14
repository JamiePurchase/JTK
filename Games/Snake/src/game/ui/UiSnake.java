/*
 * Snake
 * Jamie Purchase
 */
package game.ui;

import entities.snake.SnakeRole;
import game.Game;
import game.ui.bar.UiBar;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import styles.Style;

/**
 *
 * @author Jamie
 */
public class UiSnake
{
    private Game game;
    private final Rectangle statArea = new Rectangle(150, 575, 650, 150);
    private final UiBar statHealth = new UiBar(this.game, new Rectangle(372, 640, 200, 30), Style.colour("UI_BAR_HEALTH"));
    private final UiBar statEnergy = new UiBar(this.game, new Rectangle(372, 675, 200, 30), Style.colour("UI_BAR_ENERGY"));
    private final Rectangle portraitArea = new Rectangle(50, 550, 200, 200);
    
    public UiSnake(Game game)
    {
        this.game = game;
    }
    
    private BufferedImage getPortraitBkg()
    {
        return this.game.snake.getRoleActive().getImagePortrait();
    }
    
    private BufferedImage getPortraitFore()
    {
        //if(this.game.snake.getRole() == SnakeRole.GRASS) {return Drawing.getImageResource("resources/gfx/interface/snake/portrait3.png");}
        //if(this.game.snake.getRole() == SnakeRole.CRIMSON) {return Drawing.getImageResource("resources/gfx/interface/snake/portrait2.png");}
        //if(this.game.snake.getRole() == SnakeRole.WATER) {return Drawing.getImageResource("resources/gfx/interface/snake/portrait1.png");}
        //return null;
        
        // TEMP
        return Drawing.getImageResource("resources/gfx/interface/snake/portrait3.png");
    }
    
    public void render(Graphics g)
    {
        this.renderBackground(g);
        this.renderPortrait(g);
        this.renderInfo(g);
        this.renderHealth(g);
        this.renderEnergy(g);
    }
    
    private void renderBackground(Graphics g)
    {
        // Background
        Drawing.fadeRect(g, this.statArea, Color.BLACK, 0.5f);
        Drawing.fillRect(g, 150, 575, 650, 10, Style.colour("UI_BORDER"));
        Drawing.fillRect(g, 150, 715, 650, 10, Style.colour("UI_BORDER"));
        Drawing.fillRect(g, 790, 575, 10, 150, Style.colour("UI_BORDER"));
        Drawing.drawRect(g, this.statArea, Color.BLACK);
        Drawing.drawRect(g, 150, 585, 640, 130, Color.BLACK);
    }
    
    private void renderEnergy(Graphics g)
    {
        Text.write(g, "ENERGY", 270, 698, "LEFT", Style.font("UI_HEADER3"), Color.BLACK);
        this.statEnergy.render(g, this.game.snake.getValue("CHARGE"));
    }
    
    private void renderHealth(Graphics g)
    {
        Text.write(g, "HEALTH", 270, 663, "LEFT", Style.font("UI_HEADER3"), Color.BLACK);
        this.statHealth.render(g, this.game.snake.getValue("HEALTH"));
    }
    
    private void renderInfo(Graphics g)
    {
        Text.write(g, "JAMIE", 250, 620, "LEFT", Style.font("UI_HEADER1"), Color.BLACK);
        Text.write(g, "lv 1", 800, 630, "LEFT", Style.font("UI_HEADER2"), Color.BLACK);
    }
    
    private void renderPortrait(Graphics g)
    {
        // Background
        Drawing.drawImage(g, this.getPortraitBkg(), this.portraitArea.x, this.portraitArea.y);
        
        // Portrait
        //Drawing.drawImage(g, this.getPortraitFore(), this.portraitArea.x, this.portraitArea.y);
    }
    
}