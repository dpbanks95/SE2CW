package uk.ac.uea.framework;

/**
 * interface for creating music and sounds
 */
public interface Audio {
    /**
     * Create music object
     * @param file
     * @return Music
     */
    public Music createMusic(String file);

    /**
     * Create sound object
     * @param file
     * @return Sound
     */
    public Sound createSound(String file);
}
