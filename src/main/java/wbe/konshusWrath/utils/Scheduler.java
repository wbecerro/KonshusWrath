package wbe.konshusWrath.utils;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import wbe.konshusWrath.KonshusWrath;

import java.util.Random;

public class Scheduler {

    private static boolean night = false;

    public static BossBar bossBar = null;

    public static void startSchedulers() {
        startNightCheck();
    }

    private static void startNightCheck() {
        World world = Bukkit.getServer().getWorld("world");
        Random random = new Random();
        NamespacedKey barKey = new NamespacedKey(KonshusWrath.getInstance(), "bloodMoon");
        bossBar = Bukkit.createBossBar(barKey, KonshusWrath.config.bossBarMessage,
                BarColor.RED, BarStyle.SOLID, BarFlag.DARKEN_SKY);
        bossBar.setVisible(false);
        bossBar.setProgress(100);
        for(Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(KonshusWrath.getInstance(), new Runnable() {
            @Override
            public void run() {
                long time = world.getTime();
                if(time >= 0L && time <= 11999L) { // Día
                    night = false;
                } else if(time >= 12000L && time <= 23999L) { // Noche
                    if(!night) {
                        if(random.nextDouble(100) <= KonshusWrath.bloodMoonChance) {
                            // Se activa la luna sangrienta
                            KonshusWrath.bloodMoonActive = true;
                            bossBar.setVisible(true);
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KonshusWrath.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    KonshusWrath.bloodMoonActive = false;
                                    bossBar.setVisible(false);
                                }
                            }, KonshusWrath.config.bloodMoonDuration * 20L);
                        } else {
                            KonshusWrath.bloodMoonChance += KonshusWrath.config.extraChancePerNight;
                        }
                    }
                    night = true;
                }
            }
        }, 0L, 20L);
    }
}
