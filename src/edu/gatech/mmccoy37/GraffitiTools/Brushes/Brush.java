package edu.gatech.mmccoy37.GraffitiTools.Brushes;

import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by matt on 5/18/16.
 */
public abstract class Brush {

    private int size;
    private Material newMaterial;
    private byte data;
    private ArrayList<Location> locs;
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 30;

    public abstract void setSize(int s);

    public abstract void setNewMaterial(Material mat);

    public abstract void setData(byte b);
    public abstract int getSize();

    public abstract Material getNewMaterial();

    public abstract byte getData();


    public abstract void regionAdd(Location location);
    public abstract ArrayList<Location> getRegion();

    public abstract void gather(Block targetBlock);

    public abstract void replace();
}
