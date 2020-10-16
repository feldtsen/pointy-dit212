package game.controller.event;

// Any class implementing this interface should notify its listeners
// when an ability action is activated or has finished
public interface AbilityActionEventHandler {
    // Method for registering listeners. These listeners are typically stored in an internal array
    // and iterated through each time an event is sent.
    void registerListener(AbilityActionEventListener listener);
}
