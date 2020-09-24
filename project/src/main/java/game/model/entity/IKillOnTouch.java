package game.model.entity;

public interface IKillOnTouch {

    double getStrength();

    default boolean isStrongerThan(IKillOnTouch other) {
        return this.getStrength() > other.getStrength();
    }

}
