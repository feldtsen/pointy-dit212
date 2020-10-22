package model.obstacle;

import game.model.entity.obstacle.Spikes;
import game.model.entity.player.Player;
import game.services.EntityFactory;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpikeTest {


    @Test
    public void testSpike() {

        Spikes spikes = new Spikes(new Point2D(0,0),10,10,100);
        Player player = EntityFactory.basicPlayer(0,0);

        Point2D minimumTranslationVector = player.checkCollision(spikes);
        if (minimumTranslationVector != null) {
            spikes.handleCollision(minimumTranslationVector, player);
        }
        assertFalse(player.isAlive());
    }
}
