/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package game;

import engine.Application;
import game.entity.EntityAbstract;
import game.entity.unit.Direction;
import game.entity.unit.UnitAbstract;
import game.entity.unit.UnitType;
import game.force.ForceNature;
import game.force.ForceRuler;
import game.force.ForceType;
import game.resource.ResourceType;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import ui.DialogPause;
import ui.InfoResource;
import ui.InfoSelect;
import ui.Minimap;
import ui.OptionButton;

/**
 *
 * @author Jamie
 */
public class GameData
{
    // World
    private final Rectangle worldArea = new Rectangle(0, 40, 1377, 728);
    private int worldScrollX, worldScrollY;
    
    // Pause
    private DialogPause pauseDialog = new DialogPause(this);
    private boolean pauseActive = false;
    
    // Info
    private final InfoSelect infoSelect = new InfoSelect(this);
    private final Minimap infoMinimap = new Minimap(this);
    
    // Force
    private ForceNature forceNature;
    private ArrayList<ForceRuler> forceList;
    
    // Data
    private final Rectangle dataArea = new Rectangle(0, 0, 1366, 40);
    private final InfoResource dataResourceWood, dataResourceFood, dataResourceGold, dataResourceStone;
    private final OptionButton dataOptionMenu = new OptionButton("DATA_MENU", "MENU", 1261, 5);
    
    // Select
    private ArrayList<EntityAbstract> selectEntity;
    
    public GameData()
    {        
        // World
        this.worldScrollX = 0;
        this.worldScrollY = 0;
        
        // Force: Nature
        this.forceNature = new ForceNature(this);
        this.forceNature.addNature("TREE", "Tree", 300, 250);
        this.forceNature.addNature("TREE", "Tree", 400, 200);
        this.forceNature.addNature("TREE", "Tree", 700, 250);
        this.forceNature.addNature("TREE", "Tree", 900, 550);
        
        // Force: Player
        this.forceList = new ArrayList();
        this.forceList.add(new ForceRuler(this, "JAMIE", ForceType.PLAYER));
        this.forceList.get(0).addUnit("PLAYER_U1", "Settler", 600, 300, Direction.SE, UnitType.SETTLER);
        this.forceList.get(0).addUnit("PLAYER_U2", "Settler", 800, 350, Direction.SW, UnitType.SETTLER);
        
        // Force: Computer
        this.forceList.add(new ForceRuler(this, "COMPUTER", ForceType.COMPUTER));
        
        // Data
        this.dataResourceWood = new InfoResource(this.forceList.get(0), new Rectangle(20, 5, 120, 30), ResourceType.WOOD, "resources/gfx/info/icon_resource_wood.png");
        this.dataResourceFood = new InfoResource(this.forceList.get(0), new Rectangle(160, 5, 120, 30), ResourceType.FOOD, "resources/gfx/info/icon_resource_food.png");
        this.dataResourceGold = new InfoResource(this.forceList.get(0), new Rectangle(300, 5, 120, 30), ResourceType.GOLD, "resources/gfx/info/icon_resource_gold.png");
        this.dataResourceStone = new InfoResource(this.forceList.get(0), new Rectangle(440, 5, 120, 30), ResourceType.STONE, "resources/gfx/info/icon_resource_stone.png");
        
        // Select
        this.selectEntity = new ArrayList();
    }
    
    private Point getBoardPoint(Point point)
    {
        return new Point(point.x + this.worldScrollX, point.y + this.worldScrollY);
    }
    
    private EntityAbstract getEntityAt(Point point)
    {
        // Nature
        EntityAbstract entity = this.getEntityAtForce(point, this.forceNature.getEntityList());
        if(entity != null) {return entity;}
        
        // Rulers
        for(int x = 0; x < this.forceList.size(); x++)
        {
            entity = this.getEntityAtForce(point, this.forceList.get(x).getEntityList());
            if(entity != null) {return entity;}
        }
        
        // Nothing
        return null;
    }
    
    private EntityAbstract getEntityAtForce(Point point, ArrayList<EntityAbstract> entity)
    {
        for(int x = 0; x < entity.size(); x++)
        {
            if(entity.get(x).getNexus().contains(point)) {return entity.get(x);}
        }
        return null;
    }
    
    private ForceRuler getForcePlayer()
    {
        return this.forceList.get(0);
    }
    
    public int getRenderPosX(int posX)
    {
        return posX - this.worldScrollX;
    }
    
    public int getRenderPosY(int posY)
    {
        return posY - this.worldScrollY;
    }
    
    public ArrayList<EntityAbstract> getSelectEntity()
    {
        return this.selectEntity;
    }
    
    public void inputKey(KeyEvent e)
    {
        System.out.println("INPUT KEY (size = " + this.selectEntity.size() + ")");
        if(this.selectEntity.size() == 1)
        {
            if(this.selectEntity.get(0).isRef("PLAYER_U1"))
            {
                System.out.println("PLAYER_U1 SELECTED!");
                this.inputKeyEntity(e, (UnitAbstract) this.selectEntity.get(0));
            }
        }
        else if(this.selectEntity.size() < 1) {this.inputKeyFree(e);}
    }
    
    public void inputKeyEntity(KeyEvent e, UnitAbstract unit)
    {
        // Arrow: Up
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            unit.move(Direction.NE);
        }
    }
    
    public void inputKeyFree(KeyEvent e)
    {
        // Arrow: Up
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.setWorldScrollMove(0, -50);
            return;
        }
        
        // Arrow: Down
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.setWorldScrollMove(0, 50);
            return;
        }
        
        // Arrow: Left
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.setWorldScrollMove(-50, 0);
            return;
        }
        
        // Arrow: Right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.setWorldScrollMove(50, 0);
            return;
        }
    }
    
    public void inputMouse(MouseEvent e)
    {
        // Pause
        if(this.pauseActive)
        {
            if(this.pauseDialog.getArea().contains(e.getPoint())) {this.pauseDialog.inputMouse(e);}
            return;
        }
        else
        {
            // Data
            if(this.dataArea.contains(e.getPoint())) {this.inputMouseData(e);}

            // Info
            else if(this.selectEntity.size() > 0 && this.infoSelect.getArea().contains(e.getPoint())) {this.inputMouseInfo(e);}

            // World
            else if(this.worldArea.contains(e.getPoint())) {this.inputMouseWorld(e);}
        }
    }
    
    private void inputMouseData(MouseEvent e)
    {
        // Menu
        if(this.dataOptionMenu.getArea().contains(e.getPoint()))
        {
            this.pause(true);
        }
    }
    
    private void inputMouseInfo(MouseEvent e)
    {
        //
    }
    
    private void inputMouseWorld(MouseEvent e)
    {
        EntityAbstract entity = this.getEntityAt(getBoardPoint(e.getPoint()));
        if(entity != null)
        {
            this.selectEntity = new ArrayList();
            this.selectEntity.add(entity);
        }
        else
        {
            this.selectEntity = new ArrayList();
        }
    }
    
    public void pause(boolean value)
    {
        this.pauseActive = value;
    }
    
    public void render(Graphics g)
    {
        this.renderWorld(g);
        this.renderForce(g);
        this.renderData(g);
        this.renderInfo(g);
        
        // temp
        if(this.pauseActive) {this.renderPause(g);}
    }
    
    private void renderData(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.dataArea, "INFO_BKG", true);
        
        // Resources
        this.dataResourceWood.render(g);
        this.dataResourceFood.render(g);
        this.dataResourceGold.render(g);
        this.dataResourceStone.render(g);
        
        // Age Info
        GFX.drawRect(g, new Rectangle(583, 5, 200, 30), "OPTION_BKG", true);
        GFX.drawRect(g, new Rectangle(583, 5, 200, 30), "OPTION_BORDER", false);
        GFX.write(g, "AGE", 683, 28, "CENTER", "INFO_STANDARD", "INFO_TEXT");
        
        // Menu Option
        this.dataOptionMenu.render(g);
        
        // Border
        GFX.drawLine(g, this.dataArea.x, this.dataArea.y + this.dataArea.height, this.dataArea.x + this.dataArea.width, this.dataArea.y + this.dataArea.height, "INFO_BORDER");
    }
    
    private void renderForce(Graphics g)
    {
        // Nature
        this.renderForceEntity(g, this.forceNature.getEntityList());
        
        // Rulers
        for(int x = 0; x < this.forceList.size(); x++)
        {
            this.renderForceEntity(g, this.forceList.get(x).getEntityList());
        }
    }
    
    private void renderForceEntity(Graphics g, ArrayList<EntityAbstract> entity)
    {
        for(int x = 0; x < entity.size(); x++)
        {
            entity.get(x).render(g);
        }
    }
    
    private void renderInfo(Graphics g)
    {
        if(this.selectEntity.size() > 0) {this.infoSelect.render(g);}
        this.infoMinimap.render(g);
    }
    
    private void renderPause(Graphics g)
    {
        // Fade
        GFX.drawRect(g, Application.getAppArea(), "BLACK", 0.5f);
        
        // Dialog
        this.pauseDialog.render(g);
    }
    
    private void renderWorld(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.worldArea, "BKG_GRASS", true);
        
        // temp
        GFX.drawImage(g, "resources/gfx/terrain/dirt1.png", 600, 300);
    }
    
    public void setWorldScroll(int scrollX, int scrollY)
    {
        this.worldScrollX = scrollX;
        this.worldScrollY = scrollY;
    }
    
    public void setWorldScrollMove(int scrollX, int scrollY)
    {
        this.worldScrollX += scrollX;
        if(this.worldScrollX < 0) {this.worldScrollX = 0;}
        this.worldScrollY += scrollY;
        if(this.worldScrollY < 0) {this.worldScrollY = 0;}
    }
    
    public void tick()
    {
        if(!this.pauseActive)
        {
            this.tickGame();
            this.tickForce();
        }
    }
    
    private void tickForce()
    {
        // Nature
        this.forceNature.tick();
        
        // Rulers
        for(int x = 0; x < this.forceList.size(); x++)
        {
            this.forceList.get(x).tick();
        }
    }
    
    private void tickGame()
    {
        // timer?
    }
    
}