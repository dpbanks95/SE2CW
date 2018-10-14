package uk.ac.uea.framework;

import uk.ac.uea.framework.implementation.AndroidAudio;
import android.app.Activity;

/**
 * Created by ybm14yju on 14/10/2016.
 */

/**
 * A reusable class that is handles a sound resource in a generic manner.
 */
public class SoundResource {
    private Audio myAudio;
    private Sound mySound;

    public SoundResource(Activity act){
        myAudio = new AndroidAudio(act);
    }

    /**
     * Loads in a sound resource
     * @param resourcePath The path to the sound resource from the root directory e.g (/assets/click.mp3)
     */
    public void load(String resourcePath){
        mySound = myAudio.createSound(resourcePath);
    }

    /**
     * Plays the sound resource
     */
    public void play(){
        mySound.play((float)0.9);
    }

    /**
     * Stops the sound resource from playing
     */
    public void stop(){
        mySound.stop();
    }
}
