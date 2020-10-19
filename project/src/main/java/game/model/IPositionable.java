/*
 * Authors: Anton Hildingsson
 */

package game.model;

import javafx.geometry.Point2D;

// Interface implemented by any object in the game which has an position.
public interface IPositionable {
    Point2D getPosition();
}
