/*
 * Snake
 * Jamie Purchase
 */
package game;

import app.Engine;
import app.input.InputKey;
import audio.Audio;
import console.Console;
import entities.Direction;
import entities.Entity;
import entities.enemies.Enemy;
import entities.fruit.Fruit;
import entities.nature.Nature;
import entities.notify.Notify;
import entities.projectile.Projectile;
import entities.snake.Snake;
import game.ui.UiInfo;
import game.ui.UiMinimap;
import game.ui.UiSnake;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import shapes.RectangleTools;
import styles.Style;
import world.Location;
import world.LocationArea;
import world.Solidity;
import world.World;
import world.WorldTerrain;

/**
 *
 * @author Jamie
 */
public class Game
{
    private World boardWorld;
    private int boardScrollX, boardScrollY;
    public Snake snake;
    public ArrayList<Fruit> fruit;
    public ArrayList<Enemy> enemy;
    public ArrayList<Nature> nature;
    public ArrayList<Notify> notify;
    public ArrayList<Projectile> projectile;
    private int score;
    private UiInfo uiInfo;
    private UiMinimap uiMinimap;
    private UiSnake uiSnake;
    private boolean pauseActive;
    private int pauseCursorNow, pauseCursorFrame;
    private final BufferedImage pauseCursorImage = Drawing.getImageResource("resources/gfx/interface/cursor1.png");
    private final ArrayList<String> pauseOption;
    private final Rectangle pauseBox = new Rectangle(483, 200, 400, 300);
    private int pauseTickNow;
    private final int pauseTickMax = 30;
    public Console console;
    
    // TEMP
    private BufferedImage tempTerrain;
    
    public Game(Location snakeLocation)
    {
        //this.boardWorld = (World) Engine.getAppVariable("PRELOAD_WORLD1");
        this.boardScrollX = 10;
        this.boardScrollY = 10;
        this.snake = new Snake(this, snakeLocation, 100);
        this.fruit = new ArrayList();
        this.enemy = new ArrayList();
        this.nature = new ArrayList();
        this.notify = new ArrayList();
        this.projectile = new ArrayList();
        this.score = 0;
        this.uiInfo = new UiInfo(this);
        this.uiMinimap = new UiMinimap(this);
        this.uiSnake = new UiSnake(this);
        this.pauseActive = false;
        this.pauseCursorNow = 0;
        this.pauseCursorFrame = 0;
        this.pauseOption = new ArrayList();
        this.pauseOption.add("RESUME GAME");
        this.pauseOption.add("ABOUT THE APP");
        this.pauseOption.add("EXIT TO WINDOWS");
        this.pauseTickNow = 0;
        this.console = null;
        //this.console = new Console();
        
        // Temp: Fruit
        this.fruit.add(new Fruit(this, new Location(17, 13), "apple1"));
        this.fruit.add(new Fruit(this, new Location(14, 18), "apple2"));
        this.fruit.add(new Fruit(this, new Location(19, 14), "bannana1"));
        
        // Temp: Enemy
        this.enemy.add(new Enemy(this, new Location(13, 16), Direction.WEST, "ant1", 100));
        //this.enemy.add(new Enemy(this, new Location(17, 16), Direction.EAST, "ant1", 100));
        
        // Temp: Nature
        this.nature.add(new Nature(this, new LocationArea(16, 16, 2, 2), "stone1"));
        
        // TEST
        //Audio.addMusic("ERROR_1", "resources/sfx/error1.mp3");
        
        // TEMP
        this.tempTerrain = (BufferedImage) Engine.getAppVariable("PRELOAD_WORLD1");
    }
    
    public void console(String output)
    {
        if(this.console != null) {this.console.output(output);}
    }
    
    public void createEnemy(Enemy newEnemy)
    {
        this.enemy.add(newEnemy);
    }
    
    public void createFruit(Fruit newFruit)
    {
        this.fruit.add(newFruit);
    }
    
    public void createNature(Nature newNature)
    {
        this.nature.add(newNature);
    }
    
    public void createNotify(Location notifyLocation, String notifyText)
    {
        this.createNotify(notifyLocation, notifyText, 0);
    }
    
    public void createNotify(Location notifyLocation, String notifyText, int offsetY)
    {
        this.notify.add(new Notify(this, notifyLocation, notifyText, offsetY));
    }
    
    public void createProjectile(Projectile newProjectile)
    {
        this.projectile.add(newProjectile);
    }
    
    public int getBoardScrollX()
    {
        return this.boardScrollX;
    }
    
    public int getBoardScrollY()
    {
        return this.boardScrollY;
    }
    
    public Enemy getEnemyAt(Location location)
    {
        for(int x = 0; x < this.enemy.size(); x++)
        {
            if(this.enemy.get(x).location.isAt(location)) {return this.enemy.get(x);}
        }
        return null;
    }
    
    public ArrayList<Enemy> getEnemyInArea(LocationArea area)
    {
        ArrayList<Enemy> enemyNear = new ArrayList();
        ArrayList<Location> enemyArea = area.getLocationArray();
        for(int x = 0; x < enemyArea.size(); x++)
        {
            Enemy enemyAt = this.getEnemyAt(enemyArea.get(x));
            if(enemyAt != null) {enemyNear.add(enemyAt);}
        }
        return enemyNear;
    }
    
    public Entity getEntityAt(Location location)
    {
        return this.getEntityAt(location, false, true, true, true);
    }
    
    public Entity getEntityAt(Location location, boolean checkSnake, boolean checkNature, boolean checkFruit, boolean checkEnemy)
    {
        // Snake
        if(checkSnake && this.snake.location.isAt(location)) {return this.snake;}
        
        // Nature
        Nature nature = this.getNatureAt(location);
        if(nature != null) {return nature;}
        
        // Fruit
        Fruit fruit = this.getFruitAt(location);
        if(fruit != null) {return fruit;}
        
        // Enemies
        Enemy enemy = this.getEnemyAt(location);
        if(enemy != null) {return enemy;}
        
        // Nothing
        return null;
    }
    
    public Fruit getFruitAt(Location location)
    {
        for(int x = 0; x < this.fruit.size(); x++)
        {
            if(this.fruit.get(x).location.isAt(location)) {return this.fruit.get(x);}
        }
        return null;
    }
    
    public ArrayList<Fruit> getFruitInArea(LocationArea area)
    {
        ArrayList<Fruit> fruitNear = new ArrayList();
        ArrayList<Location> fruitArea = area.getLocationArray();
        for(int x = 0; x < fruitArea.size(); x++)
        {
            Fruit fruitAt = this.getFruitAt(fruitArea.get(x));
            if(fruitAt != null) {fruitNear.add(fruitAt);}
        }
        return fruitNear;
    }
    
    public Nature getNatureAt(Location location)
    {
        for(int x = 0; x < this.nature.size(); x++)
        {
            if(this.nature.get(x).area.contains(location)) {return this.nature.get(x);}
        }
        return null;
    }
    
    public int getScore()
    {
        return this.score;
    }
    
    public void input(InputKey key)
    {
        // Console
        this.console("Game -> input(" + key.name() + ")");
        
        // Input
        if(this.pauseActive) {this.inputPause(key);}
        else {this.inputGame(key);}
    }
    
    private void inputGame(InputKey key)
    {
        // Commands
        if(key == InputKey.COMMAND_ESCAPE)
        {
            this.pauseActive = true;
            this.pauseCursorNow = 0;
        }
        if(key == InputKey.COMMAND_SPACE) {this.snake.action();}
        
        // Arrows
        if(key == InputKey.ARROW_DOWN) {this.snake.push(Direction.SOUTH);}
        if(key == InputKey.ARROW_LEFT) {this.snake.push(Direction.WEST);}
        if(key == InputKey.ARROW_RIGHT) {this.snake.push(Direction.EAST);}
        if(key == InputKey.ARROW_UP) {this.snake.push(Direction.NORTH);}
        
        // TEST
        if(key == InputKey.COMMAND_BACKSPACE) {this.snake.roleChange();}
    }
    
    private void inputPause(InputKey key)
    {
        // Commands
        if(key == InputKey.COMMAND_ESCAPE) {this.pauseActive = false;}
        if(key == InputKey.COMMAND_SPACE)
        {
            if(this.pauseCursorNow == 0) {this.pauseActive = false;}
            //if(this.pauseCursorNow == 1) {}
            if(this.pauseCursorNow == 2) {System.exit(0);}
        }
        
        // Arrows
        if(key == InputKey.ARROW_DOWN && this.pauseCursorNow < this.pauseOption.size() - 1) {this.pauseCursorNow += 1;}
        if(key == InputKey.ARROW_UP && this.pauseCursorNow > 0) {this.pauseCursorNow -= 1;}
    }
    
    public void render(Graphics g)
    {
        // World
        this.renderBackground(g);
        this.renderNature(g);
        
        // Pause
        if(this.pauseActive) {this.renderPause(g);}
        
        // Entities
        else
        {
            this.renderSnake(g);
            this.renderFruit(g);
            this.renderEnemy(g);
            this.renderProjectile(g);
            this.renderNotify(g);
            this.renderInterface(g);
            
            // Console
            if(this.console != null) {this.console.render(g);}
        }
    }
    
    private void renderBackground(Graphics g)
    {
        //Drawing.fillScreen(g, Style.colour("GRASS1"));
        //this.boardWorld.getTerrainArea(g, new Rectangle(0, 0, 1366, 768), this.boardScrollX, this.boardScrollY);
        if(this.tempTerrain != null) {Drawing.drawImage(g, this.tempTerrain.getSubimage(this.boardScrollX * 50, this.boardScrollY * 50, 1366, 768), 0, 0);}
    }
    
    private void renderEnemy(Graphics g)
    {
        for(int x = 0; x < this.enemy.size(); x++) {this.enemy.get(x).render(g);}
    }
    
    private void renderFruit(Graphics g)
    {
        for(int x = 0; x < this.fruit.size(); x++) {this.fruit.get(x).render(g);}
    }
    
    private void renderInterface(Graphics g)
    {
        //this.uiInfo.render(g);
        this.uiMinimap.render(g);
        this.uiSnake.render(g);
    }
    
    private void renderNature(Graphics g)
    {
        for(int x = 0; x < this.nature.size(); x++) {this.nature.get(x).render(g);}
    }
    
    private void renderNotify(Graphics g)
    {
        for(int x = 0; x < this.notify.size(); x++) {this.notify.get(x).render(g);}
    }
    
    private void renderProjectile(Graphics g)
    {
        for(int x = 0; x < this.projectile.size(); x++) {this.projectile.get(x).render(g);}
    }
    
    private void renderPause(Graphics g)
    {
        // Fade Screen
        Drawing.fadeScreen(g, Color.BLACK, 0.5f);
        
        // Draw Box
        Drawing.fillRect(g, RectangleTools.offset(this.pauseBox, 5, 5), Color.BLACK);
        Drawing.fillRect(g, this.pauseBox, Color.BLACK);
        Drawing.drawRect(g, this.pauseBox, Color.WHITE);
        
        // Title Text
        Text.writeShadow(g, "PAUSE", 683, 285, 2, "CENTER", Style.font("HEADER1"), Colour.getColourRGB(255, 255, 255), Colour.getColourRGB(100, 100, 100));
        
        // Option Text
        for(int x = 0; x < this.pauseOption.size(); x++)
        {
            Text.write(g, this.pauseOption.get(x), 590, x * 50 + 360, "LEFT", Style.font("STANDARD"), Colour.getColourRGB(255, 255, 255));
        }
        
        // Option Cursor
        BufferedImage cursorImage = this.pauseCursorImage.getSubimage(0, 0, 100, 50);
        if(this.pauseCursorFrame == 1) {cursorImage = this.pauseCursorImage.getSubimage(100, 0, 100, 50);}
        Drawing.drawImage(g, Drawing.resize(cursorImage, 50, 25), 515, this.pauseCursorNow * 50 + 340);
    }
    
    private void renderSnake(Graphics g)
    {
        this.snake.render(g);
    }
    
    public void setBoardScroll(int scrollX, int scrollY, boolean adjust)
    {
        this.setBoardScrollX(scrollX, adjust);
        this.setBoardScrollY(scrollY, adjust);
    }
    
    public void setBoardScrollX(int scrollX, boolean adjust)
    {
        if(adjust) {this.boardScrollX += scrollX;}
        else {this.boardScrollX = scrollX;}
    }
    
    public void setBoardScrollY(int scrollY, boolean adjust)
    {
        if(adjust) {this.boardScrollY += scrollY;}
        else {this.boardScrollY = scrollY;}
    }
    
    public void scoreAdd(int amount)
    {
        this.score += amount;
    }
    
    public void scoreSubtract(int amount)
    {
        this.score -= amount;
    }
    
    public void sfx(String key)
    {
        Audio.playMusic(key);
    }
    
    public void tick()
    {
        if(this.pauseActive) {this.tickPause();}
        else
        {
            this.snake.tick();
            this.tickFruit();
            this.tickEnemy();
            this.tickProjectile();
            this.tickNotify();
        }
    }
    
    private void tickEnemy()
    {
        for(int x = 0; x < this.enemy.size(); x++) {this.enemy.get(x).tick();}
    }
    
    private void tickFruit()
    {
        for(int x = 0; x < this.fruit.size(); x++) {this.fruit.get(x).tick();}
    }
    
    private void tickNotify()
    {
        for(int x = 0; x < this.notify.size(); x++) {this.notify.get(x).tick();}
    }
    
    private void tickPause()
    {
        this.pauseTickNow += 1;
        if(this.pauseTickNow >= this.pauseTickMax)
        {
            this.pauseTickNow = 0;
            this.pauseCursorFrame += 1;
            if(this.pauseCursorFrame > 1) {this.pauseCursorFrame = 0;}
        }
    }
    
    private void tickProjectile()
    {
        for(int x = 0; x < this.projectile.size(); x++) {this.projectile.get(x).tick();}
    }
    
}