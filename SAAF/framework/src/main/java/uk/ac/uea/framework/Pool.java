package uk.ac.uea.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for managing a Pool of objects
 * @param <T>
 */
public class Pool<T> {
    /**
     * Interface for PoolObjectFactory
     * @param <T>
     */
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    /**
     * Class constructor
     * @param factory
     * @param maxSize
     */
    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    /**
     * Create an object
     * @return T
     */
    public T newObject() {
        T object = null;

        if (freeObjects.size() == 0)
            object = factory.createObject();
        else
            object = freeObjects.remove(freeObjects.size() - 1);

        return object;
    }

    /**
     * Add an object to freeObjects list
     * @param object
     */
    public void free(T object) {
        if (freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
}
