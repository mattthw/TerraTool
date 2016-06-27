package edu.gatech.mmccoy37.GraffitiTools.Commands;

import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import edu.gatech.mmccoy37.GraffitiTools.Tools.DefaultTool;
import edu.gatech.mmccoy37.GraffitiTools.Tools.Tool;
import org.bukkit.entity.Player;

/**
 * Created by matt on 6/26/16.
 */
public class CommandTool {

    public static boolean setTool(Player p, String s) {

        Tool oldTool = PlayerStates.getData(p).getTool();
        Tool newTool;

        switch (s) {
            case ("default"):
                if (oldTool instanceof DefaultTool) {
                    return true;
                } else {
                    newTool = new DefaultTool();
                }
                break;
            case ("help"):
                p.sendMessage(CommandCore.TAG + " tool list:");
                p.sendMessage(CommandCore.TAB + "default");
                return true;
            default:
                return false;
        }
        newTool.setWandMaterial(oldTool.getWandMaterial());
        newTool.setBrush(oldTool.getBrush());
        newTool.setModifier(oldTool.getModifier());
        newTool.setBlockNew(oldTool.getBlockNew());
        newTool.setBlockOld(oldTool.getBlockOld());
        PlayerStates.getData(p).setTool(newTool);
        p.sendMessage(CommandCore.TAG + " tool set to " + s);
        return true;
    }
}
