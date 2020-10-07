package game.view.score;

import game.model.entity.player.IPlayer;

public interface IScorePanel {
    void updateScore(IPlayer player, int score);
}
