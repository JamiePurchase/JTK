/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package states.board;

import board.BoardAbstract;
import board.BoardEdit;
import board.editor.BrushType;
import board.editor.DialogTileset;
import board.tile.TileAbstract;
import board.ui.ChatAbstract;
import campaign.CampaignData;
import dialog.DialogAbstract;
import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import toolbar.ToolbarAbstract;
import tools.BooleanTool;
import tools.RectangleTool;

/**
 *
 * @author Jamie
 */
public class StateBoardEditor extends StateBoardAbstract
{
    // File
    private String filePath, fileName;
    private boolean fileChanged;
    
    // Interface
    private Rectangle uiTitleArea = new Rectangle(0, 0, 1366, 30);
    private ToolbarAbstract uiToolbarObject;
    private Rectangle uiToolbarArea = new Rectangle(0, 30, 1366, 68);
    private Rectangle uiBoardArea = new Rectangle(0, 98, 1088, 640);
    private Rectangle uiDetailArea = new Rectangle(1088, 98, 278, 640);
    private Rectangle uiDetailTerrainTile = new Rectangle(this.uiDetailArea.x + 50, this.uiDetailArea.y + 50, 32, 32);
    private Rectangle uiStatusArea = new Rectangle(0, 738, 1366, 30);
    private String uiStatusMessageText = "";
    private int uiStatusMessageTick = 0;
    private DialogAbstract uiDialog = null;
    
    // Options
    private boolean optionGrid = false;
    
    // Brush
    private BrushType brushType;
    private String brushTerrainPaintSheetF, brushTerrainSelectRef;
    private boolean brushTerrainSelectActive, brushHarvestSelectActive, brushZoneSelectActive;
    private int brushTerrainPaintSheetX, brushTerrainPaintSheetY, brushHarvestSelectPos, brushZoneSelectPos;
    
    public StateBoardEditor(String ref, String board)
    {
        // Board
        super(ref);
        this.board = new BoardEdit("REF", this);
        this.board.setRenderArea(this.uiBoardArea);
        
        // Temp
        //this.board.save();
        
        // File
        this.filePath = "";
        this.fileName = "";
        this.fileChanged = false;
        
        // Interface
        this.uiToolbarObject = new ToolbarAbstract("TOOLBAR", this.uiToolbarArea, "TOOLBAR_BKG", "BLACK", "TOOLBAR_BKG", "TOOLBAR_ITEM", "TOOLBAR_ITEM_HIGHLIGHT");
        this.uiToolbarObject.setLabelStyle("BLACK", "TOOLBAR_ITEM");
        this.uiToolbarObject.addLabel("FILE", 15);
        this.uiToolbarObject.addItem("TOOLBAR_FILE_NEW", "resources/gfx/menu/tool_file_new.png", 15, 28, 32);
        this.uiToolbarObject.addItem("TOOLBAR_FILE_OPEN", "resources/gfx/menu/tool_file_open.png", 65, 28, 32);
        this.uiToolbarObject.addItem("TOOLBAR_FILE_SAVE", "resources/gfx/menu/tool_file_new.png", 115, 28, 32);
        this.uiToolbarObject.addItem("TOOLBAR_FILE_EXIT", "resources/gfx/menu/tool_file_exit.png", 165, 28, 32);
        this.uiToolbarObject.addDivider(400);
        this.uiToolbarObject.addLabel("VIEW", 415);
        this.uiToolbarObject.addItem("TOOLBAR_VIEW_GRID", "resources/gfx/menu/tool_file_new.png", 415, 28, 32);
        this.uiToolbarObject.addDivider(800);
        this.uiToolbarObject.addLabel("BRUSH", 825);
        this.uiToolbarObject.addItem("TOOLBAR_BRUSH_TERRAIN", "resources/gfx/menu/tool_file_new.png", 825, 28, 32);
        this.uiToolbarObject.setBorder(true, true, true, true);
        
        //GFX.drawLine(g, this.uiToolbarArea.x + 400, this.uiToolbarArea.y, this.uiToolbarArea.x + 400, this.uiToolbarArea.y + this.uiToolbarArea.height, "TOOLBAR_BORDER");
        //GFX.drawLine(g, this.uiToolbarArea.x + 800, this.uiToolbarArea.y, this.uiToolbarArea.x + 800, this.uiToolbarArea.y + this.uiToolbarArea.height, "TOOLBAR_BORDER");
        
        //GFX.write(g, "FILE", this.uiToolbarArea.x + 15, this.uiToolbarArea.y + 20, "LEFT", "TOOLBAR_ITEM", "BLACK");
        //GFX.write(g, "VIEW", this.uiToolbarArea.x + 415, this.uiToolbarArea.y + 20, "LEFT", "TOOLBAR_ITEM", "BLACK");
        //GFX.write(g, "BRUSH", this.uiToolbarArea.x + 825, this.uiToolbarArea.y + 20, "LEFT", "TOOLBAR_ITEM", "BLACK");
        
        // Brush
        this.brushType = BrushType.TERRAIN_PAINT;
        this.brushTerrainPaintSheetF = "resources/gfx/scenery/village1.png";
        this.brushTerrainPaintSheetX = 1;
        this.brushTerrainPaintSheetY = 1;
        this.brushTerrainSelectActive = false;
        this.brushTerrainSelectRef = this.board.getTileRef(0, 0);
        this.brushHarvestSelectActive = false;
        this.brushHarvestSelectPos = 0;
        this.brushZoneSelectActive = false;
        this.brushZoneSelectPos = 0;
    }
    
    private TileAbstract getBrushTerrainPaint()
    {
        return new TileAbstract(this.brushTerrainPaintSheetF, this.brushTerrainPaintSheetX, this.brushTerrainPaintSheetY);
    }
    
    private String getCursorStatus(Point point)
    {
        // Create a string
        String cursor = "X --- Y ---";
        
        // Is the cursor on the board?
        if(this.uiBoardArea.contains(point))
        {
            // Get x value
            cursor = "X ";
            int x = this.getCursorTileX(point);
            if(x < 10) {cursor += "00" + x + " ";}
            else if(x < 100) {cursor += "0" + x + " ";}
            else {cursor += x + " ";}

            // Get y value
            cursor += "Y ";
            int y = this.getCursorTileY(point);
            if(y < 10) {cursor += "00" + y + " ";}
            else if(y < 100) {cursor += "0" + y + " ";}
            else {cursor += y;}
        }
        
        // Return string
        return cursor;
    }
    
    private int getCursorTileX(Point point)
    {
        int pointX = point.x - this.uiBoardArea.x;
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
        int pointY = point.y - this.uiBoardArea.y;
        int tileY = 0;
        while(pointY > 32)
        {
            pointY -= 32;
            tileY += 1;
        }
        return tileY;
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        //
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        // Dialog
        if(this.uiDialog != null)
        {
            this.uiDialog.inputMouse(e);
            return;
        }
        
        // Editor
        if(this.uiTitleArea.contains(e.getPoint())) {this.inputMouseTitlebar(e);}
        if(this.uiToolbarArea.contains(e.getPoint())) {this.inputMouseToolbar(e);}
        if(this.uiBoardArea.contains(e.getPoint())) {this.inputMouseBoard(e);}
        if(this.uiDetailArea.contains(e.getPoint())) {this.inputMouseDetail(e);}
    }
    
    private void inputMouseBoard(MouseEvent e)
    {
        // Get tile (note: not taking scroll into account)
        int tileX = this.getCursorTileX(e.getPoint());
        int tileY = this.getCursorTileY(e.getPoint());
        
        // Terrain Paint
        if(this.brushType == BrushType.TERRAIN_PAINT)
        {
            this.board.setTileAt(tileX, tileY, this.getBrushTerrainPaint(), true);
            this.fileChanged = true;
            return;
        }
        
        // Terrain Select
        if(this.brushType == BrushType.TERRAIN_SELECT)
        {
            this.brushTerrainSelectActive = true;
            this.brushTerrainSelectRef = this.board.getTileRef(tileX, tileY);
            return;
        }
    }
    
    private void inputMouseDetail(MouseEvent e)
    {
        if(this.brushType == BrushType.TERRAIN_PAINT) {this.inputMouseDetailTerrain(e);}
    }
    
    private void inputMouseDetailTerrain(MouseEvent e)
    {
        // Tileset Dialog
        if(uiDetailTerrainTile.contains(e.getPoint()))
        {
            this.uiDialog = new DialogTileset(this, this.brushTerrainPaintSheetF);
            return;
        }
        
        // Load Tileset
        /*if(uiDetailTerrainLoad.contains(e.getPoint()))
        {
            this.uiDialog = new DialogTilesheet(this, this.brushTerrainPaintSheetF);
            return;
        }*/
    }
    
    private void inputMouseTitlebar(MouseEvent e)
    {
        //
    }
    
    private void inputMouseToolbar(MouseEvent e)
    {
        // Get toolbar item
        String ref = this.uiToolbarObject.getItemAt(e.getPoint());
        
        // File: New
        //if(ref.equals("TOOLBAR_FILE_NEW"))
        
        // File: Open
        //if(ref.equals("TOOLBAR_FILE_OPEN"))
        
        // File: Save
        if(ref.equals("TOOLBAR_FILE_SAVE"))
        {
            this.board.save();
            this.fileChanged = false;
            this.setStatusMessage("BOARD SAVED");
        }
        
        // File: Exit
        if(ref.equals("TOOLBAR_FILE_EXIT")) {System.exit(0);}
        
        // View: Grid
        if(ref.equals("TOOLBAR_VIEW_GRID"))
        {
            this.optionGrid = BooleanTool.flip(this.optionGrid);
            // note: may want to show enable/disable status on button
            return;
        }
    }
    
    @Override
    public void interactHint()
    {
        //
    }
    
    @Override
    public void interactHint(String hint)
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        // Title
        this.renderTitle(g);
        
        // Board
        this.renderBoard(g);
        
        // Toolbar
        this.uiToolbarObject.render(g);
        
        // Detail
        this.renderDetail(g);
        
        // Status
        this.renderStatus(g);
        
        // Dialog
        if(this.uiDialog != null)
        {
            GFX.drawRect(g, Application.getAppAreaDraw(), "BLACK", 0.5f);
            if(this.uiDialog != null) {this.uiDialog.render(g);}
        }
    }
    
    private void renderBoard(Graphics g)
    {
        // Board
        this.board.render(g);
        
        // Grid
        if(this.optionGrid) {this.renderBoardGrid(g);}
        
        // Terrain Selection
        
        // Harvest
        //
        
        // Zone
        //
        
        // Border
        GFX.drawRect(g, this.uiBoardArea, "BLACK", false);
    }
    
    private void renderBoardGrid(Graphics g)
    {
        for(int x = 0; x < 34; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                GFX.drawRect(g, new Rectangle(this.uiBoardArea.x + (x * 32), this.uiBoardArea.y + (y * 32), 32, 32), "BLACK", false);
            }
        }
    }
    
    private void renderDetail(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.uiDetailArea, "WHITE", true);
        
        // Border
        GFX.drawRect(g, this.uiDetailArea, "BLACK", false);
        
        // Title
        String detailTitle = "";
        
        // Brush Detail
        if(this.brushType == BrushType.TERRAIN_PAINT)
        {
            detailTitle = "TERRAIN OPTIONS";
            this.renderDetailTerrain(g);
        }
        
        // Title
        GFX.write(g, detailTitle, this.uiDetailArea.x + 30, this.uiDetailArea.y + 20, "LEFT", "TOOLBAR_ITEM", "BLACK");
    }
    
    private void renderDetailTerrain(Graphics g)
    {
        // Tile Image
        GFX.drawImage(g, this.brushTerrainPaintSheetF, this.uiDetailTerrainTile.x, uiDetailTerrainTile.y, this.brushTerrainPaintSheetX, this.brushTerrainPaintSheetY, 32, 32);
        GFX.drawRect(g, uiDetailTerrainTile, "BLACK", false);
        
        // Tile Name
        GFX.write(g, this.getBrushTerrainPaint().getName(), (int) this.uiDetailArea.getCenterX(), this.uiDetailArea.y + 100, "CENTER", "TOOLBAR_ITEM", "BLACK");
    }
    
    private void renderStatus(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.uiStatusArea, "WHITE", true);
        
        // Border
        GFX.drawRect(g, this.uiStatusArea, "BLACK", false);
        
        // Message
        if(this.uiStatusMessageTick > 0) {GFX.write(g, this.uiStatusMessageText, this.uiStatusArea.x + 30, this.uiStatusArea.y + 20, "LEFT", "TOOLBAR_ITEM", "BLACK");}
        
        // Cursor
        GFX.write(g, getCursorStatus(Application.getMousePoint()), this.uiStatusArea.x + this.uiStatusArea.width - 30, this.uiStatusArea.y + 20, "RIGHT", "TOOLBAR_ITEM", "BLACK");
    }
    
    private void renderTitle(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.uiTitleArea, "TITLEBAR_BKG", true);
        
        // Border
        GFX.drawRect(g, this.uiTitleArea, "BLACK", false);
        
        // Hint
        GFX.write(g, "BOARD EDITOR", this.uiTitleArea.x + 20, this.uiTitleArea.y + 24, "LEFT", "TITLEBAR_HEADER", "BLACK");
    }
    
    public void setBrushTerrainPaint(String sheet, int tileX, int tileY)
    {
        this.brushTerrainPaintSheetF = sheet;
        this.brushTerrainPaintSheetX = tileX;
        this.brushTerrainPaintSheetY = tileY;
    }
    
    public void setDialogDone()
    {
        this.uiDialog = null;
    }
    
    private void setStatusMessage(String message)
    {
        this.uiStatusMessageText = message;
        this.uiStatusMessageTick = 180;
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
        this.tickStatus();
        if(this.uiDialog != null) {this.uiDialog.tick();}
    }
    
    private void tickStatus()
    {
        if(this.uiStatusMessageTick > 0)
        {
            this.uiStatusMessageTick -= 1;
            if(this.uiStatusMessageTick < 1)
            {
                this.uiStatusMessageText = "";
            }
        }
    }

}