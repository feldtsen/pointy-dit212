package game;

import game.model.entity.IEntity;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.player.Player;
import game.model.shape2d.ICircle;
import game.services.EntityFactory;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<ILevel> levels;
    private ILevel currentLevel;

    public Game() {
        this.levels = dummyLevel();

    }

    public static List<ILevel> dummyLevel() {

        Player player = EntityFactory.basicPlayer();
        List<IEntity<?>> enemies = new ArrayList<IEntity<?>>();
        IEntity<ICircle> e1 = EntityFactory.basicEnemy(player);
        enemies.add(e1);

        ILevel level = new Level(enemies, new ArrayList<IEntity<?>>(), new ArrayList<IEntity<?>>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<ILevel>();
        levels.add(level);
        return levels;
    }
}
