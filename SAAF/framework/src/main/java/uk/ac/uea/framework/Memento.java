package uk.ac.uea.framework;

/**
 * Memento Pattern implementation
 */
public class Memento {
    private  MementoState state;

    /**
     * Class constructor
     * @param newState
     */
    public Memento(MementoState newState){
        state = newState;
    }

    /**
     * Get the state of the memento
     * @return MementoState
     */
    public MementoState getState(){
        return state;
    }
}
