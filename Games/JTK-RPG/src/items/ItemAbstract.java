/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package items;

/**
 *
 * @author Jamie
 */
public abstract class ItemAbstract
{
    private String ref;
    protected String name;
    
    public ItemAbstract(String ref, String name)
    {
        this.ref = ref;
        this.name = name;
    }
    
}