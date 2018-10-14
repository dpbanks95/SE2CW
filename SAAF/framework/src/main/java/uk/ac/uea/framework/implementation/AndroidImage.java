package uk.ac.uea.framework.implementation;

import android.graphics.Bitmap;

import uk.ac.uea.framework.Image;
import uk.ac.uea.framework.Graphics.ImageFormat;

/**
 * Manage generatibg an image
 */
public class AndroidImage implements Image {
    Bitmap bitmap;
    ImageFormat format;

    /**
     * Class constructor
     * @param bitmap
     * @param format
     */
    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    /**
     * Get width of Bitmap
     * @return int width
     */
    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    /**
     * Get height of Bitmap
     * @return int height
     */
    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    /**
     * Get the format of the image
     * @return ImageFormat
     */
    @Override
    public ImageFormat getFormat() {
        return format;
    }

    /**
     * Dispose of an image
     */
    @Override
    public void dispose() {
        bitmap.recycle();
    }      
}
