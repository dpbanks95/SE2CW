package uk.ac.uea.framework.implementation;

/**
 * Created by ybm14yju on 21/10/2016.
 */

public abstract class Originator {
    protected MementoState state;

    public final void setState(MementoState state){
        this.state = state;
    }

    public final Memento saveToMemento(){
        return new Memento(state);
    }

    public final void restoreFromMemento(Memento m){
        state = m.getState();
    }
}
