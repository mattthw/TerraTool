package edu.gatech.mmccoy37.GraffitiTools.Data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 5/14/16.
 */

public class PlayerStates {
    private static Map<Player, PlayerData> activePlayers;
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
        activePlayers.putIfAbsent(player, new PlayerData());
    }

    public static void remove(Player player) {
        if (activePlayers != null) {
            activePlayers.remove(player);
        }
    }

//    public static void setTool(Player player, Tool tool) {
//        if (activePlayers.get(player) == null) {
//            add(player);
//        }
//        activePlayers.put(player, tool);
//    }

    public static boolean hasPlayer(Player player) {

        if (activePlayers == null || player == null) {
            return false;
        } else {
            return activePlayers.containsKey(player);
        }
    }

    public static PlayerData getData(Player player) {
        return activePlayers.get(player);
    }

    public static String getString() {
        if (activePlayers != null)
            return activePlayers.toString();
        return "null";
    }
}
