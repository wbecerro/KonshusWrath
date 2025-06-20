package wbe.konshusWrath.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import wbe.konshusWrath.KonshusWrath;

public class PapiExtension extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "wbe";
    }

    @Override
    public String getIdentifier() {
        return "KonshusWrath";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("bloodMoonChance")) {
            return String.valueOf(KonshusWrath.bloodMoonChance);
        }

        return null;
    }
}
