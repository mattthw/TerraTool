package edu.gatech.mmccoy37.GraffitiTools.Brushes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;

/**
 * Created by matt on 5/18/16.
 */
public class DefaultBrush extends Brush {

    private int size = 1;
    private Material newMaterial = Material.STONE;
    private byte data = (byte)-1;
    private ArrayList<Location> locs = new ArrayList<>();


    public void gather(Block targetBlock) {
        Location targetLocation = targetBlock.getLocation();

        //OLD method for checkif valid: '!VOID_BLOCKS.contains(targetBlock)'
        if (size == 1 && targetBlock.getType().isBlock()) {
            //size = 1
            regionAdd(targetLocation);
        } else if (size == 2) {
            //size = 2
            for (int x = targetLocation.getBlockX(); x < targetLocation.getBlockX() + size; x++) {
                for (int y = targetLocation.getBlockY(); y < targetLocation.getBlockY() + size; y++) {
                    for (int z = targetLocation.getBlockZ(); z < targetLocation.getBlockZ() + size; z++) {
                        Location l = new Location(targetBlock.getWorld(), (double)x, (double)y, (double)z);
                        if (targetBlock.getType().isBlock()) {
                            regionAdd(l);
                        }
                    }
                }
            }
        } else {
            //size > 2
            size--;
            int radius = (int)(size/2) + 1;
            //GET CUBE OF BLOCKS AROUND TARGET BLOCK
            for (int x = targetLocation.getBlockX() - radius; x < targetLocation.getBlockX() + radius; x++) {
                for (int y = targetLocation.getBlockY() - radius; y < targetLocation.getBlockY() + radius; y++) {
                    for (int z = targetLocation.getBlockZ() - radius; z < targetLocation.getBlockZ() + radius; z++) {
                        Location l = new Location(targetBlock.getWorld(), (double)x, (double)y, (double)z);
                        if (targetBlock.getType().isBlock() && targetLocation.distance(l) <= (size/2) ) {
                            regionAdd(l);
                        }
                    }
                }
            }
        }
    }

}
