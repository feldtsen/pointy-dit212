/*
 * Authors: Joachim Pedersen
 *
 * The audio handler handles music and sound effects for the game. 
 * Sound effects are registered using the registerSoundEffect method. 
 * A registered sound effect will be played once when the playSoundEffect
 * method is called. 
 */

package game.model.audio;

import game.App;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class AudioHandler implements IAudioHandler{
    private MediaPlayer themePlayer;
    // When doing certain actions, we can add sound effect to a array we can iterate over when playing
    private final ArrayList<MediaPlayer> soundEffectPlayers = new ArrayList<>();

    public AudioHandler(){
        try {
            // Creates a media player for handling playing a single media file
            Media mainTheme = new Media(App.class.getResource("audio/bg.mp3").toURI().toASCIIString());
            // Tells media player what media to play
            themePlayer = new MediaPlayer(mainTheme);
            // Used to play music indefinitely
            themePlayer.setCycleCount(MediaPlayer.INDEFINITE);
            // Lower the default volume
            themePlayer.setVolume(.06);
            // Plays the media associated with the player
            themePlayer.play();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void playSoundEffects() {
        // We don't want multiple media players floating around, so we dispose of those we don't need
        for (MediaPlayer soundEffectPlayer : soundEffectPlayers) {
            soundEffectPlayer.play();
            // To avoid a media player to remain active even when it is not playing,
            // we dispose of it when the media ends
            soundEffectPlayer.setOnEndOfMedia(soundEffectPlayer::dispose);
        }
    }

    @Override
    public void registerSoundEffect(String track) {
        try {
            // Creates a new mediaPlayer for every track we want to queue
            Media sfx = new Media(App.class.getResource("audio/"+track+".mp3").toURI().toASCIIString());

            // Associates a player with a media file
            MediaPlayer sfxPlayer = new MediaPlayer(sfx);

            // Add the media player to the queue of sounds to be played during game loop
            soundEffectPlayers.add(sfxPlayer);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toggleMusic() {
        themePlayer.setMute(!themePlayer.isMute());
    }


}
