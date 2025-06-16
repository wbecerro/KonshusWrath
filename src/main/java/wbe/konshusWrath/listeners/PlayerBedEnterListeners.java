package wbe.konshusWrath.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import wbe.konshusWrath.KonshusWrath;

public class PlayerBedEnterListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void cancelBedUseOnBloodMoon(PlayerBedEnterEvent event) {
        if(event.isCancelled()) {
            return;
        }

        if(KonshusWrath.bloodMoonActive) {
            event.setCancelled(true);
            event.setUseBed(Event.Result.DENY);
            event.getPlayer().sendMessage(KonshusWrath.messages.cannotUseBed);
        }
    }
}
