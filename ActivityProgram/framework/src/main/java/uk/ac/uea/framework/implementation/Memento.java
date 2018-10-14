package uk.ac.uea.framework.implementation;

/**
 * Created by ybm14yju on 21/10/2016.
 */

public class Memento {
    private MementoState state;

    public Memento(MementoState newState){
        state = newState;
    }

    public MementoState getState(){
        return state;
    }
}
