package uk.ac.uea.framework.implementation;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;

import uk.ac.uea.framework.Music;

/**
 * Manage loading and use of music
 */
public class AndroidMusic implements Music, OnCompletionListener, OnSeekCompleteListener,
        OnPreparedListener, OnVideoSizeChangedListener {
    MediaPlayer mediaPlayer;
    boolean isPrepared = false;
    private static AndroidMusic instance;

    /**
     * Class constructor
     * @param assetDescriptor
     */
    private AndroidMusic(AssetFileDescriptor assetDescriptor) {
        load(assetDescriptor);
    }

    /**
     * Load music
     * @param assetDescriptor
     */
    private void load(AssetFileDescriptor assetDescriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),
                    assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    /**
     * Get the instance of AndroidMusic
     * @param assetDescriptor
     * @return AndroidMusic
     */
    public static AndroidMusic getInstance(AssetFileDescriptor assetDescriptor) {
        if (instance == null) {
            instance = new AndroidMusic(assetDescriptor);
        } else {
            instance.stop();
            instance.load(assetDescriptor);
        }
        return instance;
    }

    /**
     * dispose MediaPlayer
     */
    @Override
    public void dispose() {

        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();
        }
        this.mediaPlayer.release();
    }

    /**
     * Check if music is looping
     * @return boolean
     */
    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    /**
     * Check if music is playing
     * @return boolean
     */
    @Override
    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }

    /**
     * Check if music is stopped
     * @return boolean
     */
    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    /**
     * Pause the music
     */
    @Override
    public void pause() {
        if (this.mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    /**
     * play the music
     */
    @Override
    public void play() {
        if (this.mediaPlayer.isPlaying())
            return;

        try {
            synchronized (this) {
                if (!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set music to loop
     * @param isLooping
     */
    @Override
    public void setLooping(boolean isLooping) {
        mediaPlayer.setLooping(isLooping);
    }

    /**
     * Set volume of music
     * @param volume
     */
    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    /**
     * Stop music
     */
    @Override
    public void stop() {
        if (this.mediaPlayer.isPlaying() == true) {
            this.mediaPlayer.stop();

            synchronized (this) {
                isPrepared = false;
            }
        }
    }

    /**
     * Set prepared to false
     * @param player
     */
    @Override
    public void onCompletion(MediaPlayer player) {
        synchronized (this) {
            isPrepared = false;
        }
    }

    /**
     * Seek to 0
     */
    @Override
    public void seekBegin() {
        mediaPlayer.seekTo(0);

    }

    /**
     * Set prepared to true
     * @param player
     */
    @Override
    public void onPrepared(MediaPlayer player) {
        // TODO Auto-generated method stub
        synchronized (this) {
            isPrepared = true;
        }

    }

    /**
     * On seek complete
     * @param player
     */
    @Override
    public void onSeekComplete(MediaPlayer player) {
        // TODO Auto-generated method stub
    }

    /**
     * On Video size changed
     * @param player
     * @param width
     * @param height
     */
    @Override
    public void onVideoSizeChanged(MediaPlayer player, int width, int height) {
        // TODO Auto-generated method stub
    }
}
