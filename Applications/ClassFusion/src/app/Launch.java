/**
 * Class Fusion
 */
package app;

import engine.Application;
import engine.display.DisplayTitlebar2;
import java.awt.Font;
import states.StateIntro;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        // Application
        new Application("CLASS FUSION", 1200, 600, "1.0", "").start(false);
        Application.setTitlebar(new DisplayTitlebar2("CLASS FUSION", "BLACK", "TITLEBAR_TITLE", "TITLEBAR_BKG", "BLACK"));
        
        // Style: Colours
        Application.setStyleColour("TITLEBAR_BKG", 0, 94, 138);
        Application.setStyleColour("TITLEBAR_BUTTON", 0, 65, 100);
        Application.setStyleColour("TITLEBAR_BUTTON_HOVER", 0, 75, 120);
        
        // Style: Fonts
        Application.setStyleFont("HEADER1", new Font("Times New Roman", Font.PLAIN, 42));
        Application.setStyleFont("INFO_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("MENU", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("OPTION_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        //
        Application.setStyleFont("STANDARD", new Font("Don't Mix Yer Drinks", Font.PLAIN, 26));
        //Application.setStyleFont("STANDARD", new Font("Jorvik Informal", Font.PLAIN, 22));
        Application.setStyleFont("TITLEBAR_TITLE", new Font("Vanthian Ragnarok", Font.BOLD, 22));
        Application.setStyleFont("TITLEBAR_BUTTON", new Font("Courier New", Font.BOLD, 18));
        
        // State
        Application.stateNew(new StateIntro());
        
        // Properties
        //Application.setProperty("MODE_DEBUG", true);
    }
    
}