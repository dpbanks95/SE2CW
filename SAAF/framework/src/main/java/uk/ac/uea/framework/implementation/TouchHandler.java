package uk.ac.uea.framework.implementation;

import java.util.List;

import android.view.View.OnTouchListener;

import uk.ac.uea.framework.Input.TouchEvent;

/**
 * interface for handling touch
 */
public interface TouchHandler extends OnTouchListener {
    /**
     * Check if user is touching the screen
     * @param pointer
     * @return boolean
     */
    public boolean isTouchDown(int pointer);

    /**
     * get x coordinate of touch event
     * @param pointer
     * @return int
     */
    public int getTouchX(int pointer);

    /**
     * get y coordinate of touch event
     * @param pointer
     * @return int
     */
    public int getTouchY(int pointer);

    /**
     * Get list of touch events
     * @return List<TouchEvent>
     */
    public List<TouchEvent> getTouchEvents();
}
