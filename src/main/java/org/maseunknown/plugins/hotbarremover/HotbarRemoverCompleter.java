package org.maseunknown.plugins.hotbarremover;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class HotbarRemoverCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            if (startsWith("start", args[0])) {
                list.add("start");
            }
            if (startsWith("stop", args[0])) {
                list.add("stop");
            }
            if (startsWith("help", args[0])) {
                list.add("help");
            }
        }
        return list;
    }

    private boolean startsWith(String a, String b) {
        if (b.length() <= a.length()) {
            for (int i = 0; i < b.length(); i++) {
                if (b.toLowerCase().charAt(i) != a.toLowerCase().charAt(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
