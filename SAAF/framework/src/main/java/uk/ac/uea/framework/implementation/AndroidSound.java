package uk.ac.uea.framework.implementation;

import android.media.SoundPool;

import uk.ac.uea.framework.Sound;

/**
 * Manage use of sounds
 */
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    /**
     * Class constructor
     * @param soundPool
     * @param soundId
     */
    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    /**
     * play the sound
     * @param volume
     */
    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    /**
     * Stop the sound
     */
    @Override
    public void stop(){
        soundPool.stop(soundId);
    }

    /**
     * Dispose of the sound
     */
    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }

}
