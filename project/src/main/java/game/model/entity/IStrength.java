package game.model.entity;

public interface IStrength {

    int getStrength();

    default boolean isStrongerThan(IStrength other) {
        return this.getStrength() > other.getStrength();
    }

}
