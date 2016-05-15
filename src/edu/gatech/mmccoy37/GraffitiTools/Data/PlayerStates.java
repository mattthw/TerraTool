package edu.gatech.mmccoy37.GraffitiTools.Data;

import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by matt on 5/14/16.
 */

public class PlayerStates {
    private static ArrayList<Player> players;
    private static PlayerStates ourInstance=new PlayerStates();

    public static PlayerStates getInstance()
    {
        return ourInstance;
    }

    private PlayerStates() {
    }

    public static void enable(Player p) {
        if (players == null)
            players = new ArrayList<>();
        players.add(p);
        Log.info(players.toString());
    }

    public static void disable(Player p) {
        if (players == null)
            players = new ArrayList<>();
        players.remove(p);
        Log.info(players.toString());
    }

    public static boolean isEnabled(Player p) {
        if (p == null || players == null) {
            return false;
        }
        return players.contains(p);
    }
}
