package wbe.konshusWrath.config;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Set;

public class Config {

    private FileConfiguration config;

    public double baseChance;
    public double extraChancePerNight;

    public Sound bloodMoonStart;
    public Sound bloodMoonEnd;

    public int bloodMoonMinDuration;
    public int chanceDivision;
    public int bloodMoonPosition;
    public boolean weatherClear;
    public boolean spawnMob;
    public double mobArea;
    public double mobChance;
    public double mobsHealthMultiplier;
    public double playerDamageReceivedMultiplier;
    public String bossBarMessage;
    public HashMap<MythicMob, Integer> mobs = new HashMap<>();
    public int totalWeight = 0;

    public Config(FileConfiguration config) {
        this.config = config;

        baseChance = config.getDouble("Config.baseChance");
        extraChancePerNight = config.getDouble("Config.extraChancePerNight");

        bloodMoonStart = Sound.valueOf(config.getString("Sounds.bloodMoonStart"));
        bloodMoonEnd = Sound.valueOf(config.getString("Sounds.bloodMoonEnd"));

        bloodMoonMinDuration = config.getInt("BloodMoon.minDuration");
        chanceDivision = config.getInt("BloodMoon.chanceDivision");
        bloodMoonPosition = config.getInt("BloodMoon.moonPosition");
        weatherClear = config.getBoolean("BloodMoon.clearWeather");
        spawnMob = config.getBoolean("BloodMoon.spawnMobs");
        mobArea = config.getDouble("BloodMoon.mobArea");
        mobChance = config.getDouble("BloodMoon.mobChance");
        mobsHealthMultiplier = config.getDouble("BloodMoon.mobsHealthMultiplier");
        playerDamageReceivedMultiplier = config.getDouble("BloodMoon.playerDamageReceivedMultiplier");
        bossBarMessage = config.getString("BloodMoon.bossBarMessage").replace("&", "§");

        loadMobs();
    }

    private void loadMobs() {
        Set<String> configMobs = config.getConfigurationSection("BloodMoon.mobs").getKeys(false);
        for(String mob : configMobs) {
            MythicMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(mob).orElseThrow();
            int weight = config.getInt("BloodMoon.mobs." + mob + ".weight");
            totalWeight += weight;
            mobs.put(mythicMob, weight);
        }
    }
}
