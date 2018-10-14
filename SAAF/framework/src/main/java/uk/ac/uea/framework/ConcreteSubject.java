package uk.ac.uea.framework;

/**
 * Observer pattern ConcreteSubject
 * @param <T>
 */
public final class ConcreteSubject<T> extends Subject {
    private T State;

    /**
     * Get the state of the Subject
     * @return generic T
     */
    public T GetState() {
        return State;
    }

    /**
     * St state of the subject
     * @param newState
     */
    public void SetState(T newState) {
        State = newState;
        this.Notify();
    }
}