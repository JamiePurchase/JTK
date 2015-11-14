/*
 * JTK-SCRIPT
 * 13/11/2015
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package states;

import console.Output;
import dialog.DialogAbout;
import dialog.DialogAbstract;
import dialog.DialogChanged;
import dialog.DialogOpen;
import dialog.DialogSave;
import dialog.DialogType;
import engine.Application;
import files.FileSystem;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import toolbar.ToolbarAbstract;
import tools.ArrayListTool;
import tools.BooleanTool;

/**
 *
 * @author Jamie
 */
public class StateApp extends StateAbstract
{
    // Toolbar
    private ToolbarAbstract toolbar;
    
    // File
    private String filePath, fileName;
    private ArrayList<String> fileData;
    private boolean fileLoaded, fileChanged;
    
    // Cursor
    private int cursorX, cursorY, cursorTick, cursorX2, cursorY2;
    private boolean cursorMulti, cursorRender;
    
    // Status
    private String statusHint;
    private int statusTick;
    
    // Dialog
    private DialogType dialogActive, dialogNext;
    private HashMap<DialogType, DialogAbstract> dialogMap;

    public StateApp()
    {
        super("APP");
        
        // Toolbar
        this.toolbar = new ToolbarAbstract("TOOLBAR", new Rectangle(Application.getAppAreaDraw().x, Application.getAppAreaDraw().y, Application.getAppAreaDraw().width, 30), "TOOLBAR_BKG", "TOOLBAR_BORDER", "BLACK", "FILE_DATA", "TOOLBAR_ITEM_HIGHLIGHT");
        this.toolbar.addItem("TOOLBAR_NEW", "NEW");
        this.toolbar.addItem("TOOLBAR_OPEN", "OPEN");
        this.toolbar.addItem("TOOLBAR_SAVE", "SAVE");
        this.toolbar.addItem("TOOLBAR_EXIT", "EXIT");
        
        // File
        this.filePath = "";
        this.fileName = "untitled";
        this.fileData = new ArrayList();
        this.fileLoaded = false;
        this.fileChanged = false;
        
        // Cursor
        this.cursorX = 0;
        this.cursorY = 0;
        this.cursorTick = 0;
        this.cursorX2 = 0;
        this.cursorY2 = 0;
        this.cursorMulti = false;
        this.cursorRender = true;
        
        // Status
        this.statusHint = "";
        this.statusTick = 0;
        
        // Dialog
        this.dialogActive = DialogType.NULL;
        this.dialogNext = DialogType.NULL;
        this.dialogMap = new HashMap();
        this.dialogMap.put(DialogType.ABOUT, new DialogAbout());
        this.dialogMap.put(DialogType.CHANGED, new DialogChanged());
        this.dialogMap.put(DialogType.OPEN, new DialogOpen());
        this.dialogMap.put(DialogType.SAVE, new DialogSave());
        
        // Title
        this.title();
        
        // Temp
        this.fileData.add("1 hello");
        this.fileData.add("2");
    }
    
    private void actionNew()
    {
        if(this.fileChanged)
        {
            this.dialogActive = DialogType.SAVE;
            this.dialogNext = DialogType.NEW;
        }
        else
        {
            this.fileNew();
        }
    }
    
    private void actionOpen()
    {
        /*if(this.fileChanged)
        {
            this.dialogActive = DialogType.SAVE;
            this.dialogNext = DialogType.OPEN;
        }
        else
        {
            this.dialogActive = DialogType.OPEN;
            this.dialogNext = DialogType.NULL;
        }*/
        
        // Temp
        this.fileOpen("C:/Users/Jamie/Documents/NetBeansProjects/JTK/Games/Venture/save/temp.jtk");
        this.setStatusHint("Opened a new file");
    }
    
    private void actionSave()
    {
        if(!this.fileLoaded)
        {
            this.dialogActive = DialogType.SAVE;
            this.dialogNext = DialogType.NULL;
        }
        else
        {
            this.fileSave();
        }
    }
    
    private void fileNew()
    {
        // File
        this.filePath = "";
        this.fileName = "untitled";
        this.fileData = new ArrayList();
        this.fileData.add("");
        this.fileLoaded = false;
        this.fileChanged = false;
        
        // Cursor
        this.cursorX = 0;
        this.cursorY = 0;
        this.cursorX2 = 0;
        this.cursorY2 = 0;
        this.cursorMulti = false;
        
        // Dialog
        this.dialogActive = DialogType.NULL;
        this.dialogNext = DialogType.NULL;
        
        // Title
        this.title();
    }
    
    private void fileOpen(String path)
    {
        // File
        this.filePath = path;
        this.fileName = FileSystem.getFileName(path);
        this.fileData = FileSystem.loadFile(path);
        this.fileLoaded = true;
        this.fileChanged = false;
        
        // Cursor
        this.cursorX = 0;
        this.cursorY = 0;
        this.cursorX2 = 0;
        this.cursorY2 = 0;
        this.cursorMulti = false;
        
        // Dialog
        this.dialogActive = DialogType.NULL;
        this.dialogNext = DialogType.NULL;
        
        // Title
        this.title();
    }
    
    private void fileSave()
    {
        // Save
        FileSystem.saveFile(this.fileData, this.filePath);
        
        // File
        this.fileChanged = false;
        this.fileLoaded = true;
        
        // Title
        this.title();
    }
    
    private Rectangle getEditorArea()
    {
        return new Rectangle(Application.getAppAreaDraw().x, Application.getAppAreaDraw().y + 30, Application.getAppAreaDraw().width - 30, Application.getAppAreaDraw().height - 60);
    }
    
    private String getLine()
    {
        return this.fileData.get(this.cursorY);
    }
    
    private Rectangle getScrollArea()
    {
        return new Rectangle(Application.getAppAreaDraw().x + Application.getAppAreaDraw().width - 30, Application.getAppAreaDraw().y + 30, 30, Application.getAppAreaDraw().height - 60);
    }
    
    private Rectangle getScrollButtonDown()
    {
        return new Rectangle(getScrollArea().x, getScrollArea().y + getScrollArea().height - 30, 30, 30);
    }
    
    private Rectangle getScrollButtonUp()
    {
        return new Rectangle(getScrollArea().x, getScrollArea().y, 30, 30);
    }
    
    private Rectangle getStatusArea()
    {
        return new Rectangle(Application.getAppAreaDraw().x, Application.getAppAreaDraw().y + Application.getAppAreaDraw().height - 30, Application.getAppAreaDraw().width, 30);
    }
    
    private Rectangle getToolArea()
    {
        return new Rectangle(Application.getAppAreaDraw().x, Application.getAppAreaDraw().y, Application.getAppAreaDraw().width, 30);
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        // Dialog Active?
        if(this.dialogActive != DialogType.NULL)
        {
            this.inputKeyDialog(e);
            return;
        }
        
        // Debug
        Output.print("StateApp -> inputKey(" + e.getExtendedKeyCode() + ")");
        Output.print("  inputHold");
        Output.print(Application.getKeyboard().holdListContains("KEY|17"));
        
        // Control
        if(Application.getKeyboard().holdListContains("KEY|17"))
        {
            this.inputKeyControl(e);
            return;
        }
        
        // Shift
        else if(Application.getKeyboard().holdListContains("KEY|16"))
        {
            this.inputKeyShift(e);
            return;
        }
        
        // Other
        else
        {
            this.inputKeyEditor(e);
        }
    }
    
    private void inputKeyControl(KeyEvent e)
    {
        // New
        if(e.getKeyCode() == KeyEvent.VK_N)
        {
            this.actionNew();
            return;
        }
        
        // Open
        if(e.getKeyCode() == KeyEvent.VK_O)
        {
            this.actionOpen();
            return;
        }
        
        // Save
        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            this.actionSave();
            return;
        }
    }
    
    private void inputKeyDialog(KeyEvent e)
    {
        //
    }
    
    private void inputKeyEditor(KeyEvent e)
    {
        // Enter
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String newLine = this.getLine().substring(this.cursorX);
            this.fileData.set(this.cursorY, this.getLine().substring(0, this.cursorX));
            this.fileData = ArrayListTool.insert(this.fileData, newLine, this.cursorY + 1);
            this.cursorX = 0;
            this.cursorY += 1;
            return;
        }
        
        // Backspace
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
            String newLine;
            int newCursorX;
            if(this.cursorX > 0)
            {
                newLine = this.getLine().substring(0, this.cursorX - 1) + this.getLine().substring(this.cursorX);
                this.fileData.set(this.cursorY, newLine);
                this.cursorX -= 1;
            }
            else if(this.cursorY > 0)
            {
                newCursorX = this.fileData.get(this.cursorY - 1).length();
                newLine = this.fileData.get(this.cursorY - 1) + this.getLine();
                this.fileData.set(this.cursorY - 1, newLine);
                this.fileData.remove(this.cursorY);
                this.cursorY -= 1;
                this.cursorX = newCursorX;
            }
            return;
        }
        
        // Delete
        if(e.getKeyCode() == KeyEvent.VK_DELETE)
        {
            String newLine;
            int newCursorX;
            if(this.cursorX < this.getLine().length())
            {
                newLine = this.getLine().substring(0, this.cursorX) + this.getLine().substring(this.cursorX + 1);
                this.fileData.set(this.cursorY, newLine);
            }
            else if(this.cursorY < this.fileData.size() - 1)
            {
                newCursorX = this.getLine().length();
                newLine = this.getLine() + this.fileData.get(this.cursorY + 1);
                this.fileData.set(this.cursorY, newLine);
                this.fileData.remove(this.cursorY + 1);
                this.cursorX = newCursorX;
            }
            return;
        }
        
        // Arrow: Left
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(this.cursorX > 0)
            {
                this.cursorX -= 1;
            }
            else if(this.cursorY > 0)
            {
                this.cursorY -= 1;
                this.cursorX = this.getLine().length();
            }
            return;
        }
        
        // Arrow: Right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(this.cursorX < this.getLine().length())
            {
                this.cursorX += 1;
            }
            else if(this.cursorY < this.fileData.size() - 1)
            {
                this.cursorY += 1;
                this.cursorX = 0;
            }
            return;
        }
        
        // Arrow: Up
        if(e.getKeyCode() == KeyEvent.VK_UP && this.cursorY > 0)
        {
            this.cursorY -= 1;
            if(this.cursorX > this.getLine().length()) {this.cursorX = this.getLine().length();}
            return;
        }
        
        // Arrow: Down
        if(e.getKeyCode() == KeyEvent.VK_DOWN && this.cursorY < this.fileData.size() - 1)
        {
            this.cursorY += 1;
            if(this.cursorX > this.getLine().length()) {this.cursorX = this.getLine().length();}
            return;
        }
        
        // Home
        if(e.getKeyCode() == KeyEvent.VK_HOME)
        {
            this.cursorX = 0;
            return;
        }
        
        // End
        if(e.getKeyCode() == KeyEvent.VK_END)
        {
            this.cursorX = this.getLine().length();
            return;
        }

        // Alphanumeric or Symbol
        if(Application.getKeyboard().isAlphanumeric(e) || Application.getKeyboard().isSymbol(e))
        {
            this.inputKeyType(e.getKeyChar());
        }
    }
    
    private void inputKeyShift(KeyEvent e)
    {
        // Debug
        Output.print("StateApp -> inputKeyShift");
        
        // Arrow: Right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(!this.cursorMulti) {this.setMulti(true);}
            if(this.cursorX < this.getLine().length())
            {
                this.cursorX += 1;
            }
            else if(this.cursorY < this.fileData.size() - 1)
            {
                this.cursorY += 1;
                this.cursorX = 0;
            }
            return;
        }
        
        // End
        if(e.getKeyCode() == KeyEvent.VK_END)
        {
            if(!this.cursorMulti) {this.setMulti(true);}
            this.cursorX = this.getLine().length();
            return;
        }
    }
    
    private void inputKeyType(char type)
    {
        String newLine = this.getLine().substring(0, this.cursorX) + type + this.getLine().substring(this.cursorX);
        this.fileData.set(this.cursorY, newLine);
        this.cursorX += 1;
        this.fileChanged = true;
        this.title();
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        // Editor
        this.renderEditor(g);
        this.renderScroll(g);
        //this.renderTool(g);
        this.toolbar.render(g);
        this.renderStatus(g);
        
        // Dialog
        if(this.dialogActive != DialogType.NULL) {this.dialogMap.get(this.dialogActive).render(g);}
    }
    
    private void renderEditor(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.getEditorArea(), "WHITE", true);
        
        // Multi
        //if(this.cursorMulti) {this.renderEditorMulti(g);}
        
        // Contents
        String renderColour, renderFont;
        for(int x = 0; x < this.fileData.size(); x++)
        {
            renderColour = "FILE_DATA";
            renderFont = "FILE_DATA";
            if(this.fileData.get(x).substring(0, 1).equals(">"))
            {
                renderColour = "FILE_DATA_COMMENT";
                renderFont = "FILE_DATA_COMMENT";
            }
            GFX.write(g, this.fileData.get(x), this.getEditorArea().x + 10, this.getEditorArea().y + 20 + (x * 20), "LEFT", renderFont, renderColour);
        }
        
        // Cursor
        if(this.cursorRender) {this.renderEditorCursor(g);}
    }
    
    private void renderEditorCursor(Graphics g)
    {
        // Initial location
        int renderX = this.getEditorArea().x + 3 + GFX.getTextWidth(g, this.getLine().substring(0, this.cursorX), "FILE_DATA");
        int renderY = this.getEditorArea().y + 20 + (this.cursorY * 20);
        GFX.write(g, "|", renderX, renderY, "LEFT", "FILE_DATA", "FILE_DATA");
    }
    
    private void renderEditorMulti(Graphics g)
    {
        // Start and End
        int multiPosX1 = this.cursorX;
        int multiPosY1 = this.cursorY;
        int multiPosX2 = this.cursorX2;
        int multiPosY2 = this.cursorY2;
        if(this.cursorY > this.cursorY2)
        {
            multiPosX1 = this.cursorX2;
            multiPosY1 = this.cursorY2;
            multiPosX2 = this.cursorX;
            multiPosY2 = this.cursorY;
        }
        
        // Line Count
        int multiLine = (multiPosY2 - multiPosY1) + 1;
        
        // Highlight Multiple Lines
        /*
        if(multiLine > 1)
        {
            for(int line = 0; line < multiLine; line++)
            {
                // First Line
                if(line == 0)
                {
                    renderEditorMultiHighlight(g, multiPosX1, this.fileData.get(multiPosY1 + line).length(), multiPosY1 + line);
                }
                
                // Last Line
                else if(line == (multiLine - 1))
                {
                    renderEditorMultiHighlight(g, 0, multiPosX2, multiPosY1 + line);
                }

                // Other Line
                else
                {
                    renderEditorMultiHighlight(g, 0, this.fileData.get(multiPosY1 + line).length(), multiPosY1 + line);
                }
            }
        }
        */
        
        // Highlight Single Line
        //else {renderEditorMultiHighlight(g, multiPosX1, multiPosX2, multiPosY1);}
        renderEditorMultiHighlight(g, multiPosX1, multiPosX2, multiPosY1);
    }
    
    private void renderEditorMultiHighlight(Graphics g, int x1, int x2, int y)
    {
        // Highlight location
        int multiRenderX = this.getEditorArea().x + 3;
        if(x1 > 0) {multiRenderX += GFX.getTextWidth(g, this.fileData.get(y).substring(0, x1), "FILE_DATA");}
        int multiRenderY = this.getEditorArea().y + 20 + (y * 20);
        int multiRenderW = GFX.getTextWidth(g, this.fileData.get(y).substring(x1, x2 - 1), "FILE_DATA");
        
        // Render highlight
        GFX.drawRect(g, new Rectangle(multiRenderX, multiRenderY, multiRenderW, 30), "GREEN", true);
    }
    
    private void renderScroll(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.getScrollArea(), "TOOLBAR_BKG", true);
        
        // Button: Up
        if(this.getScrollButtonUp().contains(Application.getMousePoint())) {GFX.drawRect(g, this.getScrollButtonUp(), "TOOLBAR_SCROLL_BUTTON_HIGHLIGHT", true);}
        else {GFX.drawRect(g, this.getScrollButtonUp(), "TOOLBAR_SCROLL_BUTTON", true);}
        GFX.drawRectBorder(g, this.getScrollButtonUp(), "TOOLBAR_BORDER", false, false, true, false);
        //GFX.write(g, "^", (int) this.getScrollButtonUp().getCenterX(), this.getScrollButtonUp().y + 10, "CENTER", "FILE_DATA", "FILE_DATA");
        GFX.drawImage(g, "resources/gfx/scrollUp.png", this.getScrollButtonUp().x, this.getScrollButtonUp().y);
        
        // Button: Down
        if(this.getScrollButtonDown().contains(Application.getMousePoint())) {GFX.drawRect(g, this.getScrollButtonDown(), "TOOLBAR_SCROLL_BUTTON_HIGHLIGHT", true);}
        else {GFX.drawRect(g, this.getScrollButtonDown(), "TOOLBAR_SCROLL_BUTTON", true);}
        GFX.drawRectBorder(g, this.getScrollButtonDown(), "TOOLBAR_BORDER", true, false, false, false);
        //GFX.write(g, "v", (int) this.getScrollButtonDown().getCenterX(), this.getScrollButtonDown().y + 10, "CENTER", "FILE_DATA", "FILE_DATA");
        GFX.drawImage(g, "resources/gfx/scrollDown.png", this.getScrollButtonDown().x, this.getScrollButtonDown().y);
        
        // Border
        GFX.drawRectBorder(g, this.getScrollArea(), "TOOLBAR_BORDER", false, false, false, true);
    }
    
    private void renderStatus(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.getStatusArea(), "TOOLBAR_BKG", true);
        
        // Application Info
        GFX.write(g, this.statusHint, this.getStatusArea().x + 15, this.getStatusArea().y + 20, "LEFT", "FILE_DATA", "FILE_DATA");
        
        // Cursor Position
        GFX.write(g, "LINE " + this.cursorY, this.getStatusArea().x + this.getStatusArea().width - 300, this.getStatusArea().y + 20, "LEFT", "FILE_DATA", "FILE_DATA");
        GFX.write(g, "COLUMN " + this.cursorX, this.getStatusArea().x + this.getStatusArea().width - 200, this.getStatusArea().y + 20, "LEFT", "FILE_DATA", "FILE_DATA");
        
        // Border
        GFX.drawRectBorder(g, this.getStatusArea(), "TOOLBAR_BORDER", true, false, false, false);
    }
    
    private void renderTool(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.getToolArea(), "TOOLBAR_BKG", true);
        
        // Border
        GFX.drawRectBorder(g, this.getToolArea(), "TOOLBAR_BORDER", false, false, true, false);
    }
    
    private void setMulti(boolean multi)
    {
        if(multi)
        {
            this.cursorMulti = true;
            this.cursorX2 = this.cursorX;
            this.cursorY2 = this.cursorY;
        }
        else
        {
            this.cursorMulti = false;
            this.cursorX2 = 0;
            this.cursorY2 = 0;
        }
    }
    
    private void setStatusHint(String hint)
    {
        this.statusHint = hint;
        this.statusTick = 180;
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
        this.tickCursor();
        if(this.statusTick > 0) {this.tickStatus();}
    }
    
    private void tickCursor()
    {
        this.cursorTick += 1;
        if(this.cursorTick >= 24)
        {
            this.cursorTick = 0;
            this.cursorRender = BooleanTool.flip(this.cursorRender);
        }
    }
    
    private void tickStatus()
    {
        this.statusTick -= 1;
        if(this.statusTick < 1) {this.statusHint = "";}
    }
    
    private void title()
    {
        String title = "JTK SCRIPT - " + this.fileName;
        if(this.fileChanged) {title += "*";}
        Application.getTitlebar().setTitle(title);
    }
    
}