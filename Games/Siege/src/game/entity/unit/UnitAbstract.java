/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game.entity.unit;

import engine.Application;
import game.entity.EntityAbstract;
import game.force.ForceAbstract;
import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class UnitAbstract extends EntityAbstract
{
    private Direction direction;
    private UnitType type;
    
    public UnitAbstract(ForceAbstract force, String ref, String name, int posX, int posY, Direction direction, UnitType type)
    {
        super(force, ref, name, posX, posY);
        this.direction = direction;
        this.type = type;
    }
    
    public void move(Direction move)
    {
        if(move == Direction.NE)
        {
            this.posX += 12;
            this.posY -= 6;
        }
    }
    
    public void render(Graphics g)
    {
        int sheetX = 0;
        int sheetY = 0;
        if(this.direction == Direction.NE) {sheetY = 192;}
        if(this.direction == Direction.NW) {sheetY = 288;}
        if(this.direction == Direction.SW) {sheetY = 96;}
        GFX.drawImage(g, "resources/gfx/unit/temp1.png", this.getRenderPosX(), this.getRenderPosY(), sheetX, sheetY, 48, 96);
        
        // temp
        if(Application.getPropertyExists("MODE_DEBUG"))
        {
            boolean debugMode = (boolean) Application.getProperty("MODE_DEBUG");
            if(debugMode)
            {
                GFX.drawPolygon(g, this.getRenderNexus(), "PURPLE", false);
            }
        }
    }
    
    public void tick()
    {
        //
    }
    
}