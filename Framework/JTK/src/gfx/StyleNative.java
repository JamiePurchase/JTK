/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package gfx;

import console.Output;
import engine.Application;
import static gfx.Style.setStyleColour;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Jamie
 */
public class StyleNative
{
    
    public static void load()
    {
        // Colours
        Application.setStyleColour("BLACK", Color.BLACK);
        Application.setStyleColour("GREEN", GFX.getColourRGB(0, 200, 0));
        Application.setStyleColour("PURPLE", GFX.getColourRGB(200, 0, 200));
        Application.setStyleColour("WHITE", Color.WHITE);
        
        // Fonts
        Application.setStyleFont("INPUT", new Font("Courier New", Font.PLAIN, 14));
        Application.setStyleFont("STANDARD", new Font("Courier New", Font.PLAIN, 14));
        Application.setStyleFont("STANDARD_BOLD", new Font("Courier New", Font.BOLD, 14));
        Application.setStyleFont("STANDARD_ERROR", new Font("Courier New", Font.PLAIN, 14));
        
        // Debug
        Output.print("NATIVE STYLES LOADED");
    }
    
}