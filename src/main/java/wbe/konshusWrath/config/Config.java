package wbe.konshusWrath.config;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Config {

    private FileConfiguration config;

    public double baseChance;
    public double extraChancePerNight;
    public List<CreatureSpawnEvent.SpawnReason> allowedSpawnReasons = new ArrayList<>();

    public Sound bloodMoonStart;
    public Sound bloodMoonEnd;

    public HashMap<BloodMoon, Integer> bloodMoons = new HashMap<>();

    private int totalWeight = 0;
    public int totalBloodMoonWeight = 0;

    public Config(FileConfiguration config) {
        this.config = config;

        baseChance = config.getDouble("Config.baseChance");
        extraChancePerNight = config.getDouble("Config.extraChancePerNight");
        List<String> spawnReasons = config.getStringList("Config.allowedSpawnReasons");
        spawnReasons.forEach(spawnReason -> {
            allowedSpawnReasons.add(CreatureSpawnEvent.SpawnReason.valueOf(spawnReason));
        });

        bloodMoonStart = Sound.valueOf(config.getString("Sounds.bloodMoonStart"));
        bloodMoonEnd = Sound.valueOf(config.getString("Sounds.bloodMoonEnd"));

        loadBloodMoons();
    }

    private void loadBloodMoons() {
        Set<String> configBloodMoons = config.getConfigurationSection("BloodMoons").getKeys(false);
        for(String bloodMoon : configBloodMoons) {
            int weight = config.getInt("BloodMoons." + bloodMoon + ".weight");
            totalBloodMoonWeight += weight;
            int bloodMoonMinDuration = config.getInt("BloodMoons." + bloodMoon + ".minDuration");
            double chanceDivision = config.getDouble("BloodMoons." + bloodMoon + ".chanceDivision");
            int bloodMoonPosition = config.getInt("BloodMoons." + bloodMoon + ".moonPosition");
            boolean weatherClear = config.getBoolean("BloodMoons." + bloodMoon + ".clearWeather");
            boolean spawnMob = config.getBoolean("BloodMoons." + bloodMoon + ".spawnMobs");
            double mobArea = config.getDouble("BloodMoons." + bloodMoon + ".mobArea");
            double mobChance = config.getDouble("BloodMoons." + bloodMoon + ".mobChance");
            double mobsHealthMultiplier = config.getDouble("BloodMoons." + bloodMoon + ".mobsHealthMultiplier");
            double playerDamageReceivedMultiplier = config.getDouble("BloodMoons." + bloodMoon + ".playerDamageReceivedMultiplier");
            int extraDropRolls = config.getInt("BloodMoons." + bloodMoon + ".extraDropRolls");
            String bossBarMessage = config.getString("BloodMoons." + bloodMoon + ".bossBarMessage").replace("&", "§");
            HashMap<MythicMob, Integer> mobs = loadMobs(bloodMoon);

            BloodMoon newBloodMoon = new BloodMoon(bloodMoon, bloodMoonMinDuration, chanceDivision, bloodMoonPosition,
                    weatherClear, spawnMob, mobArea, mobChance, mobsHealthMultiplier, playerDamageReceivedMultiplier,
                    extraDropRolls, bossBarMessage, mobs, totalWeight);
            bloodMoons.put(newBloodMoon, weight);
        }
    }

    private HashMap<MythicMob, Integer> loadMobs(String bloodmoon) {
        totalWeight = 0;
        Set<String> configMobs = config.getConfigurationSection("BloodMoons." + bloodmoon + ".mobs").getKeys(false);
        HashMap<MythicMob, Integer> mobs = new HashMap<>();
        for(String mob : configMobs) {
            MythicMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(mob).orElseThrow();
            int weight = config.getInt("BloodMoons." + bloodmoon + ".mobs." + mob + ".weight");
            totalWeight += weight;
            mobs.put(mythicMob, weight);
        }

        return mobs;
    }
}
