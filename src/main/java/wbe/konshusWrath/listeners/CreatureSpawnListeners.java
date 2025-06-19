package wbe.konshusWrath.listeners;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import wbe.konshusWrath.KonshusWrath;

import java.util.Random;

public class CreatureSpawnListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void addHealthOnBloodMoon(CreatureSpawnEvent event) {
        if(event.isCancelled()) {
            return;
        }

        if(!KonshusWrath.bloodMoonActive) {
            return;
        }

        LivingEntity entity = event.getEntity();

        if(!MythicBukkit.inst().getMobManager().isMythicMob(entity)) {
            NamespacedKey attributeKey = new NamespacedKey(KonshusWrath.getInstance(), "BloodMoonExtraHealth");
            AttributeModifier attributeModifier = new AttributeModifier(attributeKey, KonshusWrath.config.mobsHealthMultiplier - 1,
                    AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.ANY);
            if(entity.getAttribute(Attribute.MAX_HEALTH).getModifiers().contains(attributeModifier)) {
                return;
            }
            entity.getAttribute(Attribute.MAX_HEALTH).addModifier(attributeModifier);
        }

        entity.setHealth(entity.getAttribute(Attribute.MAX_HEALTH).getValue());

        if(!KonshusWrath.config.spawnMob) {
            return;
        }

        if(!(entity instanceof Monster)) {
            return;
        }

        Random random = new Random();
        if(random.nextDouble(100) <= KonshusWrath.config.mobChance) {
            event.setCancelled(true);
            MythicBukkit.inst().getMobManager().spawnMob(KonshusWrath.utilities.getMobToSpawn().getInternalName(), entity.getLocation());
        }
    }
}
