package game.model.entity.obstacle;

import game.model.IUpdatable;
import game.model.entity.IEntity;
import game.model.shape2d.Rectangle;

//Interface marking all obstacles.
public interface IObstacle extends IEntity<Rectangle>, IUpdatable {
}
