package game.view;

import game.model.level.ILevel;

// A view for rendering a single level
public interface IRenderer {
    void draw(ILevel level);
}
