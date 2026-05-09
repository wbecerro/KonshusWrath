package wbe.konshusWrath.config;

import io.lumine.mythic.api.mobs.MythicMob;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BloodMoon {

    private String id;

    private int minDuration;

    private double chanceDivision;

    private int position;

    private boolean clearWeather;

    private boolean spawnMobs;

    private double mobArea;

    private double mobChance;

    private double mobHealthMultiplier;

    private double playerDamageReceivedMultiplier;

    private int extraDropRolls;

    private String bossbar;

    private HashMap<MythicMob, Integer> mobs;

    private int totalWeight;

    public BloodMoon(String id, int minDuration, double chanceDivision, int position, boolean clearWeather, boolean spawnMobs,
                     double mobArea, double mobChance, double mobHealthMultiplier, double playerDamageReceivedMultiplier,
                     int extraDropRolls, String bossbar, HashMap<MythicMob, Integer> mobs, int totalWeight) {
        this.id = id;
        this.minDuration = minDuration;
        this.chanceDivision = chanceDivision;
        this.position = position;
        this.clearWeather = clearWeather;
        this.spawnMobs = spawnMobs;
        this.mobArea = mobArea;
        this.mobChance = mobChance;
        this.mobHealthMultiplier = mobHealthMultiplier;
        this.playerDamageReceivedMultiplier = playerDamageReceivedMultiplier;
        this.extraDropRolls = extraDropRolls;
        this.bossbar = bossbar;
        this.mobs = mobs;
        this.totalWeight = totalWeight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public double getChanceDivision() {
        return chanceDivision;
    }

    public void setChanceDivision(double chanceDivision) {
        this.chanceDivision = chanceDivision;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isClearWeather() {
        return clearWeather;
    }

    public void setClearWeather(boolean clearWeather) {
        this.clearWeather = clearWeather;
    }

    public boolean isSpawnMobs() {
        return spawnMobs;
    }

    public void setSpawnMobs(boolean spawnMobs) {
        this.spawnMobs = spawnMobs;
    }

    public double getMobArea() {
        return mobArea;
    }

    public void setMobArea(double mobArea) {
        this.mobArea = mobArea;
    }

    public double getMobChance() {
        return mobChance;
    }

    public void setMobChance(double mobChance) {
        this.mobChance = mobChance;
    }

    public double getMobHealthMultiplier() {
        return mobHealthMultiplier;
    }

    public void setMobHealthMultiplier(double mobHealthMultiplier) {
        this.mobHealthMultiplier = mobHealthMultiplier;
    }

    public double getPlayerDamageReceivedMultiplier() {
        return playerDamageReceivedMultiplier;
    }

    public void setPlayerDamageReceivedMultiplier(double playerDamageReceivedMultiplier) {
        this.playerDamageReceivedMultiplier = playerDamageReceivedMultiplier;
    }

    public int getExtraDropRolls() {
        return extraDropRolls;
    }

    public void setExtraDropRolls(int extraDropRolls) {
        this.extraDropRolls = extraDropRolls;
    }

    public String getBossbar() {
        return bossbar;
    }

    public void setBossbar(String bossbar) {
        this.bossbar = bossbar;
    }

    public HashMap<MythicMob, Integer> getMobs() {
        return mobs;
    }

    public void setMobs(HashMap<MythicMob, Integer> mobs) {
        this.mobs = mobs;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public MythicMob getMobToSpawn() {
        Random random = new Random();
        int randomNumber = random.nextInt(totalWeight);
        int weight = 0;
        Map.Entry<MythicMob, Integer> last = null;

        for(Map.Entry<MythicMob, Integer> mob : mobs.entrySet()) {
            weight += mob.getValue();
            if(randomNumber < weight) {
                return mob.getKey();
            }
            last = mob;
        }

        return last.getKey();
    }
}
