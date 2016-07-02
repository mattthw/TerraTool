package edu.gatech.mmccoy37.TerraTool.Tools.Brushes;

import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.material.Tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by matt on 7/2/16.
 */
public class TreeBrush extends Brush {

    public static final boolean ALLOW_MODIFIERS = false;
    private int size;
    private int radius;
    private TreeType type;

//    public TreeBrush(String typeStr) {
//        super();
//        this.type = TreeType.valueOf(typeStr);
//    }

    public String getName() {
        return "Tree";
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
        this.radius = i/2;
    }
    public TreeType getType() {
        return this.type;
    }
    public void setType(TreeType type) {
        this.type = type;
    }


    public HashMap<Location, BlockData> getTargetSet(Block b) {
        HashMap<Location, BlockData> temp = new HashMap<>();
        for (double x = b.getX() - this.radius; x < b.getX() + this.radius; x++) {
            for (double y = b.getY() - this.radius; y < b.getY() + this.radius; y++) {
                for (double z = b.getZ() - this.radius; z < b.getZ() + this.radius; z++) {
                    Location l = new Location(b.getWorld(), x, y, z);
                    Location lUp = new Location(b.getWorld(), x, y + 1, z);
                    if ((l.getBlock().getType().equals(Material.DIRT)
                            || l.getBlock().getType().equals(Material.GRASS))
                                && lUp.getBlock().getType().equals(Material.AIR)) {
                        temp.put(l,new BlockData(l.getBlock().getType(), (byte)0));
                    }
                }
            }
        }
        return temp;
    }

    public boolean modifyTargetSet(HashMap<Location, BlockData> blocks) {
        for (Location l: blocks.keySet()) {
            if (l.getWorld().generateTree(l,type)) {
                HashMap<Location, BlockData> blacklist = getVoxels(l, 10, null);
                for (Location i : blacklist.keySet()) {
                    blocks.remove(i);
                }
            }
        }
        return true;
    }
}
