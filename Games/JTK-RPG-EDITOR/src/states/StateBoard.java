/*
 * JTK-RPG-EDITOR
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package states;

import board.BoardAbstract;
import board.tile.TileAbstract;
import board.ui.ChatAbstract;
import board.ui.MenuAbstract;
import campaign.CampaignData;
import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class StateBoard extends StateAbstract
{
    // Board
    private BoardAbstract board;
    
    // Pause
    private boolean pauseMenu = false;
    private MenuAbstract pauseMenuUI;
    
    // Chat
    private boolean chatActive = false;
    private ChatAbstract chatDisplay = null;

    public StateBoard()
    {
        // Board
        super("BOARD");
        this.board = new BoardAbstract("BOARD1");
        
        // Pause
        this.pauseMenuUI = new MenuAbstract();
        this.pauseMenuUI.addOption("PM_IN", "INVENTORY", 90, 80);
        this.pauseMenuUI.addOption("PM_EQ", "EQUIPMENT", 90, 130);
        this.pauseMenuUI.addOption("PM_AB", "ABILITIES", 90, 180);
        this.pauseMenuUI.addOption("PM_JO", "JOURNAL", 90, 230);
        this.pauseMenuUI.addOption("PM_OP", "OPTIONS", 90, 280);
        this.pauseMenuUI.addOption("PM_DO", "DONE", 90, 330);
        
        // Fence
        this.board.setTileAt(5, 5, new TileAbstract("resources/gfx/scenery/village1.png", 1, 0));
        this.board.setTileAt(6, 5, new TileAbstract("resources/gfx/scenery/village1.png", 2, 0));
        this.board.setTileAt(7, 5, new TileAbstract("resources/gfx/scenery/village1.png", 2, 0));
        this.board.setTileAt(8, 5, new TileAbstract("resources/gfx/scenery/village1.png", 2, 0));
        this.board.setTileAt(9, 5, new TileAbstract("resources/gfx/scenery/village1.png", 3, 0));
        this.board.setTileAt(10, 5, new TileAbstract("resources/gfx/scenery/village1.png", 2, 0));
        this.board.setTileAt(11, 5, new TileAbstract("resources/gfx/scenery/village1.png", 2, 0));
        this.board.setTileAt(12, 5, new TileAbstract("resources/gfx/scenery/village1.png", 4, 0));
        this.board.addZone("", 5, 5, 12, 5, true);
        
        // Tree
        this.board.setTileAt(2, 8, new TileAbstract("resources/gfx/scenery/village1.png", 5, 0));
        this.board.setTileAt(3, 8, new TileAbstract("resources/gfx/scenery/village1.png", 6, 0));
        this.board.setTileAt(2, 9, new TileAbstract("resources/gfx/scenery/village1.png", 5, 1));
        this.board.setTileAt(3, 9, new TileAbstract("resources/gfx/scenery/village1.png", 6, 1));
        this.board.addZone("", 2, 8, 3, 9, true);
        
        // House
        this.board.setTileAt(26, 8, new TileAbstract("resources/gfx/scenery/village2.png", 0, 0));
        this.board.setTileAt(27, 8, new TileAbstract("resources/gfx/scenery/village2.png", 1, 0));
        this.board.setTileAt(28, 8, new TileAbstract("resources/gfx/scenery/village2.png", 1, 0));
        this.board.setTileAt(29, 8, new TileAbstract("resources/gfx/scenery/village2.png", 5, 0));
        this.board.setTileAt(26, 9, new TileAbstract("resources/gfx/scenery/village2.png", 0, 2));
        this.board.setTileAt(27, 9, new TileAbstract("resources/gfx/scenery/village2.png", 1, 2));
        this.board.setTileAt(28, 9, new TileAbstract("resources/gfx/scenery/village2.png", 1, 2));
        this.board.setTileAt(29, 9, new TileAbstract("resources/gfx/scenery/village2.png", 5, 2));
        this.board.setTileAt(26, 10, new TileAbstract("resources/gfx/scenery/village2.png", 0, 3));
        this.board.setTileAt(27, 10, new TileAbstract("resources/gfx/scenery/village2.png", 1, 3));
        this.board.setTileAt(28, 10, new TileAbstract("resources/gfx/scenery/village2.png", 1, 3));
        this.board.setTileAt(29, 10, new TileAbstract("resources/gfx/scenery/village2.png", 5, 3));
        this.board.setTileAt(26, 11, new TileAbstract("resources/gfx/scenery/village2.png", 0, 5));
        this.board.setTileAt(27, 11, new TileAbstract("resources/gfx/scenery/village2.png", 3, 5));
        this.board.setTileAt(28, 11, new TileAbstract("resources/gfx/scenery/village2.png", 2, 5));
        this.board.setTileAt(29, 11, new TileAbstract("resources/gfx/scenery/village2.png", 5, 5));
        this.board.addZone("", 26, 8, 29, 11, true);
        
        // Test
        //this.chat(new ChatAbstract());
    }
    
    public void chat()
    {
        this.chatActive = false;
        this.chatDisplay = null;
    }
    
    public void chat(ChatAbstract newChat)
    {
        this.chatActive = true;
        this.chatDisplay = newChat;
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        if(this.chatActive) {this.inputKeyChat(e);}
        else if(this.pauseMenu) {this.inputKeyMenu(e);}
        
        // Board
        else
        {
            // Enter
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                this.pauseMenu = true;
                return;
            }
            this.board.inputKey(e);
        }
    }
    
    private void inputKeyChat(KeyEvent e)
    {
        this.chat();
    }
    
    private void inputKeyMenu(KeyEvent e)
    {
        // Enter
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String ref = this.pauseMenuUI.getOptionRef();
            if(ref.equals("PM_DO"))
            {
                this.pauseMenu = false;
                return;
            }
        }
        
        // Escape
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            this.pauseMenu = false;
        }
        
        // Menu
        this.pauseMenuUI.inputKey(e);
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        // Board
        this.board.render(g, Application.getAppArea());
        
        // Chat Display
        if(this.chatActive) {this.chatDisplay.render(g);}
        
        // Pause Menu
        if(this.pauseMenu) {this.renderMenu(g);}
    }
    
    private void renderMenu(Graphics g)
    {
        // temp
        Rectangle menuArea = new Rectangle(40, 40, 300, 688);
        ArrayList<String> menuOption = new ArrayList();
        
        // Shadow
        GFX.drawRect(g, new Rectangle(menuArea.x + 4, menuArea.y + 4, menuArea.width, menuArea.height), "BLACK", true);
        
        // Background
        GFX.drawRect(g, menuArea, "WHITE", true);
        
        // Border
        GFX.drawRect(g, menuArea, "BLACK", false);
        
        // temp
        this.pauseMenuUI.render(g);
        
        // Options
        /*for(int x = 0; x < menuOption.size(); x++)
        {
            GFX.write(g, menuOption.get(x), menuArea.x + 50, menuArea.y + 40 + (50 * x), "LEFT", "MENU_OPTION", "BLACK");
        }
        
        // Cursor
        GFX.write(g, ">", menuArea.x + 25, menuArea.y + 40, "LEFT", "MENU_OPTION", "BLACK");*/
        
        // temp
        this.renderMenuParty(g);
    }
    
    private void renderMenuParty(Graphics g)
    {
        CampaignData campaign = (CampaignData) Application.getProperty("CAMPAIGN_DATA");
        GFX.drawRect(g, new Rectangle(600, 40, 400, 688), "WHITE", true);
        GFX.drawRect(g, new Rectangle(600, 40, 400, 688), "BLACK", false);
        for(int x = 0; x < campaign.getCharacterList().size(); x++)
        {
            GFX.write(g, campaign.getCharacterList().get(x).getName(), 650, 80 + (30 * x), "LEFT", "MENU_OPTION", "BLACK");
        }
    }

    @Override
    public void stateChange()
    {
        //
    }

    @Override
    public void stateFinish()
    {
        //
    }

    @Override
    public void tick()
    {
        this.board.tick();
    }

}