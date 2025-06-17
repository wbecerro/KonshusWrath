package wbe.konshusWrath.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wbe.konshusWrath.KonshusWrath;

public class CommandListener implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("KonshusWrath")) {
            Player player = null;
            if(sender instanceof Player) {
                player = (Player) sender;
            }

            if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
                if(!sender.hasPermission("konshuswrath.command.help")) {
                    sender.sendMessage(KonshusWrath.messages.noPermission);
                    return false;
                }

                for(String line : KonshusWrath.messages.help) {
                    sender.sendMessage(line.replace("&", "§"));
                }
            } else if(args[0].equalsIgnoreCase("start")) {
                if(!sender.hasPermission("konshuswrath.command.start")) {
                    sender.sendMessage(KonshusWrath.messages.noPermission);
                    return false;
                }

                if(args.length > 1) {
                    sender.sendMessage(KonshusWrath.messages.bloodMoonStarted);
                    KonshusWrath.utilities.startBloodMoon(Integer.parseInt(args[1]));
                    return true;
                }

                sender.sendMessage(KonshusWrath.messages.bloodMoonStarted);
                KonshusWrath.utilities.startBloodMoon();
            } else if(args[0].equalsIgnoreCase("end")) {
                if(!sender.hasPermission("konshuswrath.command.end")) {
                    sender.sendMessage(KonshusWrath.messages.noPermission);
                    return false;
                }

                sender.sendMessage(KonshusWrath.messages.bloodMoonEnded);
                KonshusWrath.utilities.endBloodMoon();
            } else if(args[0].equalsIgnoreCase("get")) {
                if(!sender.hasPermission("konshuswrath.command.get")) {
                    sender.sendMessage(KonshusWrath.messages.noPermission);
                    return false;
                }

                sender.sendMessage(KonshusWrath.messages.bloodMoonChance.replace("%chance%", String.valueOf(KonshusWrath.bloodMoonChance)));
            } else if(args[0].equalsIgnoreCase("addChance")) {
                if(!sender.hasPermission("konshuswrath.command.addChance")) {
                    sender.sendMessage(KonshusWrath.messages.noPermission);
                    return false;
                }

                if(args.length < 2) {
                    sender.sendMessage(KonshusWrath.messages.notEnoughArgs);
                    sender.sendMessage(KonshusWrath.messages.addChanceArguments);
                }

                KonshusWrath.bloodMoonChance += Double.parseDouble(args[1]);
                sender.sendMessage(KonshusWrath.messages.addedChance.replace("%chance%", args[1]));
            } else if(args[0].equalsIgnoreCase("reload")) {
                if(!sender.hasPermission("konshuswrath.command.reload")) {
                    sender.sendMessage(KonshusWrath.messages.noPermission);
                    return false;
                }

                KonshusWrath.getInstance().reloadConfiguration();
                sender.sendMessage(KonshusWrath.messages.reload);
            }
        }

        return true;
    }
}
