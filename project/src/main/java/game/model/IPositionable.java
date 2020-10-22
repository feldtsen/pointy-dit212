/*
 * Authors: Anton Hildingsson
 *
 * Interface implemented by any object in the game which has an position.
 */

package game.model;

import javafx.geometry.Point2D;

public interface IPositionable {
    Point2D getPosition();
}
