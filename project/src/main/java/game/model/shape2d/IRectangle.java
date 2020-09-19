package game.model.shape2d;

public interface IRectangle extends IShape2D {
    double getWidth();
    double getHeight();
    double getRotation();
    void setRotation(double rotation);
}
