/*
 * Snake
 * Jamie Purchase
 */
package world;

import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class LocationArea
{
    public int x, y, w, h;
    
    public LocationArea(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public boolean contains(Location location)
    {
        ArrayList<Location> array = this.getLocationArray();
        for(int x = 0; x < array.size(); x++)
        {
            if(array.get(x).isAt(location)) {return true;}
        }
        return false;
    }
    
    public Location getCorner()
    {
        return new Location(this.x, this.y);
    }
    
    public ArrayList<Location> getLocationArray()
    {
        ArrayList<Location> array = new ArrayList();
        for(int nx = 0; nx < this.w; nx++)
        {
            for(int ny = 0; ny < this.h; ny++) {array.add(new Location(this.x + nx, this.y + ny));}
        }
        return array;
    }
    
}