/*
 * Authors: Joachim Pedersen
 */

package game.model.audio;

public interface IAudioHandler {
    // Plays all sound
    void playSoundEffects();

    // Register a sound effect to play
    void registerSoundEffect(String track);

    // Mutes the theme melody
    void toggleMusic();
}
