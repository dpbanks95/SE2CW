package uk.ac.uea.framework;

/**
 * Class to forward the updated state information
 * @param <T>
 */
public interface ObserverClient<T> {
    /**
     * Update state
     * @param StateUpdate
     */
    public void Update(T StateUpdate);
}
