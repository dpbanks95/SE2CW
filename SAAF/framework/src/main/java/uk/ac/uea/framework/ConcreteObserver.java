package uk.ac.uea.framework;

import java.util.*;

/**
 * Observer pattern ConcreteObserver
 * @param <T>
 */
public final class ConcreteObserver<T> extends Observer {
    private ConcreteSubject<T> MySubject;
    private ObserverClient<T> MyClient;

    /**
     * Class constructor
     * @param Subject
     * @param Client
     */
    public ConcreteObserver(ConcreteSubject<T> Subject, ObserverClient<T> Client) {
        MySubject = Subject;
        MySubject.Attach(this);
        MyClient = Client;
    }

    /**
     * Detach ConcreteSubject
     */
    public void Detach() {
        MySubject.Detach(this);
    }

    /**
     * Update ConcreteSubject
     */
    @Override
    public void Update() {
        MyClient.Update(MySubject.GetState());
    }
}
