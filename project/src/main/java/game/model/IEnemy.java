package game.model;

public interface IEnemy extends ILiving, IMovable {
    boolean attack();
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);
}
