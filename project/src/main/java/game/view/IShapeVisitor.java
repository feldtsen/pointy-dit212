/*
 * Authors: Joachim Pedersen
 *
 * Interface for implementing visitor pattern for shapes.
 * This is used to avoid casting and the use of "instanceof" when
 * rendering shapes.
 */

package game.view;

import game.model.shape2d.ICircle;
import game.model.shape2d.IRectangle;
import game.model.shape2d.ITriangle;

public interface IShapeVisitor {
    void visit(ICircle circle);
    void visit(IRectangle rectangle);
    void visit(ITriangle triangle);
}
