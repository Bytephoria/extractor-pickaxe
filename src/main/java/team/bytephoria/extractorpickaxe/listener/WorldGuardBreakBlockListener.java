package team.bytephoria.extractorpickaxe.listener;

import com.sk89q.worldguard.bukkit.event.block.BreakBlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import team.bytephoria.extractorpickaxe.handler.ExtractorBlockHandler;

public final class WorldGuardBreakBlockListener implements Listener {

    private final ExtractorBlockHandler extractorBlockHandler;
    public WorldGuardBreakBlockListener(final @NotNull ExtractorBlockHandler extractorBlockHandler) {
        this.extractorBlockHandler = extractorBlockHandler;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onBreakBlockEvent(final @NotNull BreakBlockEvent blockEvent) {
        final Player player = blockEvent.getCause().getFirstPlayer();
        if (player == null || blockEvent.getBlocks().isEmpty()) {
            return;
        }

        this.extractorBlockHandler.handle(player, blockEvent.getBlocks().get(0));
        blockEvent.setAllowed(true);
        blockEvent.setSilent(true);
        blockEvent.setResult(Event.Result.DENY);
    }
}
