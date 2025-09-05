package team.bytephoria.extractorpickaxe;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import team.bytephoria.extractorpickaxe.commands.ExtractorBukkitCommand;
import team.bytephoria.extractorpickaxe.data.CacheData;
import team.bytephoria.extractorpickaxe.handler.ExtractorBlockHandler;
import team.bytephoria.extractorpickaxe.listener.BukkitBlockBreakListener;
import team.bytephoria.extractorpickaxe.listener.WorldGuardBreakBlockListener;
import team.bytephoria.extractorpickaxe.service.ExtractorService;

import java.io.File;

/**
 * Main plugin class for the Extractor Pickaxe plugin.
 * <p>
 * Handles initialization of services, configuration, listeners, and commands.
 */
public final class PaperPlugin extends JavaPlugin {

    private CacheData cacheData;
    private ExtractorService extractorService;
    private ExtractorBlockHandler extractorBlockHandler;

    @Override
    public void onEnable() {

        this.cacheData = this.loadCacheData();
        this.extractorService = new ExtractorService();
        this.extractorBlockHandler = new ExtractorBlockHandler(this.extractorService);

        if (this.getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            this.getServer().getPluginManager().registerEvents(new WorldGuardBreakBlockListener(this.extractorBlockHandler), this);
            this.getLogger().info("WorldGuard listener has been registered.");
        } else {
            this.getServer().getPluginManager().registerEvents(new BukkitBlockBreakListener(this.extractorBlockHandler), this);
            this.getLogger().info("Bukkit listener has been registered.");
        }

        this.getServer().getCommandMap().register("extractor-pickaxe", new ExtractorBukkitCommand(this.extractorService, this.cacheData));
    }

    @Override
    public void onDisable() {
        this.extractorBlockHandler = null;
        this.extractorService = null;
        this.cacheData = null;
    }

    /** Loads plugin messages and configuration into a CacheData object. */
    private @NotNull CacheData loadCacheData() {
        final File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            this.saveResource("config.yml", false);
            this.getLogger().info("'config.yml' has been created.");
        }

        final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        return new CacheData()
                .setNoPermission(yamlConfiguration.getString("messages.no-permission", ""))
                .setInvalidCommand(yamlConfiguration.getString("messages.invalid-command", ""))
                .setExtractorReceived(yamlConfiguration.getString("messages.extractor-received", ""))
                .setConvertSuccess(yamlConfiguration.getString("messages.convert-success", ""))
                .setConvertFail(yamlConfiguration.getString("messages.convert-fail", ""))
                .setInvalidNumber(yamlConfiguration.getString("messages.invalid-number", ""))
                .setSetUsesSuccess(yamlConfiguration.getString("messages.set-uses-success", ""))
                .setSetUsesFail(yamlConfiguration.getString("messages.set-uses-fail", ""))
                .setMustHoldPickaxe(yamlConfiguration.getString("messages.must-hold-pickaxe", ""));
    }
}
