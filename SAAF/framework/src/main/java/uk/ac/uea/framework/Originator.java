package uk.ac.uea.framework;

/**
 *  Class that has a state worth saving using the Memento pattern
 */
public abstract class Originator {
    protected MementoState state;

    /**
     * Set the state of this
     * @param state
     */
    public final void setState(MementoState state){
        this.state = state;
    }

    /**
     * Save state to memento
     * @return Memento
     */
    public final Memento saveToMemento(){
        return new Memento(state);
    }

    /**
     * Restore state from memento
     * @param m
     */
    public final void restoreFromMemento(Memento m){
        state = m.getState();
    }
}
