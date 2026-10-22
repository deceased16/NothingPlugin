/**
 * All languages the plugin can talk in.
 *
 * Want to add your own? Copy one of the lines below, change the code and
 * the two messages, done. Then put the new code in config.yml.
 * No other code needs touching.
 */

package ru.deceased16.NothingPlugin;
import java.util.Locale;

public enum Language {

    EN("en",
            "Plugin enabled. Doing absolutely nothing.",
            "Plugin disabled. Still did nothing."),

    RU("ru",
            "Плагин включён. Абсолютно ничего не делаю.",
            "Плагин выключен. Всё равно ничего не делал.");

    // New languages go above this line. Mind the comma after the previous one!

    // Fallback when the config has something we don't know.
    public static final Language DEFAULT = EN;

    private final String code;
    private final String enableMessage;
    private final String disableMessage;

    Language(String code, String enableMessage, String disableMessage) {
        this.code = code;
        this.enableMessage = enableMessage;
        this.disableMessage = disableMessage;
    }

    public String getCode() {
        return code;
    }

    public String getEnableMessage() {
        return enableMessage;
    }

    public String getDisableMessage() {
        return disableMessage;
    }

    /**
     * Looks up a language by code. Forgiving about the format:
     * "ru", "RU", "ru_RU" and "ru-ru" all work.
     *
     * @return the language, or null if we don't have it
     */

    public static Language fromCode(String input) {
        if (input == null) {
            return null;
        }
        String normalized = input.trim().toLowerCase(Locale.ROOT);

        // cut off the region part: ru_RU -> ru
        int separator = normalized.indexOf('_');
        if (separator < 0) {
            separator = normalized.indexOf('-');
        }
        if (separator > 0) {
            normalized = normalized.substring(0, separator);
        }

        for (Language language : values()) {
            if (language.code.equals(normalized)) {
                return language;
            }
        }
        return null;
    }

    // Handy for error messages: "en, ru".
    public static String supportedCodes() {
        StringBuilder sb = new StringBuilder();
        for (Language language : values()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(language.code);
        }
        return sb.toString();
    }
}