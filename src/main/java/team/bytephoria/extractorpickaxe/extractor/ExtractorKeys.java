package team.bytephoria.extractorpickaxe.extractor;

import org.bukkit.NamespacedKey;

/**
 * Utility class that defines constant keys for storing and retrieving
 * custom metadata from {@link org.bukkit.inventory.ItemStack} objects.
 * <p>
 * These keys are intended for use in NBT tags or PersistentDataContainers.
 * <br>
 * This class is not meant to be instantiated.
 */
public final class ExtractorKeys {

    // Prevent instantiation
    private ExtractorKeys() {
        throw new UnsupportedOperationException("KeyConstants is a utility class and cannot be instantiated.");
    }

    /**
     * Key used to mark whether an {@link org.bukkit.inventory.ItemStack} is an extractor.
     * <p>
     * Expected value type: {@code boolean}.
     */
    public static final String EXTRACTOR_FLAG_KEY = "extractor";

    /**
     * Key used to store the remaining number of uses for an extractor {@link org.bukkit.inventory.ItemStack}.
     * <p>
     * Expected value type: {@code int}.
     */
    public static final String EXTRACTOR_USES_KEY = "extractor_uses";

    /**
     * Plugin namespace used for creating {@link NamespacedKey} objects.
     */
    public static final String PLUGIN_NAMESPACE = "extractorpickaxe";

    /**
     * NamespacedKey for marking an item as an extractor.
     */
    public static final NamespacedKey EXTRACTOR_FLAG_NAMESPACED_KEY =
            new NamespacedKey(PLUGIN_NAMESPACE, EXTRACTOR_FLAG_KEY);

    /**
     * NamespacedKey for storing the remaining uses of an extractor.
     */
    public static final NamespacedKey EXTRACTOR_USES_NAMESPACED_KEY =
            new NamespacedKey(PLUGIN_NAMESPACE, EXTRACTOR_USES_KEY);
}