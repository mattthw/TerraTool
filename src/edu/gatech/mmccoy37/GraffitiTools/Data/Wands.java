package edu.gatech.mmccoy37.GraffitiTools.Data;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by matt on 5/14/16.
 */

public class Wands {
    private static HashMap<Player, Object[]> playerWands = new HashMap<>();
    private static Wands ourInstance = new Wands();

    public static Wands getInstance()
    {
        return ourInstance;
    }

    private Wands() {
    }


    public static void setColor(Player p, String c) {
        Object[] o = playerWands.get(p);
        if (o == null) {
            o = new Object[]{c, 15, 1};
        } else {
            o[0] = c;
        }
        playerWands.put(p, o);
    }

    public static void setDist(Player p, int d) {
        Object[] o = playerWands.get(p);
        if (o == null) {
            o = new Object[]{"WHITE", d, 1};
        } else {
            o[1] = d;
        }
        playerWands.put(p, o);
    }

    public static void setSize(Player p, int s) {
        Object[] o = playerWands.get(p);
        if (o == null) {
            o = new Object[]{"WHITE", 15, s};
        } else {
            o[2] = s;
        }
        playerWands.put(p, o);
    }



    public static String getColor(Player p) {
        if (p == null || playerWands == null) {
            return null;
        }
        Object[] entry = playerWands.get(p);
        if (entry == null) {
            return "blue";
        } else {
            return (String)entry[0];
        }
    }

    public static int getDist(Player p) {
        if (p == null || playerWands == null) {
            return -1;
        }
        Object[] entry = playerWands.get(p);
        if (entry == null) {
            return 50;
        } else {
            return (int)entry[1];
        }
    }

    public static int getSize(Player p) {
        if (p == null || playerWands == null) {
            return -1;
        }
        Object[] entry = playerWands.get(p);
        if (entry == null) {
            return 1;
        } else {
            return (int)entry[2];
        }
    }
}
