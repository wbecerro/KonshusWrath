package wbe.konshusWrath.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private FileConfiguration config;

    public String noPermission;
    public String notEnoughArgs;
    public String reload;
    public String bloodMoonStart;
    public String bloodMoonEnd;
    public String bloodMoonStarted;
    public String bloodMoonEnded;
    public String bloodMoonChance;
    public String addedChance;
    public String addChanceArguments;
    public List<String> help = new ArrayList<>();

    public Messages(FileConfiguration config) {
        this.config = config;

        noPermission = config.getString("Messages.noPermission").replace("&", "§");
        notEnoughArgs = config.getString("Messages.notEnoughArgs").replace("&", "§");
        reload = config.getString("Messages.reload").replace("&", "§");
        bloodMoonStart = config.getString("Messages.bloodMoonStart").replace("&", "§");
        bloodMoonEnd = config.getString("Messages.bloodMoonEnd").replace("&", "§");
        bloodMoonStarted = config.getString("Messages.bloodMoonStarted").replace("&", "§");
        bloodMoonEnded = config.getString("Messages.bloodMoonEnded").replace("&", "§");
        bloodMoonChance = config.getString("Messages.bloodMoonChance").replace("&", "§");
        addedChance = config.getString("Messages.addedChance").replace("&", "§");
        addChanceArguments = config.getString("Messages.addChanceArguments").replace("&", "§");
        help = config.getStringList("Messages.help");
    }
}
