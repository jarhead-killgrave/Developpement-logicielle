package observer;

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
public interface Listenable {

    /**
     * Adds a listener to the Listenable object.
     *
     *
     * @param operation The operation that can be listened.
     * @param listener  The listener to add.
     * @throws IllegalArgumentException If the operation is not supported.
     * @throws NullPointerException     If the listener is null.
     * @throws NullPointerException     If the operation is null.
     */
    void addListener(String operation, Listener listener);

    /**
     * Adds a listener to the Listenable object.
     *
     * @param listener The listener to add.
     */
    void addListener(Listener listener);

    /**
     * Removes a listener from the Listenable object.
     *
     * @param operation The operation that can be listened.
     * @param listener  The listener to remove.
     * @throws IllegalArgumentException If the operation is not supported.
     * @throws NullPointerException     If the listener is null.
     * @throws NullPointerException     If the operation is null.
     */
    void removeListener(String operation, Listener listener);

    /**
     * Removes a listener from the Listenable object.
     *
     * @param listener The listener to remove.
     */
    void removeListener(Listener listener);




}
