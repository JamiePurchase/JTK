/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game.resource;

/**
 *
 * @author Jamie
 */
public class ResourceValue
{
    public int value, max;
    
    public ResourceValue(int value, int max)
    {
        this.value = value;
        this.max = max;
    }
    
    public String getValueString()
    {
        return "" + this.value;
    }
    
}