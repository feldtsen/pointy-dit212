/*
 * Authors: Joachim Pedersen
 *
 * Abstraction for a simple audio handler. Music is toggled using
 * the toggleMusic method and sound effects are registered using the
 * registerSoundEffect method.
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
