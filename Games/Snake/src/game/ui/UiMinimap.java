/*
 * Snake
 * Jamie Purchase
 */
package game.ui;

import entities.Direction;
import entities.enemies.Enemy;
import entities.fruit.Fruit;
import game.Game;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import shapes.RectangleTools;
import styles.Style;
import world.LocationArea;

/**
 *
 * @author Jamie
 */
public class UiMinimap
{
    private Game game;
    private final Rectangle box = new Rectangle(1121, 25, 220, 220);
    private final BufferedImage icon = Drawing.getImageResource("resources/gfx/interface/minimap1.png");
    
    public UiMinimap(Game game)
    {
        this.game = game;
    }
    
    private BufferedImage getIconEnemy()
    {
        return this.icon.getSubimage(20, 20, 20, 20);
    }
    
    private BufferedImage getIconFruit()
    {
        return this.icon.getSubimage(0, 20, 20, 20);
    }
    
    private BufferedImage getIconSnake()
    {
        Direction direction = this.game.snake.getDirection();
        if(direction == Direction.EAST) {return this.icon.getSubimage(60, 0, 20, 20);}
        if(direction == Direction.NORTH) {return this.icon.getSubimage(0, 0, 20, 20);}
        if(direction == Direction.SOUTH) {return this.icon.getSubimage(20, 0, 20, 20);}
        if(direction == Direction.WEST) {return this.icon.getSubimage(40, 0, 20, 20);}
        return null;
    }
    
    private int getRenderPosX(int posX)
    {
        return this.box.x + (20 * (posX + 1));
    }
    
    private int getRenderPosY(int posY)
    {
        return this.box.y + (20 * (posY + 1));
    }
    
    public void render(Graphics g)
    {
        this.renderBox(g);
        this.renderSnake(g);
        this.renderFruit(g, this.game.snake.location.getAreaNearby());
        this.renderEnemy(g, this.game.snake.location.getAreaNearby());
    }
    
    private void renderBox(Graphics g)
    {
        // Border
        Drawing.fillRect(g, RectangleTools.outerExclusive(this.box, 10), Style.colour("UI_BORDER"));
        Drawing.drawRect(g, RectangleTools.outer(this.box, 10), Color.BLACK);
        
        // Minimap
        Drawing.fadeRect(g, this.box, Color.BLACK, 0.5f);
        Drawing.drawRect(g, this.box, Color.BLACK);
    }
    
    private void renderEnemy(Graphics g, LocationArea area)
    {
        ArrayList<Enemy> enemyNear = this.game.getEnemyInArea(area);
        int iconX, iconY;
        for(int x = 0; x < enemyNear.size(); x++)
        {
            iconX = enemyNear.get(x).location.x - area.getCorner().x;
            iconY = enemyNear.get(x).location.y - area.getCorner().y;
            Drawing.drawImage(g, this.getIconEnemy(), this.getRenderPosX(iconX), this.getRenderPosY(iconY));
        }
    }
    
    private void renderFruit(Graphics g, LocationArea area)
    {
        ArrayList<Fruit> fruitNear = this.game.getFruitInArea(area);
        int iconX, iconY;
        for(int x = 0; x < fruitNear.size(); x++)
        {
            iconX = fruitNear.get(x).location.x - area.getCorner().x;
            iconY = fruitNear.get(x).location.y - area.getCorner().y;
            Drawing.drawImage(g, this.getIconFruit(), this.getRenderPosX(iconX), this.getRenderPosY(iconY));
        }
    }
    
    private void renderSnake(Graphics g)
    {
        Drawing.drawImage(g, this.getIconSnake(), this.getRenderPosX(4), this.getRenderPosY(4));
    }
    
}