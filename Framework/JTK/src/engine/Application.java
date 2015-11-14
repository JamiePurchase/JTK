/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package engine;

import console.Output;
import engine.display.Display;
import engine.display.DisplayTitlebar;
import engine.display.DisplayTitlebarAbstract;
import gfx.GFX;
import gfx.StyleColour;
import gfx.StyleNative;
import gfx.StyleText;
import input.InputHold;
import input.InputKeyboard;
import input.InputMouse;
import states.StateAbstract;
import states.StateIdle;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author Jamie
 */
public class Application extends JPanel implements Runnable
{
    // Program
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean running = false;
    private static BufferStrategy bs;
    private static Graphics g;
    private final static InputKeyboard keyboard = new InputKeyboard();
    private final static InputMouse mouse = new InputMouse();
    
    // Application
    private static String appTitle;
    private static Rectangle appArea;
    private static Display display;
    public static int appWidth, appHeight;
    private static boolean appFullScreen;
    private static String appVersion, appIcon;
    private static Point appMousePoint;
    
    // State
    private static ArrayList<StateAbstract> stateList;
    private static int stateNow;
    
    // Titlebar
    private static DisplayTitlebarAbstract titleBar;
    
    // Properties
    private static HashMap<String, Object> properties;
    
    // Style
    private static HashMap<String, Color> styleColour;
    private static HashMap<String, Font> styleFont;
    
    // Application: Full Screen
    public Application(String title, String version, String icon)
    {
        // Init App
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        initApplication(title, dim.width, dim.height, true, version, icon);
    }

    // Application: Modal Window
    public Application(String title, int appW, int appH, String version, String icon)
    {
        // Init App
        initApplication(title, appW, appH, false, version, icon);
        
        // Titlebar
        titleBar = new DisplayTitlebar(new Rectangle(0, 0, appWidth, 30), appTitle, StyleColour.TEXT_STANDARD, StyleText.STANDARD, StyleColour.TITLE_BKG, StyleColour.WINDOW_BORDER);
    }
    
    public static Rectangle getAppArea()
    {
        return appArea;
    }
    
    public static Rectangle getAppAreaDraw()
    {
        if(appFullScreen) {return appArea;}
        else
        {
            return new Rectangle(1, 31, appWidth - 2, appHeight - 32);
        }
    }
    
    public static String getAppTitle()
    {
        return appTitle;
    }
    
    public static Canvas getCanvas()
    {
        return display.getCanvas();
    }
    
    public static Display getDisplay()
    {
        return display;
    }
    
    public static InputKeyboard getKeyboard()
    {
        return keyboard;
    }
    
    public static InputMouse getMouse()
    {
        return mouse;
    }
    
    public static Point getMousePoint()
    {
        return appMousePoint;
    }
    
    public static Object getProperty(String key)
    {
        return properties.get(key);
    }
    
    public static boolean getPropertyExists(String key)
    {
        return properties.containsKey(key);
    }
    
    public static int getScreenCenterX()
    {
        return appWidth / 2;
    }
    
    public static int getScreenCenterY()
    {
        return appHeight / 2;
    }
    
    public static StateAbstract getState()
    {
        return stateList.get(stateNow);
    }
    
    public static Color getStyleColour(String key)
    {
        return styleColour.get(key);
    }
    
    public static void getStyleColourList()
    {
        String[] keys = (String[]) styleColour.keySet().toArray();
        System.out.println("STYLE COLOUR LIST");
        for(int x = 0; x < styleColour.size(); x++)
        {
            System.out.println(" - " + keys[x] + " (" + styleColour.get(keys[x]).toString() + ")");
        }
    }
    
    public static Font getStyleFont(String key)
    {
        return styleFont.get(key);
    }
    
    public static String getSystemProperty(String property)
    {
        return System.getProperty(property);
    }
    
    public static String getSystemResource(String path)
    {
        try
        {
            path = ClassLoader.getSystemResource(path).getPath();
        }
        catch(Exception ex) {System.out.println(ex);}
        return path.substring(1, path.length()).replaceAll("%20", " ");
    }
    
    public static String getTimestamp()
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " @ " + new SimpleDateFormat("kk:mm:ss").format(new Date());
    }
    
    public static DisplayTitlebarAbstract getTitlebar()
    {
        return titleBar;
    }
    
    public static String getVersion()
    {
        return appVersion;
    }
    
    private void initApplication(String title, int appW, int appH, boolean fullScreen, String version, String icon)
    {
        // Application Details
        appTitle = title;
        appWidth = appW;
        appHeight = appH;
        appFullScreen = fullScreen;
        appArea = new Rectangle(0, 0, appWidth, appHeight);
        appVersion = version;
        appIcon = icon;
        appMousePoint = new Point(0, 0);
        
        // Output
        Output.logo();
        
        // State
        stateList = new ArrayList();
        stateList.add(new StateIdle());
        stateNow = 0;
        
        // Titlebar
        titleBar = null;
        
        // Properties
        properties = new HashMap();
        
        // Style
        styleColour = new HashMap();
        styleFont = new HashMap();
        StyleNative.load();
    }

    private void initDisplay()
    {
        display = new Display(appTitle, appWidth, appHeight, keyboard, mouse, appIcon);
    }
    
    public static void inputHold(KeyEvent e, InputHold hold)
    {
        // Ctrl
        if(hold == InputHold.CTRL)
        {
            if(e.getKeyCode() == KeyEvent.VK_X) {System.exit(0);}
        }
        
        // Shift
        if(hold == InputHold.SHIFT)
        {
            //if(e.getKeyCode() == KeyEvent.VK_2) {consoleLine += '"';}
            //if(e.getKeyCode() == KeyEvent.VK_SEMICOLON) {consoleLine += ":";}
        }
    }
    
    public static void inputKey(KeyEvent e)
    {
        getState().inputKey(e);
        
        /*
        
        // Enter
        if(e.getKeyCode() == KeyEvent.VK_ENTER && consoleLine.length() > 0)
        {
            inputKeyEnter();
            return;
        }
        
        // Escape
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {System.exit(0);}
        
        // Space
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            consoleLine += " ";
            return;
        }
        
        // Backspace
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && consoleLine.length() > 0)
        {
            consoleLine = consoleLine.substring(0, consoleLine.length() - 1);
            return;
        }
        
        // Arrow Up
        if(e.getKeyCode() == KeyEvent.VK_UP && consoleMemory.size() > 0)
        {
            if(consoleMemoryScroll)
            {
                if(consoleMemoryPos > 0)
                {
                    consoleMemoryPos -= 1;
                    consoleLine = consoleMemory.get(consoleMemoryPos);
                }
            }
            else
            {
                consoleMemoryScroll = true;
                consoleMemoryPos = consoleMemory.size() - 1;
                consoleLine = consoleMemory.get(consoleMemoryPos);
            }
            return;
        }
        
        // Arrow Down
        if(e.getKeyCode() == KeyEvent.VK_DOWN && consoleMemoryScroll)
        {
            if(consoleMemory.size() > consoleMemoryPos + 1)
            {
                consoleMemoryPos += 1;
                consoleLine = consoleMemory.get(consoleMemoryPos);
            }
            else
            {
                consoleMemoryScroll = false;
                consoleMemoryPos = 0;
                consoleLine = "";
            }
            return;
        }
        
        // Page Up
        if(e.getKeyCode() == KeyEvent.VK_PAGE_UP && consoleData.size() > consoleLines)
        {
            consoleScroll += consoleLines;
            if(consoleScroll > consoleData.size() - consoleLines) {consoleScroll = consoleData.size() - consoleLines;}
            return;
        }
        
        // Page Down
        if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN && consoleScroll > 0)
        {
            consoleScroll -= consoleLines;
            if(consoleScroll < 0) {consoleScroll = 0;}
            return;
        }
        
        // Alphanumeric
        if(Engine.getKeyboard().isAlphanumeric(e)) {consoleLine += e.getKeyChar();}
        
        // Symbol
        if(Engine.getKeyboard().isSymbol(e)) {consoleLine += e.getKeyChar();}
        
        */
    }
    
    private static void inputKeyEnter()
    {
        /*
        
        // Update Memory
        consoleMemory.add(consoleLine);
        
        // Parse Command
        ArrayList<String> commandArray = commandSplit(consoleLine);
        boolean isNamespace = false;
        
        // Search System
        Command command = commandFind(commandArray.get(0));
        
        // Search Namespaces
        if(command == null && namespaceArray.size() > 0)
        {
            Namespace namespace = commandNamespace(commandArray.get(0));
            if(namespace != null)
            {
                command = namespace.commandFind(commandArray.get(1));
                isNamespace = true;
            }
        }
        
        // Command Found
        if(command != null)
        {
            // Help Request
            boolean commandHelp = false;
            if(consoleLine.toUpperCase().indexOf(" HELP") >= 0)
            {
                command.outputHelp();
                return;
            }
            
            // New Line
            output(consoleLine);
            consoleLine = "";
            
            // Action
            commandAction(command, commandArray, isNamespace);
        }
        
        // Command Not Found
        else
        {
            output("No matching command found for " + consoleLine, OutputType.ERROR);
            consoleLine = "";
        }
        
        */
    }
    
    public static void inputMouse(MouseEvent e)
    {
        if(titleBar != null)
        {
            if(titleBar.clickClose(e.getPoint())) {System.exit(0);}
        }
        getState().inputMouse(e);
    }

    private static void render()
    {
        // Buffer Strategy
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        // Graphics Start
        g = bs.getDrawGraphics();
        
        // Graphics Render
        renderWindow();
        getState().render(g);
        
        // Graphics Done
        bs.show();
        g.dispose();
    }
    
    private static void renderWindow()
    {
        // Background
        GFX.drawRect(g, appArea, StyleColour.WINDOW_BKG, true);
        
        // Titlebar
        if(titleBar != null) {titleBar.render(g);}
        
        // Border
        if(!appFullScreen)
        {
            GFX.drawRect(g, appArea, StyleColour.WINDOW_BORDER, false);
            GFX.drawRect(g, new Rectangle(0, 0, appArea.width - 1, appArea.height - 1), StyleColour.WINDOW_BORDER, 1);
        }
    }
    
    private static StyleColour renderWindowStyle()
    {
        //if(consoleStyle == StyleConsole.ALTERNATIVE) {return StyleColour.TITLE_BKG2;}
        return StyleColour.TITLE_BKG;
    }

    public void run()
    {
        // Application Speed
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        // Create Window
        initDisplay();

        // Application Loop
        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1)
            {			
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000)
            {
                ticks = 0;
                timer = 0;
            }
        }

        // End Application
        stop();
    }
    
    public static void setMousePoint(Point point)
    {
        appMousePoint = point;
    }
    
    public static void setProperty(String key, Object value)
    {
        properties.put(key, value);
    }
    
    public static void setStyleColour(String key, Color colour)
    {
        styleColour.put(key, colour);
    }
    
    public static void setStyleColour(String key, int r, int g, int b)
    {
        styleColour.put(key, GFX.getColourRGB(r, g, b));
    }
    
    public static void setStyleFont(String key, Font font)
    {
        styleFont.put(key, font);
    }
    
    public static void setTitlebar(DisplayTitlebarAbstract newTitleBar)
    {
        titleBar = newTitleBar;
    }
    
    public static void stateAdd(StateAbstract newState, boolean change)
    {
        stateList.add(newState);
        if(change) {stateNow = stateList.size() - 1;}
    }
    
    public static void stateChange(int change)
    {
        if(change >= 0 && change < stateList.size())
        {
            getState().stateFinish();
            stateNow = change;
            getState().stateChange();
        }
        else {System.out.println("ERROR: cannot change to state " + change + " (there are only " + (stateList.size() - 1) + " states");}
    }
    
    public static void stateNew(StateAbstract newState)
    {
        getState().stateFinish();
        stateList = new ArrayList();
        stateList.add(newState);
        stateNow = 0;
        getState().stateChange();
    }
    
    public synchronized void start(boolean dev)
    {
        if(running == false)
        {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop()
    {
        if(running == true)
        {
            try {thread.join();}
            catch (InterruptedException e) {System.out.println(e);}
        }
    }

    private void tick()
    {
        getState().tick();
    }

}