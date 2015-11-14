/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game.force;

import game.GameData;
import game.entity.EntityAbstract;
import game.entity.unit.Direction;
import game.entity.unit.UnitAbstract;
import game.entity.unit.UnitType;
import game.resource.ResourceType;
import game.resource.ResourceValue;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jamie
 */
public class ForceRuler extends ForceAbstract
{
    private HashMap<ResourceType, ResourceValue> resource;
    //private ArrayList<ConstructAbstract> constuct;
    private ArrayList<UnitAbstract> unit;
    
    public ForceRuler(GameData game, String ref, ForceType type)
    {
        super(game, ref, type);
        this.resource = new HashMap();
        this.resource.put(ResourceType.FOOD, new ResourceValue(900, 1500));
        this.resource.put(ResourceType.GOLD, new ResourceValue(400, 1500));
        this.resource.put(ResourceType.STONE, new ResourceValue(100, 1500));
        this.resource.put(ResourceType.WOOD, new ResourceValue(600, 1500));
        //this.construct = new ArrayList();
        this.unit = new ArrayList();
    }
    
    public void addUnit(String ref, String name, int posX, int posY, Direction direction, UnitType type)
    {
        this.unit.add(new UnitAbstract(this, ref, name, posX, posY, direction, type));
    }
    
    /*public ArrayList<ConstructAbstract> getConstructList()
    {
        return this.construct;
    }*/
    
    public ArrayList<EntityAbstract> getEntityList()
    {
        ArrayList<EntityAbstract> list = new ArrayList();
        EntityAbstract entity;
        /*for(int x = 0; x < this.construct.size(); x++)
        {
            entity = (EntityAbstract) this.construct.get(x);
            list.add(entity);
        }*/
        for(int y = 0; y < this.unit.size(); y++)
        {
            entity = (EntityAbstract) this.unit.get(y);
            list.add(entity);
        }
        return list;
    }
    
    public ArrayList<UnitAbstract> getUnitList()
    {
        return this.unit;
    }
    
    public ResourceValue getResource(ResourceType type)
    {
        return this.resource.get(type);
    }
    
    public void tick()
    {
        // resources?
        //this.tickConstruct();
        this.tickUnit();
    }
    
    private void tickUnit()
    {
        for(int x = 0; x < this.unit.size(); x++)
        {
            this.unit.get(x).tick();
        }
    }
    
}