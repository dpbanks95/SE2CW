package uk.ac.uea.framework.implementation;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import uk.ac.uea.framework.Audio;
import uk.ac.uea.framework.Music;
import uk.ac.uea.framework.Sound;

/**
 * Used to create new Music and Sound objects
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    /**
     *  Class constructor given an Activity
     * @param activity
     */
    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    /**
     * Create a new Music object with a given audio file
     * @param filename
     * @return Music object
     */
    @Override
    public Music createMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return AndroidMusic.getInstance(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    /**
     * Create a new Sound object with a given audio file
     * @param filename
     * @return Sound object
     */
    @Override
    public Sound createSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}
