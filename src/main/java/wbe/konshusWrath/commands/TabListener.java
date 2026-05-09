package wbe.konshusWrath.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import wbe.konshusWrath.KonshusWrath;
import wbe.konshusWrath.config.BloodMoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabListener implements TabCompleter {

    private final List<String> subCommands = Arrays.asList("help", "start", "end", "get", "addChance", "reload");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if(!command.getName().equalsIgnoreCase("KonshusWrath")) {
            return completions;
        }

        // Mostrar subcomandos
        if(args.length == 1) {
            StringUtil.copyPartialMatches(args[0], subCommands, completions);
        }

        // Argumento 1
        if(args.length == 2) {
            switch(args[0].toLowerCase()) {
                case "start":
                    for(BloodMoon bloodMoon : KonshusWrath.config.bloodMoons.keySet()) {
                        if(args[1].isEmpty()) {
                            completions.add(bloodMoon.getId());
                        } else if(bloodMoon.getId().startsWith(args[1])) {
                            completions.add(bloodMoon.getId());
                        }
                    }
                    break;
                case "addChance":
                    completions.add("<Probabilidad>");
                    break;
            }
        }

        if(args.length == 3) {
            switch(args[0].toLowerCase()) {
                case "start":
                    completions.add("<Tiempo en segundos>");
                    break;
            }
        }

        return completions;
    }
}
