/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game.force;

import game.GameData;
import game.entity.EntityAbstract;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public abstract class ForceAbstract
{
    protected GameData game;
    protected String name;
    protected ForceType type;
    
    public ForceAbstract(GameData game, String name, ForceType type)
    {
        this.game = game;
        this.name = name;
        this.type = type;
    }
    
    public abstract ArrayList<EntityAbstract> getEntityList();
    
    public GameData getGame()
    {
        return this.game;
    }
    
    public abstract void tick();

}