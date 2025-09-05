package team.bytephoria.extractorpickaxe.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.bytephoria.extractorpickaxe.utils.exception.NonInstantiableClassException;

/**
 * Utility class for converting legacy-style strings (using '&' for colors) into Adventure Components.
 */
public final class LegacyComponentUtil {

    /**
     * Private constructor to prevent instantiation.
     * Throws an exception if someone tries to instantiate the class.
     */
    private LegacyComponentUtil() {
        throw new NonInstantiableClassException();
    }

    /**
     * Converts a legacy string (with '&' color codes) into an Adventure Component.
     *
     * @param message the legacy string to convert (may be null or empty)
     * @return a Component representing the formatted message,
     *         or an empty Component if the message is null or empty
     */
    public static Component asComponent(final @Nullable String message) {
        return message == null || message.isEmpty() ? Component.empty() : getSerializer().deserialize(message);
    }

    /**
     * Provides the serializer for legacy strings using the '&' character as the color code indicator.
     *
     * @return the configured LegacyComponentSerializer
     */
    @Contract(pure = true)
    public static @NotNull ComponentSerializer<Component, TextComponent, String> getSerializer() {
        return LegacyComponentSerializer.legacyAmpersand();
    }
}
