package game.model.shape2d;

// A rotatable shape is any shape which can be rotated.
public interface IRotatableShape extends IShape2D {
    // Rotation is defined in radians.
    double getRotation();
    void setRotation(double rotation);
    double getHeight();
    double getWidth();
}
