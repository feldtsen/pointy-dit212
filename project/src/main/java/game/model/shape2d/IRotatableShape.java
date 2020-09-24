package game.model.shape2d;

public interface IRotatableShape extends IShape2D {
    double getRotation();
    void setRotation(double rotation);
    double getHeight();
    double getWidth();
}
