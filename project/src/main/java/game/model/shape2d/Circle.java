package game.model.shape2d;

public class Circle implements ICircle{

    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }



}
