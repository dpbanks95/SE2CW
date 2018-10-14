package uk.ac.uea.framework;

import java.util.ArrayList;

/**
 * Container class for Memento class instance
 */
public class CareTaker {
    private ArrayList<Memento> savedStates = new ArrayList<>();

    /**
     * Add a Memento to the list
     * @param m
     */
    public void addMemento(Memento m){
        savedStates.add(m);
    }

    /**
     * Get a Memento from the list
     * @param index
     * @return Memento
     */
    public Memento getMemento(int index){
        return savedStates.get(index);
    }
}
