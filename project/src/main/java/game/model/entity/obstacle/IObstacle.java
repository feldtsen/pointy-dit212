package game.model.entity.obstacle;

import game.model.IUpdatable;
import game.model.entity.IEntity;
import game.model.shape2d.Rectangle;

// Interface marking all obstacles.
// An obstacle is a non-hostile entity on the map which typically blocks projectiles
// and/or entity movements
public interface IObstacle extends IEntity<Rectangle>, IUpdatable {
}
