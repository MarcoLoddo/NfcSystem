package it.extrasys.tesi.tagsystem.meal_app.ui.components;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Externalizer.
 */
public class Externalizer {

    /** The map. */
    static Map<String, String> map;

    /**
     * Adds the string.
     *
     * @param key
     *            the key
     * @param string
     *            the string
     */
    public static void addString(String key, String string) {
        if (map == null) {
            map = new HashMap<String, String>();
        }
        map.put(key, string);
    }

    /**
     * Gets the string.
     *
     * @param key
     *            the key
     * @return the string
     */
    public static String getString(String key) {
        return map.get(key);
    }
}
