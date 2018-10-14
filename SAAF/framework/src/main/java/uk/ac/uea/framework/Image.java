package uk.ac.uea.framework;

import uk.ac.uea.framework.Graphics.ImageFormat;

/**
 * Interface for Image management
 */
public interface Image {
    /**
     * Get width of Bitmap
     * @return int width
     */
    public int getWidth();

    /**
     * Get height of Bitmap
     * @return int height
     */
    public int getHeight();

    /**
     * Get the format of the image
     * @return ImageFormat
     */
    public ImageFormat getFormat();

    /**
     * Dispose of an image
     */
    public void dispose();
}
