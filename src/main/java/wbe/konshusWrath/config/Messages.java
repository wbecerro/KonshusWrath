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
    public String bloodMoonStartArguments;
    public String bloodMoonEnded;
    public String bloodMoonChance;
    public String addedChance;
    public String addChanceArguments;
    public String cannotUseBed;
    public String bloodMoonDoesNotExist;
    public String noMoon;
    public List<String> help = new ArrayList<>();

    public Messages(FileConfiguration config) {
        this.config = config;

        noPermission = config.getString("Messages.noPermission").replace("&", "§");
        notEnoughArgs = config.getString("Messages.notEnoughArgs").replace("&", "§");
        reload = config.getString("Messages.reload").replace("&", "§");
        bloodMoonStart = config.getString("Messages.bloodMoonStart").replace("&", "§");
        bloodMoonEnd = config.getString("Messages.bloodMoonEnd").replace("&", "§");
        bloodMoonStarted = config.getString("Messages.bloodMoonStarted").replace("&", "§");
        bloodMoonStartArguments = config.getString("Messages.bloodMoonStartArguments").replace("&", "§");
        bloodMoonEnded = config.getString("Messages.bloodMoonEnded").replace("&", "§");
        bloodMoonChance = config.getString("Messages.bloodMoonChance").replace("&", "§");
        addedChance = config.getString("Messages.addedChance").replace("&", "§");
        addChanceArguments = config.getString("Messages.addChanceArguments").replace("&", "§");
        cannotUseBed = config.getString("Messages.cannotUseBed").replace("&", "§");
        bloodMoonDoesNotExist = config.getString("Messages.bloodMoonDoesNotExist").replace("&", "§");
        noMoon = config.getString("Messages.noMoon").replace("&", "§");
        help = config.getStringList("Messages.help");
    }
}
