package team.bytephoria.extractorpickaxe.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import team.bytephoria.extractorpickaxe.handler.ExtractorBlockHandler;

public final class BukkitBlockBreakListener implements Listener {

    private final ExtractorBlockHandler extractorBlockHandler;
    public BukkitBlockBreakListener(final @NotNull ExtractorBlockHandler extractorBlockHandler) {
        this.extractorBlockHandler = extractorBlockHandler;
    }

    @EventHandler
    public void onBlockBreakEvent(final @NotNull BlockBreakEvent breakEvent) {
        this.extractorBlockHandler.handle(breakEvent.getPlayer(), breakEvent.getBlock());
    }
}