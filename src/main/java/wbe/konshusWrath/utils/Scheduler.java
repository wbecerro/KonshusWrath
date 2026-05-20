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
import wbe.konshusWrath.config.BloodMoon;

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
        bossBar = Bukkit.createBossBar(barKey, "",
                BarColor.RED, BarStyle.SOLID, BarFlag.DARKEN_SKY);
        bossBar.setVisible(false);
        bossBar.setProgress(1);
        for(Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(KonshusWrath.getInstance(), new Runnable() {
            @Override
            public void run() {
                long time = world.getTime();
                if(time >= 0L && time <= 14999L) { // Día
                    night = false;
                } else if(time >= 13000L && time <= 23999L) { // Noche
                    if(!night) {
                        if(random.nextDouble(100) <= KonshusWrath.bloodMoonChance && !Bukkit.getOnlinePlayers().isEmpty()) {
                            BloodMoon bloodMoon = KonshusWrath.utilities.getBloodMoon();
                            int duration = (int) (KonshusWrath.bloodMoonChance / bloodMoon.getChanceDivision()) * 60;
                            duration = Math.max(duration, bloodMoon.getMinDuration());
                            KonshusWrath.utilities.startBloodMoon(duration, bloodMoon);
                        } else {
                            KonshusWrath.bloodMoonChance += KonshusWrath.config.extraChancePerNight;
                            if(KonshusWrath.bloodMoonChance > 100) {
                                KonshusWrath.bloodMoonChance = 100;
                            }
                        }
                    }
                    night = true;
                }
            }
        }, 0L, 20L);
    }
}
