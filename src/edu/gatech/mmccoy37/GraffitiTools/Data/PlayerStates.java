package edu.gatech.mmccoy37.GraffitiTools.Data;

import edu.gatech.mmccoy37.GraffitiTools.Brushes.Brush;
import edu.gatech.mmccoy37.GraffitiTools.Tools.DefaultTool;
import edu.gatech.mmccoy37.GraffitiTools.Tools.Tool;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 5/14/16.
 */

public class PlayerStates {
    private static ArrayList<Player> players = new ArrayList<>();
    private static Map<Player, Tool> activePlayers;
    private static PlayerStates ourInstance = new PlayerStates();

    public static PlayerStates getInstance() {
        return ourInstance;
    }

    private PlayerStates() {
    }


    //new methods

    public static void add(Player player) {
        if (activePlayers == null)
            activePlayers = new HashMap<>();
        activePlayers.putIfAbsent(player, null);
    }

    public static void remove(Player player) {
        if (activePlayers != null) {
            activePlayers.remove(player);
        }
    }

    public static void setTool(Player player, Tool tool) {
        if (activePlayers.get(player) == null) {
            add(player);
        }
        activePlayers.put(player, tool);
    }

    public static boolean hasPlayer(Player player) {

        if (activePlayers == null || activePlayers.get(player) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Tool getTool(Player player) {

        return activePlayers.getOrDefault(player, (Tool) new DefaultTool());
    }
}
