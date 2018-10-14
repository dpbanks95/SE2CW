package uk.ac.uea.framework.implementation;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import uk.ac.uea.framework.Pool;
import uk.ac.uea.framework.Input.TouchEvent;
import uk.ac.uea.framework.Pool.PoolObjectFactory;

/**
 * Handler for single touch
 */
public class SingleTouchHandler implements TouchHandler {
    boolean isTouched;
    int touchX;
    int touchY;
    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
    float scaleX;
    float scaleY;

    /**
     * Class constructor
     * @param view
     * @param scaleX
     * @param scaleY
     */
    public SingleTouchHandler(View view, float scaleX, float scaleY) {
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }            
        };
        touchEventPool = new Pool<TouchEvent>(factory, 100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * Handle touch event
     * @param v
     * @param event
     * @return boolean
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized(this) {
            TouchEvent touchEvent = touchEventPool.newObject();
            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchEvent.type = TouchEvent.TOUCH_DOWN;
                isTouched = true;
                break;
            case MotionEvent.ACTION_MOVE:
                touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                isTouched = true;
                break;
            case MotionEvent.ACTION_CANCEL:                
            case MotionEvent.ACTION_UP:
                touchEvent.type = TouchEvent.TOUCH_UP;
                isTouched = false;
                break;
            }
            
            touchEvent.x = touchX = (int)(event.getX() * scaleX);
            touchEvent.y = touchY = (int)(event.getY() * scaleY);
            touchEventsBuffer.add(touchEvent);                        
            
            return true;
        }
    }

    /**
     * Check if user is touching the screen
     * @param pointer
     * @return boolean
     */
    @Override
    public boolean isTouchDown(int pointer) {
        synchronized(this) {
            if(pointer == 0)
                return isTouched;
            else
                return false;
        }
    }

    /**
     * Get x coordinate of touch event
     * @param pointer
     * @return int
     */
    @Override
    public int getTouchX(int pointer) {
        synchronized(this) {
            return touchX;
        }
    }

    /**
     * Get y coordinate of touch event
     * @param pointer
     * @return int
     */
    @Override
    public int getTouchY(int pointer) {
        synchronized(this) {
            return touchY;
        }
    }

    /**
     * Get list of Touch events
     * @return List<TouchEvent>
     */
    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized(this) {     
            int len = touchEvents.size();
            for( int i = 0; i < len; i++ )
                touchEventPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }
}
