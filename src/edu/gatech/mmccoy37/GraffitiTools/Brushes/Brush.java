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

    public void setSize(int s) {
        this.size = s;
    }

    public void setNewMaterial(Material mat) {
        this.newMaterial = mat;
    }

    public void setData(byte b) {
        this.data = b;
    }

    public int getSize() {
        return size;
    }

    public Material getNewMaterial() {
        return newMaterial;
    }

    public byte getData() {
        return this.data;
    }


    public void regionAdd(Location location) {
        locs.add(location);
    }
    public ArrayList<Location> getRegion() {
        return locs;
    }

    public abstract void gather(Block targetBlock);

    public void replace() {
        for (Location loc: getRegion()) {
            if (loc != null) {
                Block block = loc.getBlock();
                block.setType(this.getNewMaterial());
                //if has metadata then set metadata
                if (this.getData() > 0) {
                    block.setData(this.getData());
                }
            }

        }
    }
}
