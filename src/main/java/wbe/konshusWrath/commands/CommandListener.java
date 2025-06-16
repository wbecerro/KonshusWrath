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
            }
        }

        return true;
    }
}
