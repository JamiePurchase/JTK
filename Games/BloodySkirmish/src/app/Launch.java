/**
 * Bloody Skirmish Jamie Purchase JTK Framework 07-11-2015
 */
package app;

import engine.Application;
import java.awt.Font;
import states.StateBattle;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        // Application
        new Application("BLOODY SKIRMISH", "1.0", "").start(false);
        
        // Style: Colours
        Application.setStyleColour("BKG_GRASS", 121, 174, 104);
        
        // Style: Fonts
        Application.setStyleFont("HEADER1", new Font("Times New Roman", Font.PLAIN, 42));
        Application.setStyleFont("INFO_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("MENU", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("OPTION_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        
        // State
        Application.stateNew(new StateBattle());
        
        // Properties
        //Application.setProperty("MODE_DEBUG", true);
    }
    
}