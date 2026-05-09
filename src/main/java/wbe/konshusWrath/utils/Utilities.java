package wbe.konshusWrath.utils;

import io.lumine.mythic.api.mobs.MythicMob;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wbe.konshusWrath.KonshusWrath;
import wbe.konshusWrath.config.BloodMoon;

import java.time.Instant;
import java.util.Map;
import java.util.Random;

public class Utilities {

    public void startBloodMoon(int duration, BloodMoon bloodMoon) {
        World world = Bukkit.getServer().getWorld("world");
        if(bloodMoon.isClearWeather()) {
            world.setClearWeatherDuration(duration * 20);
        }

        world.setTime(bloodMoon.getPosition());
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player, KonshusWrath.config.bloodMoonStart, 1F, 1F);
        }
        Bukkit.broadcastMessage(KonshusWrath.messages.bloodMoonStart);

        KonshusWrath.bloodMoonActive = bloodMoon;
        Scheduler.bossBar.setProgress(1);
        Scheduler.bossBar.setVisible(true);
        Scheduler.bossBar.setTitle(KonshusWrath.bloodMoonActive.getBossbar());
        new BukkitRunnable() {
            @Override
            public void run() {
                if(KonshusWrath.bloodMoonActive != null) {
                    endBloodMoon();
                }
            }
        }.runTaskLater(KonshusWrath.getInstance(), duration * 20L);

        double end = Instant.now().getEpochSecond() + duration;
        KonshusWrath.bloodMoonEnd = end;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(KonshusWrath.bloodMoonActive == null) {
                    this.cancel();
                } else {
                    double now = end - Instant.now().getEpochSecond();
                    now = now < 0 ? 0 : now;
                    Scheduler.bossBar.setProgress(now / duration);
                }
            }
        }.runTaskTimer(KonshusWrath.getInstance(), 20L, 20L);
    }

    public void endBloodMoon() {
        KonshusWrath.bloodMoonEnd = 0;
        World world = Bukkit.getServer().getWorld("world");
        KonshusWrath.bloodMoonActive = null;
        KonshusWrath.bloodMoonChance = KonshusWrath.config.baseChance;
        Scheduler.bossBar.setVisible(false);
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player, KonshusWrath.config.bloodMoonEnd, 1F, 1F);
        }
        Bukkit.broadcastMessage(KonshusWrath.messages.bloodMoonEnd);

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
    }

    public BloodMoon getBloodMoon() {
        Random random = new Random();
        int randomNumber = random.nextInt(KonshusWrath.config.totalBloodMoonWeight);
        int weight = 0;
        Map.Entry<BloodMoon, Integer> last = null;

        for(Map.Entry<BloodMoon, Integer> bloodMoon : KonshusWrath.config.bloodMoons.entrySet()) {
            weight += bloodMoon.getValue();
            if(randomNumber < weight) {
                return bloodMoon.getKey();
            }
            last = bloodMoon;
        }

        return last.getKey();
    }

    public String getTime(double time) {
        int hours = (int) (time / 3600);
        int minutes = (int) ((time - 3600 * hours) / 60);
        int seconds = (int) (time - hours * 3600 - minutes * 60);
        String timeLine = "";
        if(hours > 0) {
            timeLine += hours + "h ";
        }
        if(minutes > 0) {
            timeLine += minutes + "m ";
        }
        if(seconds > 0) {
            timeLine += seconds + "s";
        }
        return timeLine;
    }

    public Location getNearbyLocation(Player player, BloodMoon bloodMoon) {
        Location playerLocation = player.getLocation();
        Random random = new Random();

        double x = playerLocation.getX();
        double z = playerLocation.getZ();
        x = random.nextDouble(x - bloodMoon.getMobArea(), x + bloodMoon.getMobArea());
        z = random.nextDouble(z - bloodMoon.getMobArea(), z + bloodMoon.getMobArea());
        Location finalLocation = new Location(player.getWorld(), x, player.getWorld().getHighestBlockAt((int) x, (int) z).getY(), z);
        return finalLocation;
    }

    public BloodMoon getBloodMoonByName(String name) {
        for(BloodMoon bloodMoon : KonshusWrath.config.bloodMoons.keySet()) {
            if(bloodMoon.getId().equalsIgnoreCase(name)) {
                return bloodMoon;
            }
        }

        return null;
    }
}
