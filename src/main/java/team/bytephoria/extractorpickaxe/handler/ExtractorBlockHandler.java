package team.bytephoria.extractorpickaxe.handler;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.bytephoria.extractorpickaxe.extractor.ExtractorUseResult;
import team.bytephoria.extractorpickaxe.service.ExtractorService;
import team.bytephoria.extractorpickaxe.utils.ExtractorItemUtil;

public final class ExtractorBlockHandler {

    private final ExtractorService extractorService;
    public ExtractorBlockHandler(ExtractorService extractorService) {
        this.extractorService = extractorService;
    }

    public void handle(final @NotNull Player player, final @NotNull Block block) {
        final ItemStack itemStack = player.getEquipment().getItemInMainHand();
        if (!ExtractorItemUtil.isPickaxe(itemStack.getType())) {
            return;
        }

        if (block.getType() != Material.SPAWNER) {
            return;
        }

        final ExtractorUseResult extractorUseResult = this.extractorService.decrementExtractorUses(itemStack);
        switch (extractorUseResult) {
            case CONSUMED, SUCCESS, UNLIMITED_USES -> {
                if (extractorUseResult == ExtractorUseResult.CONSUMED) {
                    player.playSound(player, Sound.ENTITY_ITEM_BREAK, 1f, 1f);
                }

                final ItemStack spawnerItem = ExtractorItemUtil.createSpawnerFromBlock(block);
                block.breakNaturally(false);
                player.getInventory().addItem(spawnerItem);
            }
        }
    }
}