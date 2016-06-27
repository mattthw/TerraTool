package edu.gatech.mmccoy37.TerraTool.Commands;

import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.Brush;
import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.CubeBrush;
import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.BallBrush;
import edu.gatech.mmccoy37.TerraTool.Data.PlayerStates;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by matt on 6/26/16.
 */
public class CommandBrush {

    public static boolean setBrush(Player p, String s) {

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
            case ("help"):
                p.sendMessage(CommandCore.TAG + " brush list:");
                p.sendMessage(CommandCore.TAB + "ball, cube");
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
