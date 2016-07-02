package edu.gatech.mmccoy37.TerraTool.Commands;

import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.*;
import edu.gatech.mmccoy37.TerraTool.Data.PlayerStates;
import org.bukkit.ChatColor;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by matt on 6/26/16.
 */
public class CommandBrush {

    public static boolean setBrush(Player p, String s) {
        return setBrush(p, s, null);
    }

    public static boolean setBrush(Player p, String s, String[] args) {

        Brush oldBrush = PlayerStates.getData(p).getTool().getBrush();
        Brush newBrush;
        switch (s) {
            case ("ball"):
                if (oldBrush instanceof BallBrush) {
                    return true;
                } else {
                    newBrush = new BallBrush();
                }
                break;
            case ("cube"):
                if (oldBrush instanceof CubeBrush) {
                    return true;
                } else {
                    newBrush = new CubeBrush();
                }
                break;
            case ("disc"):
                if (oldBrush instanceof DiscBrush) {
                    return true;
                } else {
                    newBrush = new DiscBrush();
                }
                break;
            case ("plane"):
                if (oldBrush instanceof PlaneBrush) {
                    return true;
                } else {
                    newBrush = new PlaneBrush();
                }
                break;
            case ("tree"):
                if (oldBrush instanceof TreeBrush) {
                    return true;
                } else {
                    try {
                        TreeType temp = TreeType.valueOf(args[2]);
                        newBrush = new TreeBrush();
                        newBrush.setSize(oldBrush.getSize());
                        ((TreeBrush)newBrush).setType(temp);
                    } catch (Exception e) {
                        p.sendMessage(CommandCore.TAG + ChatColor.ITALIC + " usage:");
                        p.sendMessage(CommandCore.TAB + "/t b tree <tree-type>");
                        p.sendMessage(CommandCore.TAB + ChatColor.ITALIC + "types:");
                        ArrayList<String> names = new ArrayList<>();
                        for (TreeType t : TreeType.values()) {
                            names.add(t.name());
                        }
                        p.sendMessage(CommandCore.TAB + ChatColor.YELLOW + names);
                        return true;
                    }
                }
                break;
            case ("help"):
                p.sendMessage(CommandCore.TAG + ChatColor.ITALIC + " brush list:");
                p.sendMessage(CommandCore.TAB + "ball, disc, cube, plane, tree, " + ChatColor.GRAY + "smooth");
                return true;
            default:
                return false;
        }
        newBrush.setSize(oldBrush.getSize());
        PlayerStates.getData(p).getTool().setBrush(newBrush);
        p.sendMessage(CommandCore.TAG + " brush set to " + ChatColor.YELLOW +  s);
        return true;
    }
}
