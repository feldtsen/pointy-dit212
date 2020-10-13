package game.controller.event;

// Any class implementing this interface should notify its listeners
// when an ability action is activated or has finished
public interface AbilityActionEventHandler {
    // Method for registering listeners
    void registerListener(AbilityActionEventListener listener);
}
