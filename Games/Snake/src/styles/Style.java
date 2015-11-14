/*
 * Snake
 * Jamie Purchase
 */
package styles;

import gfx.Colour;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Jamie
 */
public class Style
{
    
    public static Color colour(String ref)
    {
        if(ref.equals("BLACK")) {return Colour.getColourRGB(0, 0, 0);}
        if(ref.equals("GRASS1")) {return Colour.getColourRGB(149, 203, 67);}
        if(ref.equals("INTERFACE1")) {return Colour.getColourRGB(207, 129, 75);}
        if(ref.equals("RED1")) {return Colour.getColourRGB(237, 28, 36);}
        if(ref.equals("RED2")) {return Colour.getColourRGB(136, 0, 21);}
        
        //
        if(ref.equals("TOOL_BAR_FILL")) {return Colour.getColourRGB(142, 166, 143);}
        if(ref.equals("TOOL_INPUT_FILL")) {return Colour.getColourRGB(132, 180, 131);}
        if(ref.equals("TOOL_INPUT_HOVER")) {return Colour.getColourRGB(119, 173, 118);}
        if(ref.equals("UI_BAR_ENERGY")) {return Colour.getColourRGB(78, 138, 40);}
        if(ref.equals("UI_BAR_HEALTH")) {return Colour.getColourRGB(134, 49, 45);}
        if(ref.equals("UI_BORDER")) {return Colour.getColourRGB(185, 122, 87);}
        return Color.BLACK;
    }
    
    public static Font font(String ref)
    {
        if(ref.equals("HEADER1")) {return new Font("Vanthian Ragnarok", Font.PLAIN, 64);}
        if(ref.equals("STANDARD")) {return new Font("Segoe Print", Font.PLAIN, 24);}
        if(ref.equals("TOOL")) {return new Font("Courier New", Font.PLAIN, 24);}
        if(ref.equals("TOOL_INPUT")) {return new Font("Courier New", Font.PLAIN, 22);}
        if(ref.equals("TOOL_MINI")) {return new Font("Courier New", Font.PLAIN, 18);}
        if(ref.equals("UI_HEADER1")) {return new Font("Vanthian Ragnarok", Font.PLAIN, 32);}
        if(ref.equals("UI_HEADER2")) {return new Font("Vanthian Ragnarok", Font.PLAIN, 26);}
        if(ref.equals("UI_HEADER3")) {return new Font("Vanthian Ragnarok", Font.BOLD, 22);}
        return new Font("Segoe Print", Font.PLAIN, 24);
    }
    
}