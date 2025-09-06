package team.bytephoria.extractorpickaxe.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import team.bytephoria.extractorpickaxe.ExtractorPermissions;
import team.bytephoria.extractorpickaxe.data.CacheData;
import team.bytephoria.extractorpickaxe.service.ExtractorService;
import team.bytephoria.extractorpickaxe.utils.ExtractorItemUtil;
import team.bytephoria.extractorpickaxe.utils.LegacyComponentUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bukkit command to manage Extractor Pickaxe functionality.
 * <p>
 * Subcommands:
 * - get: gives the player a new extractor.
 * - convert: converts the item in main hand into an extractor.
 * - set-uses: sets the number of uses of the extractor in main hand.
 */
public final class ExtractorBukkitCommand extends BukkitCommand {

    private final ExtractorService extractorService;
    private final CacheData cacheData;

    public ExtractorBukkitCommand(
            final @NotNull ExtractorService extractorService,
            final @NotNull CacheData cacheData
    ) {
        super(
                "extractor",
                "Allows players to get, convert, or set uses for their Extractor Pickaxe.",
                "/<command> <get|convert|set-uses>",
                Collections.emptyList()
        );

        this.extractorService = extractorService;
        this.cacheData = cacheData;
    }

    @Override
    public boolean execute(final @NotNull CommandSender commandSender, final @NotNull String label, final @NotNull String[] arguments) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This command is for players only.");
            return true;
        }

        if (!this.hasPermission(commandSender)) {
            this.sendMessage(player, this.cacheData.getNoPermission());
            return true;
        }

        if (arguments.length == 0) {
            this.sendMessage(player, this.cacheData.getInvalidCommand());
            return true;
        }

        switch (arguments[0].toLowerCase()) {
            case "get" -> this.handleGet(player);
            case "convert" -> this.handleConvert(player);
            case "set-uses" -> this.handleSetUses(player, arguments);
            default -> this.sendMessage(player, this.cacheData.getInvalidCommand());
        }

        return true;
    }

    private void handleGet(final @NotNull Player player) {
        this.extractorService.addExtractorItem(player.getInventory());
        this.sendMessage(player, this.cacheData.getExtractorReceived());
    }

    private void handleConvert(final @NotNull Player player) {
        final ItemStack itemStack = player.getEquipment().getItemInMainHand();
        if (!ExtractorItemUtil.isPickaxe(itemStack.getType())) {
            this.sendMessage(player, this.cacheData.getMustHoldPickaxe());
            return;
        }

        final boolean success = this.extractorService.convertToExtractor(itemStack);
        this.sendMessage(player, success ? this.cacheData.getConvertSuccess() : this.cacheData.getConvertFail());
    }

    private void handleSetUses(final @NotNull Player player, final @NotNull String[] arguments) {
        final ItemStack itemStack = player.getEquipment().getItemInMainHand();
        if (!ExtractorItemUtil.isPickaxe(itemStack.getType())) {
            this.sendMessage(player, this.cacheData.getMustHoldPickaxe());
            return;
        }

        if (arguments.length < 2) {
            this.sendMessage(player, this.cacheData.getInvalidNumber());
            return;
        }

        final int amount;
        try {
            amount = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            this.sendMessage(player, this.cacheData.getInvalidNumber());
            return;
        }

        final boolean success = this.extractorService.setExtractorUses(itemStack, amount);
        this.sendMessage(player, success
                ? this.cacheData.getSetUsesSuccess().replace("%amount%", Integer.toString(amount))
                : this.cacheData.getSetUsesFail());
    }

    private void sendMessage(final @NotNull Player player, final @NotNull String message) {
        player.sendMessage(LegacyComponentUtil.asComponent(message));
    }

    private boolean hasPermission(final @NotNull CommandSender commandSender) {
        return commandSender.hasPermission(ExtractorPermissions.COMMAND_USE);
    }

    @Override
    public @NotNull List<String> tabComplete(final @NotNull CommandSender commandSender, final @NotNull String alias, final @NotNull String @NotNull [] arguments) {
        return switch (arguments.length) {
            case 1 -> {
                if (this.hasPermission(commandSender)) {
                    final String argumentZero = arguments[0].toLowerCase();
                    yield this.getSubcommands().stream()
                            .filter(command -> command.startsWith(argumentZero))
                            .collect(Collectors.toList());
                }

                yield Collections.emptyList();
            }

            case 2 -> {
                if (this.hasPermission(commandSender)) {
                    if (arguments[0].equalsIgnoreCase("set-uses")) {
                        yield List.of("1", "5", "10", "50", "100");
                    }
                }

                yield Collections.emptyList();
            }

            default -> Collections.emptyList();
        };
    }

    @Contract(value = " -> new", pure = true)
    private @NotNull @Unmodifiable List<String> getSubcommands() {
        return List.of("get", "convert", "set-uses");
    }
}
