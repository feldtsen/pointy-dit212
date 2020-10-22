/*
 * Authors: Anton Hildingsson, Mattias Oom, Joachim Pedersen
 *
 * Simple circle interface
 */

package game.model.shape2d;

public interface ICircle extends IShape2D {
    // The radius of the circle. The radius should be equal to the width and height of the shape
    double getRadius();
}
