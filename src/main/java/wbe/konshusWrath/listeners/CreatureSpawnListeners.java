package wbe.konshusWrath.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import wbe.konshusWrath.KonshusWrath;

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

        NamespacedKey attributeKey = new NamespacedKey(KonshusWrath.getInstance(), "BloodMoonExtraHealth");
        AttributeModifier attributeModifier = new AttributeModifier(attributeKey, KonshusWrath.config.mobsHealthMultiplier - 1,
                AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.ANY);
        if(entity.getAttribute(Attribute.MAX_HEALTH).getModifiers().contains(attributeModifier)) {
            return;
        }
        entity.getAttribute(Attribute.MAX_HEALTH).addModifier(attributeModifier);
    }
}
