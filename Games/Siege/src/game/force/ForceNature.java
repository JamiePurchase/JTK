/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game.force;

import game.GameData;
import game.entity.EntityAbstract;
import game.entity.nature.NatureAbstract;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class ForceNature extends ForceAbstract
{
    private ArrayList<NatureAbstract> nature;
    
    public ForceNature(GameData game)
    {
        super(game, "NATURE", ForceType.NATURE);
        this.nature = new ArrayList();
    }
    
    public void addNature(String ref, String name, int posX, int posY)
    {
        this.nature.add(new NatureAbstract(this, ref, name, posX, posY));
    }
    
    public ArrayList<EntityAbstract> getEntityList()
    {
        ArrayList<EntityAbstract> list = new ArrayList();
        EntityAbstract entity;
        for(int x = 0; x < this.nature.size(); x++)
        {
            entity = (EntityAbstract) this.nature.get(x);
            list.add(entity);
        }
        return list;
    }
    
    public void tick()
    {
        // Nature
        for(int x = 0; x < this.nature.size(); x++)
        {
            this.nature.get(x).tick();
        }
    }
    
}