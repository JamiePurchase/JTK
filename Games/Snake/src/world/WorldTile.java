/*
 * Snake
 * Jamie Purchase
 */
package world;

/**
 *
 * @author Jamie
 */
public class WorldTile
{
    public Location location;
    public WorldTerrain terrain;
    public Solidity solidity;
    
    public WorldTile(Location location, WorldTerrain terrain, Solidity solidity)
    {
        this.location = location;
        this.terrain = terrain;
        this.solidity = solidity;
    }
    
    public String asData()
    {
        return this.location.asKey() + "|" + this.terrain.getKey() + "|" + this.solidity.name();
    }
    
}