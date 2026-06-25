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

        if(!KonshusWrath.config.allowedSpawnReasons.contains(event.getSpawnReason())) {
            return;
        }

        if(KonshusWrath.bloodMoonActive == null) {
            return;
        }

        if(!KonshusWrath.bloodMoonActive.isSpawnMobs()) {
            return;
        }

        LivingEntity entity = event.getEntity();
        if(!(entity instanceof Monster monster)) {
            return;
        }

        if(MythicBukkit.inst().getMobManager().isMythicMob(monster)) {
            return;
        }

        if(!MythicBukkit.inst().getMobManager().isMythicMob(entity)) {
            NamespacedKey attributeKey = new NamespacedKey(KonshusWrath.getInstance(), "BloodMoonExtraHealth");
            AttributeModifier attributeModifier = new AttributeModifier(attributeKey, KonshusWrath.bloodMoonActive.getMobHealthMultiplier() - 1,
                    AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.ANY);
            if(entity.getAttribute(Attribute.MAX_HEALTH).getModifiers().contains(attributeModifier)) {
                return;
            }
            entity.getAttribute(Attribute.MAX_HEALTH).addModifier(attributeModifier);
        }

        entity.setHealth(entity.getAttribute(Attribute.MAX_HEALTH).getValue());

        Random random = new Random();
        if(random.nextDouble(100) <= KonshusWrath.bloodMoonActive.getMobChance()) {
            event.setCancelled(true);
            MythicBukkit.inst().getMobManager().spawnMob(KonshusWrath.bloodMoonActive.getMobToSpawn().getInternalName(), entity.getLocation());
        }
    }
}
