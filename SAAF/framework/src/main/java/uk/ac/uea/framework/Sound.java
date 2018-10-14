package uk.ac.uea.framework;

/**
 * Interface for managing a sound
 */
public interface Sound {
    /**
     * play the sound
     * @param float volume
     */
    public void play(float volume);

    /**
     * Stop the sound
     */
    public void stop();

    /**
     * Dispose of the sound
     */
    public void dispose();
}
