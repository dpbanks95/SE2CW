package uk.ac.uea.framework;

import java.util.List;

/**
 * Interface for managing user input
 */
public interface Input {

    /**
     * Nested class for touch event
     */
    public static class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;

        public int type;
        public int x, y;
        public int pointer;
    }

    /**
     * Check if user is touching the screen
     * @param pointer
     * @return boolean
     */
    public boolean isTouchDown(int pointer);

    /**
     * Get x coordinate of user touch
     * @param pointer
     * @return int
     */
    public int getTouchX(int pointer);

    /**
     * get y coordinate of user touch
     * @param pointer
     * @return int
     */
    public int getTouchY(int pointer);

    /**
     * Get a list of touch events
     * @return List<TouchEvent>
     */
    public List<TouchEvent> getTouchEvents();
}
