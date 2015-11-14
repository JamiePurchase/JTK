/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game.entity.nature;

import game.entity.EntityAbstract;
import game.force.ForceAbstract;
import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class NatureAbstract extends EntityAbstract
{
    
    public NatureAbstract(ForceAbstract force, String ref, String name, int posX, int posY)
    {
        super(force, ref, name, posX, posY);
    }
    
    public void render(Graphics g)
    {
        GFX.drawImage(g, "resources/gfx/nature/tree1.png", this.getRenderPosX(), this.getRenderPosY(), 0, 0, 128, 128);
        //GFX.drawImage(g, "resources/gfx/nature/tree1.png", this.getRenderPosX(), this.getRenderPosY(), 128, 0, 128, 128);
        //GFX.drawImage(g, "resources/gfx/nature/tree1.png", this.getRenderPosX(), this.getRenderPosY(), 256, 0, 128, 128);
    }
    
    public void tick()
    {
        //
    }
    
}