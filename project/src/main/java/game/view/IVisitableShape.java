/*
 * Authors: Joachim Pedersen
 *
 * Interface for visitable shapes. Used for implementing the visitor
 * pattern for shapes.
 */

package game.view;

public interface IVisitableShape {
    void acceptShapeVisitor(IShapeVisitor visitor);
}
