package team.bytephoria.extractorpickaxe.service;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import team.bytephoria.extractorpickaxe.extractor.ExtractorKeys;
import team.bytephoria.extractorpickaxe.extractor.ExtractorUseResult;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Service class that manages Extractor items and their custom data.
 * Provides methods to create, convert, and update extractor items.
 */
public final class ExtractorService {

    private static final PersistentDataType<Byte, Byte> DATA_TYPE = PersistentDataType.BYTE;

    /**
     * Adds a new extractor item into the given inventory.
     */
    public void addExtractorItem(final @NotNull Inventory inventory) {
        inventory.addItem(this.createExtractorItem());
    }

    /**
     * Creates a new extractor item with default NBT data.
     */
    public @NotNull ItemStack createExtractorItem() {
        final ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
        item.editMeta(meta -> setExtractorFlag(meta, true));
        return item;
    }

    /**
     * Converts a normal item into an extractor.
     *
     * @return true if conversion was successful, false if item was already an extractor.
     */
    public boolean convertToExtractor(final @NotNull ItemStack item) {
        return this.modifyItemMeta(item, meta -> !this.hasExtractorFlag(meta),
                meta -> this.setExtractorFlag(meta, true));
    }

    /**
     * Sets the remaining uses of an extractor item.
     *
     * @return true if operation succeeded, false if item is not an extractor or amount < 1.
     */
    public boolean setExtractorUses(final @NotNull ItemStack itemStack, final int amount) {
        if (amount < 1) {
            return false;
        }

        return this.modifyItemMeta(itemStack, this::hasExtractorFlag,
                meta -> meta.getPersistentDataContainer()
                        .set(ExtractorKeys.EXTRACTOR_USES_NAMESPACED_KEY, PersistentDataType.INTEGER, amount));
    }

    /**
     * Decrements the remaining uses of an extractor item by 1.
     */
    public @NotNull ExtractorUseResult decrementExtractorUses(final @NotNull ItemStack item) {
        return this.decrementExtractorUses(item, 1);
    }

    /**
     * Decrements the remaining uses of an extractor item by a specified amount.
     */
    public @NotNull ExtractorUseResult decrementExtractorUses(final @NotNull ItemStack itemStack, final int amount) {
        final ItemMeta meta = itemStack.getItemMeta();
        if (!this.hasExtractorFlag(meta)) {
            return ExtractorUseResult.NOT_EXTRACTOR;
        }

        final PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        final int uses = persistentDataContainer.getOrDefault(ExtractorKeys.EXTRACTOR_USES_NAMESPACED_KEY, PersistentDataType.INTEGER, 0);

        if (uses > 0) {
            if (uses <= amount) {
                itemStack.setAmount(0);
                return ExtractorUseResult.CONSUMED;
            }

            persistentDataContainer.set(ExtractorKeys.EXTRACTOR_USES_NAMESPACED_KEY, PersistentDataType.INTEGER, uses - amount);
            itemStack.setItemMeta(meta);
            return ExtractorUseResult.SUCCESS;
        }

        return ExtractorUseResult.UNLIMITED_USES;
    }

    private boolean hasExtractorFlag(final @NotNull ItemMeta meta) {
        return meta.getPersistentDataContainer().has(ExtractorKeys.EXTRACTOR_FLAG_NAMESPACED_KEY, DATA_TYPE);
    }

    private void setExtractorFlag(final @NotNull ItemMeta meta, final boolean value) {
        meta.getPersistentDataContainer().set(ExtractorKeys.EXTRACTOR_FLAG_NAMESPACED_KEY, DATA_TYPE, (byte) 1);
    }

    private boolean modifyItemMeta(final @NotNull ItemStack item, final @NotNull Predicate<ItemMeta> condition, final @NotNull Consumer<ItemMeta> action) {
        final ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null && condition.test(itemMeta)) {
            action.accept(itemMeta);
            item.setItemMeta(itemMeta);
            return true;
        }

        return false;
    }
}
