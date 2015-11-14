/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package gfx;

import engine.Application;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

/**
 *
 * @author Jamie
 */
public class Style
{    
    private static HashMap<String, Color> styleColour = new HashMap();
    private static HashMap<String, Font> styleFont = new HashMap();
    
    public static Color getStyleColour(String key)
    {
        return styleColour.get(key);
    }
    
    public static void getStyleColourList()
    {
        String[] keys = (String[]) styleColour.keySet().toArray();
        System.out.println("STYLE COLOUR LIST");
        for(int x = 0; x < styleColour.size(); x++)
        {
            System.out.println(" - " + keys[x] + " (" + styleColour.get(keys[x]).toString() + ")");
        }
    }
    
    public static Font getStyleFont(String key)
    {
        return styleFont.get(key);
    }
    
    public static void init()
    {
        // Colours
        setStyleColour("BLACK", Color.BLACK);
        setStyleColour("GREEN", GFX.getColourRGB(0, 200, 0));
        setStyleColour("PURPLE", GFX.getColourRGB(200, 0, 200));
        setStyleColour("WHITE", Color.WHITE);
        
        // Fonts
        setStyleFont("INPUT", new Font("Courier New", Font.PLAIN, 14));
        setStyleFont("STANDARD", new Font("Courier New", Font.PLAIN, 14));
        setStyleFont("STANDARD_BOLD", new Font("Courier New", Font.BOLD, 14));
        setStyleFont("STANDARD_ERROR", new Font("Courier New", Font.PLAIN, 14));
    }
    
    public static void setStyleColour(String key, Color colour)
    {
        styleColour.put(key, colour);
    }
    
    public static void setStyleColour(String key, int r, int g, int b)
    {
        styleColour.put(key, GFX.getColourRGB(r, g, b));
    }
    
    public static void setStyleFont(String key, Font font)
    {
        styleFont.put(key, font);
    }
    
}
