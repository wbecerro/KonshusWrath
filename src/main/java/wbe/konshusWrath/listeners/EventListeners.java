package wbe.konshusWrath.listeners;

import org.bukkit.plugin.PluginManager;
import wbe.konshusWrath.KonshusWrath;

public class EventListeners {

    public void initializeListeners() {
        KonshusWrath plugin = KonshusWrath.getInstance();
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        //pluginManager.registerEvents();
    }
}
