package game.model.shape2d;

public interface IRectangle extends IRotatableShape {
    double getWidth();
    double getHeight();
    double getRotation();
    void setRotation(double rotation);
}
