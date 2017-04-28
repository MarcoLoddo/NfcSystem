package it.extrasys.tesi.tagsystem.user_web.events;

/**
 * The Interface LoadNewTabInterface event.
 */
public interface LoadNewTabInterfacePublisher {

    /**
     * Adds the listener.
     *
     * @param caller
     *            the caller
     */
    void addListener(LoadNewTabEventInterface caller);

}
