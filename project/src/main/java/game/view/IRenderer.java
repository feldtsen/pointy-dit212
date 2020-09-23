package game.view;

import game.model.entity.IEntity;
import javafx.scene.paint.Color;

public interface IRenderer {
    void clear();
    void setBackgroundColor(Color color);
    void drawEntity(IEntity<?> entity);
}
