package game.model.ability;

public abstract class Ability implements IAbility{

    private long cooldown;
    private long lastUsed;

    public Ability(long cooldown) {
        this.cooldown = cooldown;
        this.lastUsed = 0;
    }

    @Override
    public long getCooldown(){return cooldown;}

    public boolean use(){
        long currentTime = System.nanoTime();
        if (currentTime - lastUsed >= cooldown){
            lastUsed = currentTime;
            return true;
        }
        return false;
    }

    public abstract void activate();

}



