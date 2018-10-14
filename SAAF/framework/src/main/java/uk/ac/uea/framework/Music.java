package uk.ac.uea.framework;

/**
 * interface for managing music
 */
public interface Music {
    /**
     * play the music
     */
    public void play();

    /**
     * Stop music
     */
    public void stop();

    /**
     * Pause the music
     */
    public void pause();

    /**
     * Set music to loop
     * @param looping
     */
    public void setLooping(boolean looping);

    /**
     * Set volume of music
     * @param volume
     */
    public void setVolume(float volume);

    /**
     * Check if music is playing
     * @return boolean
     */
    public boolean isPlaying();

    /**
     * Check if music is stopped
     * @return boolean
     */
    public boolean isStopped();

    /**
     * Check if music is looping
     * @return boolean
     */
    public boolean isLooping();

    /**
     * dispose MediaPlayer
     */
    public void dispose();

    /**
     * Seek to 0
     */
    void seekBegin();
}