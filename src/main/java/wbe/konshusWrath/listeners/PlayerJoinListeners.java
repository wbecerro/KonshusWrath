package wbe.konshusWrath.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import wbe.konshusWrath.utils.Scheduler;

public class PlayerJoinListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void addPlayerToBossBar(PlayerJoinEvent event) {
        if(Scheduler.bossBar != null) {
            Scheduler.bossBar.addPlayer(event.getPlayer());
        }
    }
}
