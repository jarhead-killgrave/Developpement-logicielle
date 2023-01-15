package observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>
 *     A Listenable object
 * </b>
 *
 * <p>
 *     A Listenable object is an object that can be listened.
 * </p>
 *
 * @version	1.0
 */

public abstract class ListenableObject implements Listenable{

    /**
    * The listeners of the Listenable object.
    */
    private final Map<String, List<Listener>> listeners;

    /**
     * Protected constructor.
     * @param operations The operations that can be listened.
     */
    protected ListenableObject(String... operations) {
        this.listeners = new HashMap<>();
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    @Override
    public void addListener(String operation, Listener listener) {
        if (operation == null) {
            throw new NullPointerException("The operation cannot be null.");
        }
        if (listener == null) {
            throw new NullPointerException("The listener cannot be null.");
        }
        if (!this.listeners.containsKey(operation)) {
            throw new IllegalArgumentException("The operation " + operation + " is not supported by " + this.getClass().getSimpleName());
        }
        this.listeners.get(operation).add(listener);
    }

    @Override
    public void addListener(Listener listener) {
        if (listener == null) {
            throw new NullPointerException("The listener cannot be null.");
        }
        for (Map.Entry<String, List<Listener>> entry : this.listeners.entrySet()) {
            entry.getValue().add(listener);
        }
    }

    @Override
    public void removeListener(String operation, Listener listener) {
        if (operation == null) {
            throw new NullPointerException("The operation cannot be null.");
        }
        if (listener == null) {
            throw new NullPointerException("The listener cannot be null.");
        }
        if (!this.listeners.containsKey(operation)) {
            throw new IllegalArgumentException("The operation " + operation + " is not supported by " + this.getClass().getSimpleName());
        }
        this.listeners.get(operation).remove(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        if (listener == null) {
            throw new NullPointerException("The listener cannot be null.");
        }
        for (Map.Entry<String, List<Listener>> entry : this.listeners.entrySet()) {
            entry.getValue().remove(listener);
        }
    }

    /**
     * Add a new operation to the Listenable object.
     *
     * @param operation The operation to add.
     */
    protected void addOperation(String... operation) {
        for (String op : operation) {
            this.listeners.put(op, new ArrayList<>());
        }
    }



    /**
     * Notifies the listeners of the Listenable object.
     * @param operation The operation that occurs.
     * @param data      The data of the event.
     * @throws IllegalArgumentException If the operation is not supported.
     * @throws NullPointerException     If the operation is null.
     */
    protected void notifyListeners(String operation, Object... data) {
        if (operation == null) {
            throw new NullPointerException("The operation cannot be null.");
        }
        if (!this.listeners.containsKey(operation)) {
            throw new IllegalArgumentException("The operation " + operation + " is not supported by " + this.getClass().getSimpleName());
        }
        for (Listener listener : this.listeners.get(operation)) {
            listener.notify(this, operation, data);
        }
    }



}
