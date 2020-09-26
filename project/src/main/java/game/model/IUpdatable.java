package game.model;

public interface IUpdatable {
    // Interface used by any object which is intended to be updated each frame.
    // delta signifies the time (in seconds) since last update.
    // timeStep is the "speed" of the game, where timeStep = 1 is normal speed.
    void update(double delta, double timeStep);
}
