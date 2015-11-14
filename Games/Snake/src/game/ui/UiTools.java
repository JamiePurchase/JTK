/*
 * Snake
 * Jamie Purchase
 */
package game.ui;

import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import shapes.RectangleTools;
import styles.Style;

/**
 *
 * @author Jamie
 */
public class UiTools
{
    
    public static void drawFrame(Graphics g, Rectangle rect)
    {
        // Border
        Drawing.fillRect(g, RectangleTools.outerExclusive(rect, 10), Style.colour("UI_BORDER"));
        Drawing.drawRect(g, RectangleTools.outer(rect, 10), Color.BLACK);
        
        // Background
        Drawing.fadeRect(g, rect, Color.BLACK, 0.5f);
        Drawing.drawRect(g, rect, Color.BLACK);
    }

}