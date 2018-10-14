package uk.ac.uea.framework.implementation;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import uk.ac.uea.framework.Input;

/**
 * Manage handling of user touch inputs
 */
public class AndroidInput implements Input {    
    TouchHandler touchHandler;

    /**
     * Class constructor
     * @param context
     * @param view
     * @param scaleX
     * @param scaleY
     */
    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        if(Integer.parseInt(VERSION.SDK) < 5) 
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);        
    }

    /**
     * Check if user is touching the screen
     * @param pointer
     * @return boolean
     */
    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    /**
     * Get x coordinate of user touch
     * @param pointer
     * @return int
     */
    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    /**
     * get y coordinate of user touch
     * @param pointer
     * @return int
     */
    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    /**
     * Get a list of touch events
     * @return List<TouchEvent>
     */
    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
    
}
