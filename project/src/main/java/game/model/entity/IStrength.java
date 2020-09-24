package game.model.entity;

public interface IStrength {

    double getStrength();

    default boolean isStrongerThan(IStrength other) {
        return this.getStrength() > other.getStrength();
    }

}
