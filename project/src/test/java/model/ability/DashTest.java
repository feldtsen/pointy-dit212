package model.ability;

import game.model.ability.IAbility;
import game.model.ability.Shockwave;
import game.model.ability.action.IAbilityAction;
import game.model.entity.Entity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.model.level.ILevel;
import game.model.level.Level;
import game.services.EntityFactory;
import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class DashTest {

    ILevel level;
    IPlayer player;
    IEnemy enemy;

    @Before
    public void before(){
        player = EntityFactory.basicPlayer(500, 500);
        //enemy = EntityFactory.basicEnemy()
    }
}
