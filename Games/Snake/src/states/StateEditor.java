/*
 * Snake
 * Jamie Purchase
 */
package states;

import app.Engine;
import editor.input.Input;
import editor.input.InputButton;
import editor.input.InputText;
import editor.tool.Tool;
import editor.tool.ToolTerrain;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import styles.Style;
import world.Location;
import world.Solidity;
import world.World;
import world.WorldTerrain;

/**
 *
 * @author Jamie
 */
public class StateEditor extends State
{
    // File
    private Rectangle fileRect;
    private boolean fileBrowseActive, fileNewActive, fileOpen;
    private String fileName;
    private ArrayList<Input> fileNewInput;
    
    // Tools
    private final Rectangle toolBarZone = new Rectangle(0, 0, 1366, 43);
    private ArrayList<Input> toolBarOption;
    private final Rectangle toolPanelZone = new Rectangle(1225, 43, 141, 725);
    private HashMap<Tool, ArrayList<Input>> toolPanelOption;
    private ArrayList<Input> toolPanelOptionTerrain;
    private Tool toolActive;
    private ToolTerrain toolTerrainActive;
    private String toolTerrainSheet;
    private int toolTerrainTileX, toolTerrainTileY;
    
    // View Options
    private boolean viewGrid;
    
    // Board Settings
    private final Rectangle boardZone = new Rectangle(0, 43, 1200, 700);
    private int boardScrollX, boardScrollY;
    private final Rectangle boardScrollXZone = new Rectangle(0, 743, 1200, 25);
    private final Rectangle boardScrollYZone = new Rectangle(1200, 43, 25, 700);
    private final Rectangle boardScrollNZone = new Rectangle(1200, 743, 25, 25);
    private World boardWorld;
    
    public StateEditor()
    {
        // File
        this.fileRect = new Rectangle(383, 184, 600, 400);
        this.fileBrowseActive = false;
        this.fileNewActive = true;
        this.fileOpen = false;
        this.fileName = "";
        this.fileNewInput = new ArrayList();
        this.fileNewInput.add(new InputText("NAME", this.fileRect, 50, 10, 80, 30, "", 20));
        
        // Tools
        this.toolBarOption = new ArrayList();
        this.toolBarOption.add(new InputButton("FILE", this.toolBarZone, 5, 7, 80, 30, "NEW"));
        this.toolBarOption.add(new InputButton("OPEN", this.toolBarZone, 90, 7, 80, 30, "OPEN"));
        this.toolBarOption.add(new InputButton("SAVE", this.toolBarZone, 175, 7, 80, 30, "SAVE"));
        this.toolBarOption.add(new InputButton("EXIT", this.toolBarZone, 260, 7, 80, 30, "EXIT"));
        this.toolPanelOption = new HashMap();
        this.toolPanelOptionTerrain = new ArrayList();
        this.toolPanelOptionTerrain.add(new InputButton("TERRAIN_PAINT", this.toolPanelZone, 7, 7, "iconTerrain"));
        this.toolPanelOption.put(Tool.TERRAIN, this.toolPanelOptionTerrain);
        this.toolActive = Tool.TERRAIN;
        this.toolTerrainActive = ToolTerrain.PAINT;
        this.toolTerrainSheet = "grass1";
        this.toolTerrainTileX = 1;
        this.toolTerrainTileY = 0;
        
        // View Options
        this.viewGrid = false;
        
        // Board Settings
        this.boardScrollX = 0;
        this.boardScrollY = 0;
        this.boardWorld = new World("TEST1", 100, 100, new WorldTerrain("grass1", 0, 0), Solidity.STANDARD);
    }
    
    private WorldTerrain boardGetTerrainPaint()
    {
        return new WorldTerrain(this.toolTerrainSheet, this.toolTerrainTileX, this.toolTerrainTileY);
    }
    
    private void clickBoardScrollX(MouseEvent e)
    {
        //
    }
    
    private void clickBoardScrollY(MouseEvent e)
    {
        //
    }
    
    private void clickBoardWorld(MouseEvent e)
    {
        if(this.toolActive == Tool.TERRAIN)
        {
            if(this.toolTerrainActive == ToolTerrain.PAINT)
            {
                this.boardWorld.setTileTerrain(this.inputMouseBoard(), this.boardGetTerrainPaint());
            }
        }
    }
    
    private void clickToolBar(MouseEvent e)
    {
        String key = inputMouseOption(e, this.toolBarOption);
        if(key.equals("SAVE")) {this.boardWorld.fileSave();}
        if(key.equals("EXIT")) {System.exit(0);}
    }
    
    private void clickToolPanel(MouseEvent e)
    {
        String key = inputMouseOption(e, this.toolPanelOption.get(this.toolActive));
        if(key.equals("TERRAIN_PAINT"))
        {
            this.toolTerrainActive = ToolTerrain.PAINT;
            return;
        }
    }

    public void inputKeyPress(String key)
    {
        //
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        //
    }
    
    private Location inputMouseBoard()
    {
        int cursorX = Engine.getMousePoint().x - this.boardZone.x;
        int cursorY = Engine.getMousePoint().y - this.boardZone.y;
        int boardX = 0;
        int boardY = 0;
        while(cursorX > 50)
        {
            cursorX -= 50;
            boardX += 1;
        }
        while(cursorY > 50)
        {
            cursorY -= 50;
            boardY += 1;
        }
        return new Location(boardX + this.boardScrollX, boardY + this.boardScrollY);
    }

    public void inputMouseClickL(MouseEvent e)
    {
        if(this.boardZone.contains(e.getPoint())) {this.clickBoardWorld(e);}
        if(this.boardScrollXZone.contains(e.getPoint())) {this.clickBoardScrollX(e);}
        if(this.boardScrollYZone.contains(e.getPoint())) {this.clickBoardScrollY(e);}
        if(this.toolBarZone.contains(e.getPoint())) {this.clickToolBar(e);}
        if(this.toolPanelZone.contains(e.getPoint())) {this.clickToolPanel(e);}
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    private String inputMouseOption(MouseEvent e, ArrayList<Input> option)
    {
        for(int x = 0; x < option.size(); x++)
        {
            if(option.get(x).zone.contains(e.getPoint())) {return option.get(x).getKey();}
        }
        return "";
    }

    public void render(Graphics g)
    {
        this.renderToolBar(g);
        this.renderToolPanel(g);
        if(this.fileBrowseActive) {this.renderBrowse(g);}
        else if(this.fileNewActive) {this.renderNew(g);}
        else {this.renderBoard(g);}
    }
    
    private void renderBoard(Graphics g)
    {
        this.renderBoardWorld(g);
        this.renderBoardScrollN(g);
        this.renderBoardScrollX(g);
        this.renderBoardScrollY(g);
    }
    
    private void renderBoardScrollN(Graphics g)
    {
        Drawing.fillRect(g, this.boardScrollNZone, Style.colour("TOOL_BAR_FILL"));
        Drawing.drawRect(g, this.boardScrollNZone, Color.BLACK);
    }
    
    private void renderBoardScrollX(Graphics g)
    {
        Drawing.fillRect(g, this.boardScrollXZone, Style.colour("TOOL_BAR_FILL"));
        Drawing.drawRect(g, this.boardScrollXZone, Color.BLACK);
    }
    
    private void renderBoardScrollY(Graphics g)
    {
        Drawing.fillRect(g, this.boardScrollYZone, Style.colour("TOOL_BAR_FILL"));
        Drawing.drawRect(g, this.boardScrollYZone, Color.BLACK);
    }
    
    private void renderBoardWorld(Graphics g)
    {
        // Terrain
        Drawing.drawImage(g, this.boardWorld.getTerrainArea(g, this.boardZone, this.boardScrollX, this.boardScrollY), this.boardZone.x, this.boardZone.y);
        
        // Border
        Drawing.drawRect(g, this.boardZone, Color.BLACK);
    }
    
    private void renderBrowse(Graphics g)
    {
        // Frame
        this.renderDialog(g);
        
        // Files
        //
    }
    
    private void renderDialog(Graphics g)
    {
        // Frame
        Drawing.fillRect(g, this.fileRect, Style.colour("TOOL_BAR_FILL"));
        Drawing.drawRect(g, this.fileRect, Color.BLACK);
    }
    
    private void renderNew(Graphics g)
    {
        // Frame
        this.renderDialog(g);
        
        // Inputs
        for(int x = 0; x < this.fileNewInput.size(); x++)
        {
            this.fileNewInput.get(x).render(g);
        }
    }
    
    private void renderToolBar(Graphics g)
    {
        Drawing.fillRect(g, this.toolBarZone, Style.colour("TOOL_BAR_FILL"));
        for(int x = 0; x < this.toolBarOption.size(); x++) {this.toolBarOption.get(x).render(g);}
        Drawing.drawRect(g, this.toolBarZone, Color.BLACK);
    }
    
    private void renderToolPanel(Graphics g)
    {        
        this.renderToolPanelZone(g);
        this.renderToolPanelOptions(g);
        this.renderToolPanelCoords(g);
    }
    
    private void renderToolPanelCoords(Graphics g)
    {
        String panelCoords = "x---y---";
        if(this.boardZone.contains(Engine.getMousePoint())) {panelCoords = this.inputMouseBoard().asKey();}
        Text.write(g, "COORDINATES", (int) this.toolPanelZone.getCenterX(), this.toolPanelZone.y + this.toolPanelZone.height - 36, "CENTER", Style.font("TOOL_MINI"), Color.BLACK);
        Text.write(g, panelCoords, (int) this.toolPanelZone.getCenterX(), this.toolPanelZone.y + this.toolPanelZone.height - 10, "CENTER", Style.font("TOOL_MINI"), Color.BLACK);
    }
    
    private void renderToolPanelOptions(Graphics g)
    {
        ArrayList<Input> panel = this.toolPanelOption.get(this.toolActive);
        for(int x = 0; x < panel.size(); x++) {panel.get(x).render(g);}
    }
    
    private void renderToolPanelZone(Graphics g)
    {
        Drawing.fillRect(g, this.toolPanelZone, Style.colour("TOOL_BAR_FILL"));
        Drawing.drawRect(g, this.toolPanelZone, Color.BLACK);
    }

    public void tick()
    {
        if(this.fileNewActive) {this.tickNew();}
    }
    
    private void tickNew()
    {
        //
    }

}