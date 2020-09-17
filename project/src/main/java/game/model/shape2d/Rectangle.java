package game.model.shape2d;

public class Rectangle implements IShape2D, IRectangle{
    private double width;
    private double height;
    private double rotation;

    public Rectangle(double width, double height, double rotation) {
        this.height = height;
        this.width = width;
        this.rotation = rotation;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(double rotation)  {
        this.rotation = rotation;
    }
}
