/**
 * JTK-TOOLS Jamie Purchase 13/11/2015
 */
package tools;

import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class RectangleTool
{

    public static Rectangle offset(Rectangle original, int offsetX, int offsetY)
    {
        return new Rectangle(original.x + offsetX, original.y + offsetY, original.width, original.height);
    }

}