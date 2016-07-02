package edu.gatech.mmccoy37.TerraTool.Tools.Brushes;

import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matt on 5/18/16.
 */
public abstract class Brush {

    private int size;
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 50;
    public static final boolean ALLOW_MODIFIERS = true;

    public abstract void setSize(int s);


    public abstract int getSize();


    public abstract HashMap<Location, BlockData> getTargetSet(Block targetBlock);

    public boolean modifyTargetSet(HashMap<Location, BlockData> blocks) {
        return false;
    }

    public abstract String getName();

    public HashMap<Location, BlockData> getVoxels(Location l, int size, ArrayList<Material> whitelist) {
        HashMap<Location, BlockData> temp = new HashMap<>();
        int radius = (size / 2);
        for (double x = l.getX() - radius; x < l.getX() + radius; x++) {
            for (double y = l.getY() - radius; y < l.getY() + radius; y++) {
                for (double z = l.getZ() - radius; z < l.getZ() + radius; z++) {
                    if (whitelist == null) {
                        temp.put(l, new BlockData(l.getBlock().getType(), (byte)0));
                    } else if (whitelist.contains(l.getBlock().getType())) {
                        temp.put(l,new BlockData(l.getBlock().getType(), (byte)0));
                    }
                }
            }
        }
        return temp;
    }


}
