/*
 * Authors: Joachim Pedersen
 */

package game.model.audio;

import game.App;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class AudioHandler {
    private MediaPlayer themePlayer;
    private final ArrayList<MediaPlayer> sfxPlayers = new ArrayList<>();

    public AudioHandler(){
        try {
            Media mainTheme = new Media(App.class.getResource("audio/bg.mp3").toURI().toASCIIString());
            themePlayer = new MediaPlayer(mainTheme);
            themePlayer.setCycleCount(MediaPlayer.INDEFINITE);
            themePlayer.setVolume(.06);
            themePlayer.play();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void playSfx () {
        // We don't want multiple media players floating around, so we dispose of those we don't need
        for (MediaPlayer sfxPlayer : sfxPlayers) {
            sfxPlayer.play();
            // To avoid a media player to remain active even when it is not playing,
            // we dispose of it when the media ends
            sfxPlayer.setOnEndOfMedia(sfxPlayer::dispose);
        }
    }

    public void registerSfx(String track) {
        try {
            Media sfx = new Media(App.class.getResource("audio/"+track+".mp3").toURI().toASCIIString());
            MediaPlayer sfxPlayer = new MediaPlayer(sfx);
            sfxPlayers.add(sfxPlayer);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
