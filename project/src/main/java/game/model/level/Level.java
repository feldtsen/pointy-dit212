package game.model.level;

import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.entity.projectile.IProjectile;

import java.util.List;

public class Level implements ILevel {

    private List<Enemy> enemies;
    private List<IProjectile<?>> projectiles;
    private List<IObstacle<?>> obstacles;
    private Player player;
    private final double width;
    private final double height;



    public Level(List<Enemy> enemies, List<IProjectile<?>> projectiles, List<IObstacle<?>> obstacles, Player player, double width, double height) {
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.obstacles = obstacles;
        this.player = player;
        this.width = width;
        this.height = height;
    }

    @Override
    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    @Override
    public List<IProjectile<?>> getProjectiles() {
        return this.projectiles;
    }

    @Override
    public List<IObstacle<?>> getObstacles() {
        return this.obstacles;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    };

    @Override
    public double getWidth(){
        return this.width;
    }

    @Override
    public double getHeight(){
        return this.height;
    }


}
