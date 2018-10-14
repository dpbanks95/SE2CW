package uk.ac.uea.framework;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Observer;

/**
 * Subject for objerver pattern implementation
 */
public abstract class Subject {
    private HashSet<Observer> observers = new HashSet<Observer>();

    /**
     * Attach an observer to the observers list
     * @param Observer obs
     */
    public final void Attach(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    /**
     * Remove an observer from the obeservers list
     * @param Observer obs
     */
    public final void Detach(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    /**
     * Notify observers
     */
    protected final void Notify() {
        Iterator<Observer> ObIt = observers.iterator();
        while (ObIt.hasNext()) {
            ObIt.next().Update();
        }
    }
}