/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.ui;

import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class ChatAbstract
{
    private final Rectangle area = new Rectangle(50, 538, 1266, 200);
    private ArrayList<String> messageList;
    private boolean speakerShow;
    private String speakerName;
    
    public ChatAbstract()
    {
        this.messageList = new ArrayList();
        this.speakerShow = false;
        this.speakerName = "";
    }
    
    public void render(Graphics g)
    {
        // Shadow
        GFX.drawRect(g, new Rectangle(this.area.x + 4, this.area.y + 4, this.area.width, this.area.height), "BLACK", true);
        
        // Background
        GFX.drawRect(g, this.area, "WHITE", true);
        
        // Border
        GFX.drawRect(g, this.area, "BLACK", false);
        
        // Speaker
        GFX.write(g, "SPEAKER", this.area.x + 35, this.area.y + 45, "LEFT", "CHAT_SPEAKER", "BLACK");
        
        // Message
        GFX.write(g, "MESSAGE 1", this.area.x + 35, this.area.y + 90, "LEFT", "CHAT_STANDARD", "BLACK");
        GFX.write(g, "MESSAGE 2", this.area.x + 35, this.area.y + 130, "LEFT", "CHAT_STANDARD", "BLACK");
        GFX.write(g, "MESSAGE 3", this.area.x + 35, this.area.y + 170, "LEFT", "CHAT_STANDARD", "BLACK");
    }
    
}