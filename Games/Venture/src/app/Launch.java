/**
 * Venture Jamie Purchase JTK-RPG 07/11/2015
 */
package app;

import campaign.CampaignData;
import campaign.party.CharacterData;
import engine.Application;
import java.awt.Font;
import states.StateInit;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        // Application
        new Application("VENTURE", "1.0", "").start(false);
        
        // Style: Colours
        Application.setStyleColour("BKG_GRASS", 121, 174, 104);
        Application.setStyleColour("BKG_TITLE", 88, 21, 56);
        Application.setStyleColour("HEADER1", 255, 255, 255);
        Application.setStyleColour("HEADER1_SHADOW", 150, 50, 50);
        Application.setStyleColour("TITLE_SHADOW", 150, 50, 50);
        //
        Application.setStyleColour("DIALOG_SHADOW", 150, 50, 50);
        Application.setStyleColour("MENU_OPTION_SHADOW", 150, 50, 50);
        Application.setStyleColour("MENU_OPTION_TEXT", 0, 0, 0);
        Application.setStyleColour("TITLEBAR_BKG", 150, 50, 50);
        Application.setStyleColour("TOOLBAR_BKG", 115, 128, 136);
        Application.setStyleColour("TOOLBAR_BORDER", 0, 0, 0);
        Application.setStyleColour("TOOLBAR_ITEM_HIGHLIGHT", 106, 123, 145);
        Application.setStyleColour("TOOLBAR_SCROLL_BUTTON", 115, 128, 136);
        Application.setStyleColour("TOOLBAR_SCROLL_BUTTON_HIGHLIGHT", 106, 123, 145);
        Application.setStyleColour("UI_OPTION", 0, 0, 0);
        Application.setStyleColour("UI_SHADOW", 150, 50, 50);
        Application.setStyleColour("VISUAL_SHADOW", 150, 50, 50);
        Application.setStyleColour("VISUAL_STANDARD", 0, 0, 0);
        
        // Style: Fonts
        Application.setStyleFont("CHAT_SPEAKER", new Font("Times New Roman", Font.BOLD, 36));
        Application.setStyleFont("CHAT_STANDARD", new Font("Times New Roman", Font.PLAIN, 28));
        Application.setStyleFont("HEADER1", new Font("Don't Mix Yer Drinks", Font.PLAIN, 96));
        Application.setStyleFont("HEADER2", new Font("Don't Mix Yer Drinks", Font.ITALIC, 48));
        Application.setStyleFont("MENU", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("MENU_OPTION", new Font("Times New Roman", Font.PLAIN, 28));
        Application.setStyleFont("TITLEBAR_HEADER", new Font("Don't Mix Yer Drinks", Font.BOLD, 28));
        Application.setStyleFont("TITLE_INFO", new Font("Don't Mix Yer Drinks", Font.PLAIN, 32));
        Application.setStyleFont("TITLE_MENU", new Font("Don't Mix Yer Drinks", Font.BOLD, 32));
        Application.setStyleFont("TOOLBAR_ITEM", new Font("Courier New", Font.PLAIN, 18));
        Application.setStyleFont("UI_OPTION", new Font("Don't Mix Yer Drinks", Font.BOLD, 32));
        Application.setStyleFont("VISUAL_STANDARD", new Font("Don't Mix Yer Drinks", Font.BOLD, 46));
        
        // State
        Application.stateNew(new StateInit());
        
        // Campaign
        CampaignData campaign = new CampaignData("");
        campaign.addCharacter(new CharacterData("JAMIE"));
        Application.setProperty("CAMPAIGN_DATA", campaign);
        
        // Temp
        campaign.save();
    }
    
}