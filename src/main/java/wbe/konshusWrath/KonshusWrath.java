package wbe.konshusWrath;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import wbe.konshusWrath.commands.CommandListener;
import wbe.konshusWrath.commands.TabListener;
import wbe.konshusWrath.config.Config;
import wbe.konshusWrath.config.Messages;
import wbe.konshusWrath.listeners.EventListeners;
import wbe.konshusWrath.papi.PapiExtension;
import wbe.konshusWrath.utils.Utilities;

import java.io.File;

public final class KonshusWrath extends JavaPlugin {

    private FileConfiguration configuration;

    private CommandListener commandListener;

    private TabListener tabListener;

    private EventListeners eventListeners;

    private PapiExtension papiExtension;

    public static Config config;

    public static Messages messages;

    public static Utilities utilities;

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            papiExtension = new PapiExtension();
            papiExtension.register();
        }
        saveDefaultConfig();
        getLogger().info("Konshu's Wrath enabled correctly.");
        reloadConfiguration();

        commandListener = new CommandListener();
        getCommand("konshuswrath").setExecutor(commandListener);
        tabListener = new TabListener();
        getCommand("konshuswrath").setTabCompleter(tabListener);
        eventListeners = new EventListeners();
        eventListeners.initializeListeners();
    }

    @Override
    public void onDisable() {
        reloadConfig();
        getLogger().info("Konshu's Wrath disabled correctly.");
    }

    public static KonshusWrath getInstance() {
        return getPlugin(KonshusWrath.class);
    }

    public void reloadConfiguration() {
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }

        reloadConfig();
        configuration = getConfig();
        config = new Config(configuration);
        messages = new Messages(configuration);
        utilities = new Utilities();
    }
}
