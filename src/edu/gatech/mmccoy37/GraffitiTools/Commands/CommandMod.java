package edu.gatech.mmccoy37.GraffitiTools.Commands;

import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import edu.gatech.mmccoy37.GraffitiTools.Modifiers.PlaceMod;
import edu.gatech.mmccoy37.GraffitiTools.Modifiers.Modifier;
import edu.gatech.mmccoy37.GraffitiTools.Modifiers.ReplaceMod;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by matt on 6/26/16.
 */
public class CommandMod {

    public static boolean setMod(Player p, String s) {

        Modifier oldMod = PlayerStates.getData(p).getTool().getModifier();
        Modifier newMod;
        switch (s) {
            case ("replace"):
                if (oldMod instanceof ReplaceMod) {
                    return true;
                } else {
                    newMod = new ReplaceMod();
                }
                break;
            case ("place"):
                if (oldMod instanceof PlaceMod) {
                    return true;
                } else {
                    newMod = new PlaceMod();
                }
                break;
            case ("help"):
                p.sendMessage(CommandCore.TAG + " modifiers list:");
                p.sendMessage(CommandCore.TAB + ChatColor.RESET + "place");
                p.sendMessage(CommandCore.TAB + ChatColor.RESET + "replace");
                p.sendMessage(CommandCore.TAB + ChatColor.GRAY + "erase");
                p.sendMessage(CommandCore.TAB + ChatColor.GRAY + "fill");
                p.sendMessage(CommandCore.TAB + ChatColor.GRAY + "melt");
                p.sendMessage(CommandCore.TAB + ChatColor.GRAY + "smooth");
                return true;
            default:
                return false;
        }

        PlayerStates.getData(p).getTool().setModifier(newMod);
        p.sendMessage(CommandCore.TAG + " modifier set to " + ChatColor.YELLOW + s);
        return true;
    }
}
