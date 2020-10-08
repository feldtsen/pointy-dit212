package game.view;


public interface IVisitableShape {
    void acceptShapeVisitor(IShapeVisitor visitor);
}
