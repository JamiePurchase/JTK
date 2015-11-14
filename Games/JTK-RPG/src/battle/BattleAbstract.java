/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle;

import battle.action.ActionAbstract;
import battle.action.ActionType;
import battle.action.damage.DamageType;
import battle.action.phase.PhaseAnimation;
import battle.action.phase.PhaseDamage;
import battle.ui.InfoParty;
import battle.unit.UnitEnemy;
import battle.unit.UnitParty;
import battle.visual.VisualAbstract;
import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import menu.MenuAbstract;
import tools.RectangleTool;

/**
 *
 * @author Jamie
 */
public class BattleAbstract
{
    // Background
    private String background;
    
    // Time
    private int timeBattle, timeTurnNow;
    
    // Units
    private ArrayList<UnitParty> unitParty;
    private ArrayList<UnitEnemy> unitEnemy;
    
    // Info
    private InfoParty infoParty;
    
    // Command
    private boolean commandActive;
    private MenuAbstract commandMenu;
    private Rectangle commandArea = new Rectangle(30, 540, 300, 201);
    
    // Action
    private boolean actionActive;
    private ActionAbstract actionObject;
    private int actionTickNow, actionTickMax;
    
    // Visual
    private ArrayList<VisualAbstract> visualList;
    
    public BattleAbstract()
    {
        // Background
        this.background = "";
        
        // Time
        this.timeBattle = 0;
        this.timeTurnNow = 0;
        
        // Units
        this.unitParty = new ArrayList();
        this.unitEnemy = new ArrayList();
        
        // Info
        this.infoParty = new InfoParty(this);
        
        // Command
        this.commandActive = true;
        this.commandMenu = new MenuAbstract("COMMAND");
        this.commandMenu.setItemColour("BLACK");
        this.commandMenu.setItemShadow("TITLE_SHADOW");
        this.commandMenu.setItemFont("TITLE_MENU");
        this.commandMenu.setCursorString("~");
        this.commandMenu.addItem("COMMAND_1", "ATTACK", 50, 575);
        this.commandMenu.addItem("COMMAND_2", "SWORD ART", 50, 625);
        this.commandMenu.addItem("COMMAND_3", "SUMMON", 50, 675);
        this.commandMenu.addItem("COMMAND_4", "ITEM", 50, 725);
        
        // Action
        this.actionActive = false;
        this.actionObject = null;
        this.actionTickNow = 0;
        this.actionTickMax = 0;
        
        // Visual
        this.visualList = new ArrayList();
        
        // Temp
        this.unitParty.add(new UnitParty(this, "UNIT_ALLY1", "Jamie"));
        this.unitEnemy.add(new UnitEnemy(this, "UNIT_ENEMY1", "Skeleton"));
    }
    
    public void actionCreate(ActionAbstract newAction)
    {
        this.actionActive = true;
        this.actionObject = newAction;
        this.actionTickNow = 0;
        this.actionTickMax = 12;
    }
    
    public void actionDone()
    {
        this.actionActive = false;
        this.actionObject = null;
        this.actionTickNow = 0;
        this.actionTickMax = 0;
    }
    
    public ArrayList<UnitParty> getUnitParty()
    {
        return this.unitParty;
    }
    
    public void inputKey(KeyEvent e)
    {
        if(this.commandActive)
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                //
                // Temp
                this.unitParty.get(0).tempAnim = true;
                this.commandActive = false;
                //
                ActionAbstract action = new ActionAbstract(this, "ATTACK", ActionType.ATTACK, this.unitParty.get(0));
                action.addPhase(new PhaseAnimation(action, this.unitParty.get(0)));
                action.addPhase(new PhaseDamage(action, DamageType.MELEE, 24, this.unitEnemy.get(0)));
                this.actionCreate(action);
            }
            if(e.getKeyCode() == KeyEvent.VK_UP) {this.commandMenu.inputUp();}
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {this.commandMenu.inputDown();}
        }
    }
    
    public void render(Graphics g)
    {
        // Background
        this.renderBackground(g);
        
        // Party
        this.renderParty(g);
        
        // Enemies
        this.renderEnemy(g);
        
        // Info
        this.renderInfo(g);
        
        // Visual
        if(this.visualList.size() > 0) {this.renderVisual(g);}
        
        // Command
        if(this.commandActive) {this.renderCommand(g);}
        
        //
        // Temp
        //
        GFX.drawRect(g, new Rectangle(10, 10, 200, 40), "WHITE", true);
        GFX.drawRect(g, new Rectangle(10, 10, 200, 40), "BLACK", false);
        GFX.write(g, "HP: " + this.unitEnemy.get(0).getStatHealthNow(), 25, 40, "LEFT", "UI_OPTION", "BLACK");
    }
    
    private void renderBackground(Graphics g)
    {
        // temp
        GFX.drawRect(g, Application.getAppArea(), "BKG_GRASS", true);
    }
    
    private void renderCommand(Graphics g)
    {
        // Shadow
        GFX.drawRect(g, RectangleTool.offset(this.commandArea, 4, 4), "DIALOG_SHADOW", true);
        
        // Background
        GFX.drawRect(g, this.commandArea, "WHITE", true);
        
        // Menu Options
        this.commandMenu.render(g);
        
        // Border
        GFX.drawRect(g, this.commandArea, "BORDER", false);
    }
    
    private void renderEnemy(Graphics g)
    {
        for(int x = 0; x < this.unitEnemy.size(); x++)
        {
            this.unitEnemy.get(x).render(g);
        }
    }
    
    private void renderInfo(Graphics g)
    {
        // Party Info
        this.infoParty.render(g);
    }
    
    private void renderParty(Graphics g)
    {
        for(int x = 0; x < this.unitParty.size(); x++)
        {
            this.unitParty.get(x).render(g);
        }
    }
    
    private void renderVisual(Graphics g)
    {
        for(int x = 0; x < this.visualList.size(); x++)
        {
            this.visualList.get(x).render(g);
        }
    }
    
    public void tick()
    {
        if(this.actionActive) {this.tickAction();}
        if(this.visualList.size() > 0) {this.tickVisual();}
        this.tickParty();
        this.tickEnemy();
        if(this.commandActive) {this.commandMenu.tick();}
    }
    
    private void tickAction()
    {
        this.actionTickNow += 1;
        if(this.actionTickNow >= this.actionTickMax)
        {
            this.actionTickNow = 0;
            this.actionObject.phaseNext();
        }
    }
    
    private void tickEnemy()
    {
        for(int x = 0; x < this.unitEnemy.size(); x++)
        {
            this.unitEnemy.get(x).tick();
        }
    }
    
    private void tickParty()
    {
        for(int x = 0; x < this.unitParty.size(); x++)
        {
            this.unitParty.get(x).tick();
        }
    }
    
    private void tickVisual()
    {
        for(int x = 0; x < this.visualList.size(); x++)
        {
            this.visualList.get(x).tick();
        }
    }
    
    public void visualAdd(String caption, int posX, int posY)
    {
        this.visualList.add(new VisualAbstract(this, caption, posX, posY));
    }
    
    public void visualRemove(VisualAbstract remove)
    {
        this.visualList.remove(remove);
    }
    
}