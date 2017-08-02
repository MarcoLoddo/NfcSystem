package it.extrasys.tesi.tagsystem.corner_service.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Helper to simplify accessing i18n messages in code.
 *
 * This finds messages automatically found from src/main/resources (files named
 * messages_*.properties)
 *
 * This example uses hard-coded English locale.
 *
 * @author Joni Karppinen
 * @since 2015-11-02
 */
@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        this.accessor = new MessageSourceAccessor(this.messageSource,
                LocaleContextHolder.getLocale());
    }

    /**
     * Gets the messages.
     *
     * @param key
     *            the key
     * @return the messages
     */
    public String getMessages(String key) {
        return this.accessor.getMessage(key);
    }

}
