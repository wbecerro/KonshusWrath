package wbe.konshusWrath.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import wbe.konshusWrath.KonshusWrath;

public class EntityDamageListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void addDamageOnBloodMoon(EntityDamageEvent event) {
        if(event.isCancelled()) {
            return;
        }

        if(!KonshusWrath.bloodMoonActive) {
            return;
        }

        if(!(event.getEntity() instanceof Player)) {
            return;
        }

        event.setDamage(event.getDamage() * KonshusWrath.config.playerDamageReceivedMultiplier);
    }
}
