package uk.ac.uea.framework.implementation;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import uk.ac.uea.framework.Graphics;
import uk.ac.uea.framework.Image;

/**
 * Manages generating graphics
 */
public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    /**
     * Class Constructor
     * @param assets
     * @param frameBuffer
     */
    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    /**
     * Load a new image from file
     * @param fileName
     * @param format
     * @return Image
     */
    @Override
    public Image newImage(String fileName, ImageFormat format) {
        Config config = null;
        if (format == ImageFormat.RGB565)
            config = Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);
    }

    /**
     * Clears the screen
     * @param color
     */
    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    /**
     * Draw a line with given parameters
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param color
     */
    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    /**
     * Draw a rectangle with given parameters
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    /**
     * Draw ARGB
     * @param a
     * @param r
     * @param g
     * @param b
     */
    @Override
    public void drawARGB(int a, int r, int g, int b) {
        paint.setStyle(Style.FILL);
        canvas.drawARGB(a, r, g, b);
    }

    /**
     * Draw Text
     * @param text
     * @param x
     * @param y
     * @param paint
     */
    @Override
    public void drawString(String text, int x, int y, Paint paint) {
        canvas.drawText(text, x, y, paint);
    }

    /**
     * Draw an image
     * @param Image
     * @param x
     * @param y
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public void drawImage(Image Image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect, null);
    }

    /**
     * Draw an image
     * @param Image
     * @param x
     * @param y
     */
    @Override
    public void drawImage(Image Image, int x, int y) {
        canvas.drawBitmap(((AndroidImage) Image).bitmap, x, y, null);
    }

    /**
     * Draw an image to a given width and height
     * @param Image
     * @param x
     * @param y
     * @param width
     * @param height
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public void drawScaledImage(Image Image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
        srcRect.left=srcX;
        srcRect.top=srcY;
        srcRect.right=srcX+srcWidth;
        srcRect.bottom=srcY+srcHeight;

        dstRect.left=x;
        dstRect.top=y;
        dstRect.right=x+width;
        dstRect.bottom=y+height;

        canvas.drawBitmap(((AndroidImage)Image).bitmap,srcRect,dstRect,null);
    }

    /**
     * Get width of Bitmap
     * @return int width
     */
    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    /**
     * get height of Bitmap
     * @return int height
     */
    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
