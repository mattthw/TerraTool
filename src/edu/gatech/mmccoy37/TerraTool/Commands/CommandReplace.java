package edu.gatech.mmccoy37.TerraTool.Commands;

import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import edu.gatech.mmccoy37.TerraTool.Data.PlayerStates;
import edu.gatech.mmccoy37.TerraTool.Tools.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by matt on 6/26/16.
 */
public class CommandReplace {

    public static boolean place(Player p, Tool t, String mNew) {
        if (mNew.equals("help")) {
            p.sendMessage(CommandCore.TAG + " place " + ChatColor.YELLOW + "<new block>");
            p.sendMessage(CommandCore.TAB + " this will set the new material to <new block>");
            return true;
        }
        Material material = Material.matchMaterial(mNew);
        if (material == null)
            return false;
        BlockData b = new BlockData(material, (byte)0);
        PlayerStates.getData(p).getTool().setBlockNew(b);
        p.sendMessage(CommandCore.TAG + " block type set to " + ChatColor.YELLOW + mNew);
        return true;
    }

    public static boolean replace(Player p, Tool t, String mOld) {
        if (mOld.equals("help")) {
            p.sendMessage(CommandCore.TAG + " replace " + ChatColor.YELLOW + "<new block>");
            p.sendMessage(CommandCore.TAB + " this will set the block being replaced to <new block>");
            return true;
        }
        Material material = Material.matchMaterial(mOld);
        if (material == null)
            return false;
        BlockData b = new BlockData(material, (byte)0);
        PlayerStates.getData(p).getTool().setBlockOld(b);
        p.sendMessage(CommandCore.TAG + " block type set to " + ChatColor.YELLOW + mOld);
        return true;
    }

    public static boolean replace(Player p, Tool t, String mOld, String mNew) {
        if (mOld.equals("help")) {
            p.sendMessage(CommandCore.TAG + " replace <old block> <new block>");
            return true;
        }
        Material matNew = Material.matchMaterial(mNew);
        Material matOld = Material.matchMaterial(mOld);
        if (matNew == null || matOld == null)
            return false;
        PlayerStates.getData(p).getTool().setBlockNew(new BlockData(matNew, (byte)0));
        PlayerStates.getData(p).getTool().setBlockOld(new BlockData(matOld, (byte)0));
        p.sendMessage(CommandCore.TAG + " " + ChatColor.YELLOW + mNew + ChatColor.RESET + " will replace " + ChatColor.YELLOW +  mOld);
        return true;
    }
}
