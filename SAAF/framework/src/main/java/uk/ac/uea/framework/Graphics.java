package uk.ac.uea.framework;

import android.graphics.Paint;

/**
 * Interface for managing Graphics
 */
public interface Graphics {
    /**
     * Enum for storing image format types
     */
	public static enum ImageFormat {
        ARGB8888, ARGB4444, RGB565
    }

    /**
     * Load a new image
     * @param fileName
     * @param format
     * @return Image
     */
    public Image newImage(String fileName, ImageFormat format);

    /**
     * Clear the screen
     * @param color
     */
    public void clearScreen(int color);

    /**
     * Draw a line with given parameters
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param color
     */
    public void drawLine(int x, int y, int x2, int y2, int color);

    /**
     * Draw a rectangle with given parameters
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public void drawRect(int x, int y, int width, int height, int color);

    /**
     * Draw an image
     * @param  image
     * @param x
     * @param y
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public void drawImage(Image image, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight);

    /**
     * Draw an image
     * @param Image
     * @param x
     * @param y
     */
    public void drawImage(Image Image, int x, int y);

    /**
     * Draw Text
     * @param text
     * @param x
     * @param y
     * @param paint
     */
    void drawString(String text, int x, int y, Paint paint);

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
     * Draw ARGB
     * @param i
     * @param j
     * @param k
     * @param l
     */
    public void drawARGB(int i, int j, int k, int l);
}
