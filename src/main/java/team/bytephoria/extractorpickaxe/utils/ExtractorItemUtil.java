package team.bytephoria.extractorpickaxe.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for working with ItemStacks related to Extractor Pickaxe plugin.
 * <p>
 * Provides methods to:
 * <ul>
 *     <li>Create spawner ItemStacks from existing {@link CreatureSpawner} data or blocks.</li>
 *     <li>Check if a {@link Material} is a pickaxe.</li>
 * </ul>
 */
public final class ExtractorItemUtil {

    private ExtractorItemUtil() {
        throw new UnsupportedOperationException("ExtractorItemUtil is a utility class and cannot be instantiated.");
    }

    /**
     * Creates a spawner ItemStack by copying all relevant properties from an existing {@link CreatureSpawner}.
     *
     * @param targetSpawner The source CreatureSpawner.
     * @return An ItemStack representing a spawner with the same configuration.
     */
    public static @NotNull ItemStack createSpawner(final @NotNull CreatureSpawner targetSpawner) {
        final ItemStack itemStack = new ItemStack(Material.SPAWNER, 1);
        final BlockStateMeta itemMeta = (BlockStateMeta) itemStack.getItemMeta();
        final CreatureSpawner creatureSpawner = (CreatureSpawner) itemMeta.getBlockState();

        creatureSpawner.setSpawnedType(targetSpawner.getSpawnedType());
        creatureSpawner.setDelay(targetSpawner.getDelay());
        creatureSpawner.setMaxNearbyEntities(targetSpawner.getMaxNearbyEntities());
        creatureSpawner.setRequiredPlayerRange(targetSpawner.getRequiredPlayerRange());
        creatureSpawner.setSpawnCount(targetSpawner.getSpawnCount());
        creatureSpawner.setMaxSpawnDelay(targetSpawner.getMaxSpawnDelay());
        creatureSpawner.setMinSpawnDelay(targetSpawner.getMinSpawnDelay());
        creatureSpawner.setSpawnRange(targetSpawner.getSpawnRange());

        itemMeta.setBlockState(creatureSpawner);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * Creates a spawner ItemStack from a spawner block in the world.
     *
     * @param block The block in the world.
     * @return An ItemStack representing the spawner.
     * @throws IllegalArgumentException if the block is not a spawner.
     */
    public static @NotNull ItemStack createSpawnerFromBlock(final @NotNull Block block) {
        if (block.getType() != Material.SPAWNER) {
            throw new IllegalArgumentException("Expected a spawner block, but got: " + block.getType());
        }

        return createSpawner((CreatureSpawner) block.getState());
    }

    /**
     * Checks whether a Material is any type of pickaxe.
     *
     * @param material The material to check.
     * @return True if the material is a pickaxe.
     */
    public static boolean isPickaxe(final @NotNull Material material) {
        return switch (material) {
            case WOODEN_PICKAXE, STONE_PICKAXE, IRON_PICKAXE,
                 GOLDEN_PICKAXE, DIAMOND_PICKAXE, NETHERITE_PICKAXE -> true;
            default -> false;
        };
    }
}
