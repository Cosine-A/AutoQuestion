package kr.cosine.autoquestion;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!AutoQuestion.getPlugin(AutoQuestion.class).getConfig().contains((p.getUniqueId()).toString())) {
            AutoQuestion.getPlugin(AutoQuestion.class).getConfig().set((p.getUniqueId()).toString(), false);
        }
        Gui gui = new Gui();
        gui.setting(p);
        return false;
    }
}
