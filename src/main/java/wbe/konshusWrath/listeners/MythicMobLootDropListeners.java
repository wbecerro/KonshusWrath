package wbe.konshusWrath.listeners;

import io.lumine.mythic.bukkit.events.MythicMobLootDropEvent;
import io.lumine.mythic.core.drops.LootBag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import wbe.konshusWrath.KonshusWrath;

public class MythicMobLootDropListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void multiplyDropsOnBloodMoon(MythicMobLootDropEvent event) {
        if(!KonshusWrath.bloodMoonActive) {
            return;
        }

        LootBag lootBag = event.getMobType().getDropTable().generate();
        lootBag.drop(event.getMob().getLocation());
    }
}
