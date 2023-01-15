package observer;

/**
 * <b>
 *     A listener of an event.
 * </b>
 *
 * <p>
 *     A listener of an event is an object that can be notified when an event occurs.
 *     The listener can be notified when the event occurs.
 * </p>
 *
 * @version	1.0
 */
public interface Listener {

    /**
     * Notifies the listener when the event occurs.
     *
     * @param operation The operation that occurs.
     * @param source    The source of the event.
     * @param data      The data of the event.
     * @throws IllegalArgumentException If the operation is not supported.
     * @throws NullPointerException     If the source is null.
     * @throws NullPointerException     If the operation is null.
     */
    void notify(Listenable source, String operation, Object... data);
}
