/*
 * Snake
 * Jamie Purchase
 */
package world;

import entities.Direction;
import game.Game;
import maths.Maths;

/**
 *
 * @author Jamie
 */
public class Location
{
    public int x, y;
    
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public String asKey()
    {
        return "x" + Maths.stringDigit(this.x, 3) + "y" + Maths.stringDigit(this.y, 3);
    }
    
    public String asString()
    {
        return "LOCATION [" + this.x + ", " + this.y + "]";
    }
    
    public Location getAdjacent(Direction direction)
    {
        int adjacentX = this.x;
        int adjacentY = this.y;
        if(direction == Direction.EAST) {adjacentX += 1;}
        if(direction == Direction.NORTH) {adjacentY -= 1;}
        if(direction == Direction.SOUTH) {adjacentY += 1;}
        if(direction == Direction.WEST) {adjacentX -= 1;}
        return new Location(adjacentX, adjacentY);
    }
    
    public LocationArea getAreaNearby()
    {
        return new LocationArea(this.x - 4, this.y - 4, 11, 11);
    }
    
    public Direction getDirection(Location location)
    {
        if(this.x < location.x) {return Direction.WEST;}
        if(this.x > location.x) {return Direction.EAST;}
        if(this.y > location.y) {return Direction.NORTH;}
        if(this.y < location.y) {return Direction.SOUTH;}
        return null;
    }
    
    public int getRenderPosX(Game game)
    {
        return (this.x - game.getBoardScrollX()) * 50;
    }
    
    public int getRenderPosXJoin(Game game, Direction direction)
    {
        if(direction == Direction.EAST) {return this.getRenderPosX(game) - 25;}
        if(direction == Direction.WEST) {return this.getRenderPosX(game) + 25;}
        return this.getRenderPosX(game);
    }
    
    public int getRenderPosY(Game game)
    {
        return (this.y - game.getBoardScrollY()) * 50;
    }
    
    public int getRenderPosYJoin(Game game, Direction direction)
    {
        if(direction == Direction.NORTH) {return this.getRenderPosY(game) - 25;}
        if(direction == Direction.SOUTH) {return this.getRenderPosY(game) + 25;}
        return this.getRenderPosY(game);
    }
    
    public boolean isAt(Location location)
    {
        return this.isAt(location.x, location.y);
    }
    
    public boolean isAt(int atX, int atY)
    {
        if(this.x == atX && this.y == atY) {return true;}
        return false;
    }
    
}