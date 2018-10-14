package uk.ac.uea.framework;

import android.app.Activity;

import uk.ac.uea.framework.implementation.AndroidAudio;

/**
 * Class for managing a SoundResource
 */
public class SoundResource {
    private Audio myAudio;
    private Sound mySound;

    /**
     * Class constructor
     * @param Activity act
     */
    public SoundResource(Activity act){
        myAudio = new AndroidAudio(act);
    }

    /**
     * Load a sound
     * @param String resourcePath
     */
    public void load(String resourcePath){
        mySound = myAudio.createSound(resourcePath);
    }

    /**
     * Play a sound
     */
    public void play(){
        mySound.play((float)0.9);
    }

    /**
     * Stop a sound
     */
    public void stop(){
        mySound.stop();
    }

}
