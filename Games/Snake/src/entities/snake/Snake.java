/*
 * Snake
 * Jamie Purchase
 */
package entities.snake;

import entities.Direction;
import entities.Entity;
import entities.enemies.Enemy;
import entities.fruit.Fruit;
import entities.nature.Nature;
import entities.projectile.Projectile;
import entities.projectile.ProjectileType;
import entities.snake.role.Role;
import entities.status.Status;
import game.Game;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import world.Location;

/**
 *
 * @author Jamie
 */
public class Snake extends Entity
{
    private Direction direction;
    private SnakeStatus status;
    private ArrayList<Role> roleArray;
    private int roleActive;
    private ArrayList<Location> tail;
    private SnakeAction actionSelect;
    //private boolean actionActive;
    
    // TEST
    private int zAlt;
    
    public Snake(Game game, Location location, int health)
    {
        super(game, location);
        this.direction = Direction.SOUTH;
        this.status = SnakeStatus.STANDARD;
        this.roleArray = new ArrayList();
        this.roleArray.add(new Role("GRASS", 3, SnakeRole.GRASS));
        this.roleActive = 0;
        this.tail = new ArrayList();
        //this.actionSelect = SnakeAction.NONE;
        //this.actionActive = false;
        this.createValue("HEALTH", 100, 100);
        this.createValue("CHARGE", 100, 100);
        this.createValue("BURROW", 0, 120);
        
        // TEST
        this.createValue("HEALTH", 85, 140);
        this.createValue("CHARGE", 32, 196);
        
        // TEMP
        this.tail.add(new Location(this.location.x, this.location.y - 1));
        this.tail.add(new Location(this.location.x, this.location.y - 2));
        
        // TEMP
        //this.actionSelect = SnakeAction.FROST;
    }
    
    public void action()
    {
        /*if(this.getActionActive() == false && this.actionSelect != SnakeAction.NONE && this.getValueNow("CHARGE") == 100)
        {
            this.setValue("CHARGE", 0);
            if(this.actionSelect == SnakeAction.BURROW) {this.actionBurrow();}
            if(this.actionSelect == SnakeAction.FROST) {this.actionFrost();}
            if(this.actionSelect == SnakeAction.VENOM) {this.actionVenom();}
        }*/
    }
    
    /*private void actionBurrow()
    {
        this.game.console("Snake -> BURROW");
        this.setValue("BURROW", 0);
        this.actionActive = true;
        this.game.createNotify(this.location, "BURROW");
        this.game.createNature(new Nature(this.game, this.location, "burrow1"));
    }
    
    private void actionBurrowEnd()
    {
        this.game.console("Snake -> BURROW end");
        this.actionActive = false;
        this.game.createNotify(this.location, "BURROW");
    }
    
    private void actionFrost()
    {
        this.game.console("Snake -> FROST");
        this.game.createProjectile(new Projectile(this.game, this.location.getAdjacent(this.direction), this.direction, true, "frost1", ProjectileType.FROST, 20, 6, Status.FROZEN, 50));
    }
    
    private void actionVenom()
    {
        this.game.console("Snake -> VENOM");
        this.game.createProjectile(new Projectile(this.game, this.location.getAdjacent(this.direction), this.direction, true, "venom1", ProjectileType.VENOM, 20, 6));
    }*/
    
    public void collideProjectile(Projectile projectile)
    {
        //
    }
    
    private SnakeAction getActionActive()
    {
        return this.getRoleActive().getActionActive();
    }
    
    public Direction getDirection()
    {
        return this.direction;
    }
    
    private BufferedImage getRenderImage()
    {
        if(this.direction == Direction.EAST) {return this.getRenderImageFile().getSubimage(150, 0, 50, 50);}
        if(this.direction == Direction.NORTH) {return this.getRenderImageFile().getSubimage(0, 0, 50, 50);}
        if(this.direction == Direction.SOUTH) {return this.getRenderImageFile().getSubimage(50, 0, 50, 50);}
        if(this.direction == Direction.WEST) {return this.getRenderImageFile().getSubimage(100, 0, 50, 50);}
        return null;
    }
    
    private BufferedImage getRenderImageFile()
    {
        return this.getRoleActive().getImageSnake();
    }
    
    private BufferedImage getRenderImageTailMain()
    {
        return this.getRenderImageFile().getSubimage(200, 0, 50, 50);
    }
    
    private BufferedImage getRenderImageTailEnd()
    {
        return this.getRenderImageFile().getSubimage(300, 0, 50, 50);
    }
    
    private BufferedImage getRenderImageTailJoin()
    {
        return this.getRenderImageFile().getSubimage(250, 0, 50, 50);
    }
    
    public Role getRoleActive()
    {
        return this.roleArray.get(this.roleActive);
    }
    
    public void push(Direction direction)
    {
        // Update Direction
        this.direction = direction;
        
        // Target Location
        Location pushLocation = this.location.getAdjacent(direction);
        boolean pushNature = false;
        
        // Console
        this.game.console("Snake -> push(" + direction.name() + ")");
        this.game.console(" current: " + this.location.asString());
        this.game.console(" target: " + pushLocation.asString());
        
        // Nature
        Nature nature = this.game.getNatureAt(pushLocation);
        if(nature != null)
        {
            this.game.console(" collided with nature");
            pushNature = true;
        }
        
        // Fruit
        Fruit fruit = this.game.getFruitAt(pushLocation);
        if(fruit != null)
        {
            this.game.console(" collided with fruit");
            fruit.eat();
        }
        
        // Enemies
        Enemy enemy = this.game.getEnemyAt(pushLocation);
        if(enemy != null)
        {
            this.game.console(" collided with enemy");
            enemy.collide();
        }
        
        // Update Location
        if(pushNature == false)
        {
            this.game.console(" moved to location: " + pushLocation.asString());
            this.pushMove(pushLocation);
            
            // TEMP
            if(this.direction == Direction.EAST) {this.game.setBoardScrollX(1, true);}
            if(this.direction == Direction.NORTH) {this.game.setBoardScrollY(-1, true);}
            if(this.direction == Direction.SOUTH) {this.game.setBoardScrollY(1, true);}
            if(this.direction == Direction.WEST) {this.game.setBoardScrollX(-1, true);}
        }
    }
    
    private void pushMove(Location newLocation)
    {
        // Create Tail
        ArrayList<Location> newTail = new ArrayList();
        for(int x = 0; x < this.tail.size(); x++)
        {
            if(x == 0) {newTail.add(this.location);}
            else {newTail.add(this.tail.get(x - 1));}
        }
        
        // Update Tail
        this.tail = newTail;
        
        // Update Head
        this.location = newLocation;
    }
    
    public void remove()
    {
        //
    }
    
    public void render(Graphics g)
    {
        // Burrow
        /*if(this.actionActive && this.actionSelect == SnakeAction.BURROW)
        {
            //
        }*/
        
        // Snake
        //else
        //{
            this.renderSnake(g);
            
            // TEMP
            System.out.println("Snake -> render at " + this.location.asString());
            System.out.println(" render pos: " + this.getRenderPosX() + ", " + this.getRenderPosY());
        //}
    }
    
    private void renderSnake(Graphics g)
    {
        // Tail
        if(this.tail.size() > 0)
        {
            for(int x = 0; x < this.tail.size(); x++)
            {
                if(x == this.tail.size() - 1) {this.renderSnakeTail(g, this.tail.get(x), true, this.tail.get(x - 1));}
                else {this.renderSnakeTail(g, this.tail.get(x), false, this.location);}
            }
        }
        
        // Head
        Drawing.drawImage(g, this.getRenderImage(), this.getRenderPosX(), this.getRenderPosY());
    }
    
    private void renderSnakeTail(Graphics g, Location tailMain, boolean end, Location tailJoin)
    {
        // Tail Join
        Direction joinD = tailMain.getDirection(tailJoin);
        Drawing.drawImage(g, this.getRenderImageTailJoin(), tailMain.getRenderPosXJoin(this.game, joinD), tailMain.getRenderPosYJoin(this.game, joinD));
        
        // Tail Main
        BufferedImage mainImage = this.getRenderImageTailMain();
        if(end) {mainImage = this.getRenderImageTailEnd();}
        Drawing.drawImage(g, mainImage, tailMain.getRenderPosX(this.game), tailMain.getRenderPosY(this.game));
    }
    
    public void roleChange()
    {
        this.roleActive += 1;
        if(this.roleActive > this.roleArray.size() - 1) {this.roleActive = 0;}
    }
    
    public void roleChange(SnakeRole role)
    {
        for(int x = 0; x < this.roleArray.size(); x++)
        {
            if(this.roleArray.get(x).getType() == role) {this.roleActive = x;}
        }
    }
    
    public void tick()
    {
        // Collision
        this.tickCollision();
        
        // Charge
        if(this.getValueNow("CHARGE") < 100 && this.status != SnakeStatus.CHARGE_NULL) {this.setValue("CHARGE", 1, true);}
        
        // Burrow
        /*if(this.actionActive && this.actionSelect == SnakeAction.BURROW)
        {
            this.setValue("BURROW", 1, true);
            if(this.getValue("BURROW").isMax()) {this.actionBurrowEnd();}
        }*/
    }
    
    private void tickCollision()
    {
        //if(this.game.getEntityAt(this.location))
    }
    
}