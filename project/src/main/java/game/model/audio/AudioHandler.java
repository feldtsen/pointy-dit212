package game.model.audio;


import game.App;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class AudioHandler {
    private static Media media;
    private static MediaPlayer mediaPlayer;

    public AudioHandler(){
        try {
            media = new Media(App.class.getResource("audio/bg.mp3").toURI().toASCIIString());
            mediaPlayer = new MediaPlayer(media);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void play() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(.2);
        mediaPlayer.play();
    }


}
