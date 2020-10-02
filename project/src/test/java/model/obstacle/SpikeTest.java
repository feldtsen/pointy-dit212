package model.obstacle;

import game.model.entity.obstacle.Spikes;
import game.model.entity.player.Player;
import game.services.EntityFactory;
import javafx.geometry.Point2D;
import org.junit.Test;

public class SpikeTest {


    @Test
    public void testSpike() {

        Spikes spikes = new Spikes(new Point2D(0,0),10,10,11);
        Player player = EntityFactory.basicPlayer(0,0);

        if (player.checkCollision(spikes)) {
            player.setHitPoints(player.getHitPoints() - spikes.getStrength());
        }
        assert(player.getHitPoints() == -10);
    }
}
