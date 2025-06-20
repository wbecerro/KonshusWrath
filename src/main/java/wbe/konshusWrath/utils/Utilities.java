package wbe.konshusWrath.utils;

import io.lumine.mythic.api.mobs.MythicMob;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wbe.konshusWrath.KonshusWrath;

import java.time.Instant;
import java.util.Map;
import java.util.Random;

public class Utilities {

    public void startBloodMoon() {
        World world = Bukkit.getServer().getWorld("world");
        if(KonshusWrath.config.weatherClear) {
            world.setClearWeatherDuration(KonshusWrath.config.bloodMoonMinDuration * 20);
        }

        world.setTime(KonshusWrath.config.bloodMoonPosition);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player, KonshusWrath.config.bloodMoonStart, 1F, 1F);
        }
        Bukkit.broadcastMessage(KonshusWrath.messages.bloodMoonStart);

        KonshusWrath.bloodMoonActive = true;
        Scheduler.bossBar.setVisible(true);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KonshusWrath.getInstance(), new Runnable() {
            @Override
            public void run() {
                endBloodMoon();
            }
        }, KonshusWrath.config.bloodMoonMinDuration * 20L);

        double end = Instant.now().getEpochSecond() + KonshusWrath.config.bloodMoonMinDuration;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!KonshusWrath.bloodMoonActive) {
                    this.cancel();
                } else {
                    double now = end - Instant.now().getEpochSecond();
                    Scheduler.bossBar.setProgress(now / KonshusWrath.config.bloodMoonMinDuration);
                }
            }
        }.runTaskTimer(KonshusWrath.getInstance(), 20L, 20L);
    }

    public void startBloodMoon(int duration) {
        World world = Bukkit.getServer().getWorld("world");
        if(KonshusWrath.config.weatherClear) {
            world.setClearWeatherDuration(duration * 20);
        }

        world.setTime(KonshusWrath.config.bloodMoonPosition);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player, KonshusWrath.config.bloodMoonStart, 1F, 1F);
        }
        Bukkit.broadcastMessage(KonshusWrath.messages.bloodMoonStart);

        KonshusWrath.bloodMoonActive = true;
        Scheduler.bossBar.setProgress(1);
        Scheduler.bossBar.setVisible(true);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KonshusWrath.getInstance(), new Runnable() {
            @Override
            public void run() {
                endBloodMoon();
            }
        }, duration * 20L);

        double end = Instant.now().getEpochSecond() + duration;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!KonshusWrath.bloodMoonActive) {
                    this.cancel();
                } else {
                    double now = end - Instant.now().getEpochSecond();
                    Scheduler.bossBar.setProgress(now / duration);
                }
            }
        }.runTaskTimer(KonshusWrath.getInstance(), 20L, 20L);
    }

    public void endBloodMoon() {
        World world = Bukkit.getServer().getWorld("world");
        KonshusWrath.bloodMoonActive = false;
        KonshusWrath.bloodMoonChance = KonshusWrath.config.baseChance;
        Scheduler.bossBar.setVisible(false);
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player, KonshusWrath.config.bloodMoonEnd, 1F, 1F);
        }
        Bukkit.broadcastMessage(KonshusWrath.messages.bloodMoonEnd);

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        world.setTime(0);
    }

    public MythicMob getMobToSpawn() {
        Random random = new Random();
        int randomNumber = random.nextInt(KonshusWrath.config.totalWeight);
        int weight = 0;
        Map.Entry<MythicMob, Integer> last = null;

        for(Map.Entry<MythicMob, Integer> mob : KonshusWrath.config.mobs.entrySet()) {
            weight += mob.getValue();
            if(randomNumber < weight) {
                return mob.getKey();
            }
            last = mob;
        }

        return last.getKey();
    }

    public Location getNearbyLocation(Player player) {
        Location playerLocation = player.getLocation();
        Random random = new Random();

        double x = playerLocation.getX();
        double z = playerLocation.getZ();
        x = random.nextDouble(x - KonshusWrath.config.mobArea, x + KonshusWrath.config.mobArea);
        z = random.nextDouble(z - KonshusWrath.config.mobArea, z + KonshusWrath.config.mobArea);
        Location finalLocation = new Location(player.getWorld(), x, player.getWorld().getHighestBlockAt((int) x, (int) z).getY(), z);
        return finalLocation;
    }
}
