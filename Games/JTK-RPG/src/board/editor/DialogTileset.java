/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.editor;

import dialog.DialogAbstract;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import states.board.StateBoardEditor;

/**
 *
 * @author Jamie
 */
public class DialogTileset extends DialogAbstract
{
    private StateBoardEditor state;
    private String sheet;
    private boolean scrollActiveH, scrollActiveV;
    private Rectangle scrollAreaH = new Rectangle(this.getAreaContent().x, this.getAreaContent().y + this.getAreaContent().height - 30, this.getAreaContent().width, 30);
    private Rectangle scrollAreaV = new Rectangle(this.getAreaContent().x + this.getAreaContent().width - 30, this.getAreaContent().y, 30, this.getAreaContent().y + this.getAreaContent().height);
    
    public DialogTileset(StateBoardEditor state, String sheet)
    {
        super("DIALOG_TILESET", new Rectangle(363, 145, 640, 478), "TILESET", "TITLEBAR_BKG");
        this.state = state;
        this.sheet = sheet;
        this.scrollActiveH = false;
        this.scrollActiveV = false;
    }
    
    private int getCursorTileX(Point point)
    {
        int pointX = point.x - this.getTileArea().x;
        int tileX = 0;
        while(pointX > 32)
        {
            pointX -= 32;
            tileX += 1;
        }
        return tileX;
    }
    
    private int getCursorTileY(Point point)
    {
        int pointY = point.y - this.getTileArea().y;
        int tileY = 0;
        while(pointY > 32)
        {
            pointY -= 32;
            tileY += 1;
        }
        return tileY;
    }
    
    private Rectangle getTileArea()
    {
        int width = this.getAreaContent().width;
        int height = this.getAreaContent().height;
        if(this.scrollActiveV) {width -= 30;}
        if(this.scrollActiveH) {height -= 30;}
        return new Rectangle(this.getAreaContent().x, this.getAreaContent().y, width, height);
    }
    
    public void inputKey(KeyEvent e)
    {
        //
    }
    
    public void inputMouse(MouseEvent e)
    {
        if(this.getTileArea().contains(e.getPoint())) {this.inputMouseTileset(e);}
    }
    
    private void inputMouseTileset(MouseEvent e)
    {
        // Get the location of the click
        int tileX = this.getCursorTileX(e.getPoint());
        int tileY = this.getCursorTileY(e.getPoint());
        
        // Is this a valid tile?
        //
        
        // Set the brush accordingly
        this.state.setBrushTerrainPaint(this.sheet, tileX, tileY);
        this.state.setDialogDone();
    }
    
    public void renderContent(Graphics g)
    {
        // Tileset
        this.renderContentTileset(g);
        
        // Scrollbars
        if(this.scrollActiveH) {this.renderContentScrollH(g);}
        if(this.scrollActiveV) {this.renderContentScrollV(g);}
        
        // Border
        GFX.drawRect(g, this.getAreaContent(), this.border, false);
    }
    
    private void renderContentScrollH(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.scrollAreaH, "WHITE", true);
        
        // Border
        GFX.drawRect(g, this.scrollAreaH, this.border, false);
    }
    
    private void renderContentScrollV(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.scrollAreaV, "WHITE", true);
        
        // Border
        GFX.drawRect(g, this.scrollAreaV, this.border, false);
    }
    
    private void renderContentTileset(Graphics g)
    {
        //GFX.drawImage(g, this.sheet, this.area.x + 5, this.area.y + 5, 0, 0, 640, 384);
        GFX.drawImage(g, this.sheet, this.getTileArea().x, this.getTileArea().y);
    }
    
    public void tick()
    {
        //
    }

}