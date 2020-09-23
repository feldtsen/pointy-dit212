package game.model.entity.obstacle;
import game.model.entity.Entity;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

public class Wall extends Entity<Rectangle> implements IObstacle{

    public Wall(Point2D position, double width, double height) {
        super(position, new Rectangle(width, height,0));
    }


    @Override
    public void update(double delta, double timestep) {

    }
}
