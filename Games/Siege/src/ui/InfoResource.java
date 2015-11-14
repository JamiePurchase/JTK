/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package ui;

import game.force.ForceRuler;
import game.resource.ResourceType;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class InfoResource
{
    private ForceRuler force;
    private final Rectangle area;
    private ResourceType type;
    private String icon;
    
    public InfoResource(ForceRuler force, Rectangle area, ResourceType type, String icon)
    {
        this.force = force;
        this.area = area;
        this.type = type;
        this.icon = icon;
    }
    
    public void render(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.area, "OPTION_BKG", true);
        
        // Border
        GFX.drawRect(g, this.area, "OPTION_BORDER", false);
        
        // Icon
        GFX.drawImage(g, this.icon, this.area.x + 4, this.area.y);
        
        // Value
        GFX.write(g, this.force.getResource(this.type).getValueString(), this.area.x + 105, this.area.y + 23, "RIGHT", "INFO_STANDARD", "INFO_TEXT");
    }
    
}