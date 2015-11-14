/*
 * Snake
 * Jamie Purchase
 */
package entities.enemies.ai;

import entities.enemies.Enemy;
import game.Game;
import world.Location;

/**
 *
 * @author Jamie
 */
public class AiTarget
{
    private Game game;
    private Enemy enemy;
    public Location location;
    
    public AiTarget(Game game, Enemy enemy, Location location)
    {
        this.game = game;
        this.enemy = enemy;
        this.location = location;
    }
    
}