package edu.gatech.mmccoy37.GraffitiTools.Brushes;

import edu.gatech.mmccoy37.GraffitiTools.Data.BlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matt on 5/18/16.
 */
public class DefaultBrush extends Brush {

    private int size = 1;

    public void setSize(int s) {
        this.size = s;
    }


    public int getSize() {
        return size;
    }


//    public void replace() {
//        for (Location loc: getRegion()) {
//            if (loc != null) {
//                Block block = loc.getBlock();
//                block.setType(this.getNewMaterial());
//                //if has metadata then set metadata
//                if (this.getData() > 0) {
//                    block.setData(this.getData());
//                }
//            }
//
//        }
//    }

    public HashMap<Location, BlockData> getTargetSet(Block target) {
        Location targetLocation = target.getLocation();
        HashMap<Location, BlockData> temp = new HashMap<>();
        //OLD method for check if valid: '!VOID_BLOCKS.contains(targetBlock)'
        if (size == 1 && target.getType().isBlock()) {
            //size = 1
            temp.put(targetLocation, new BlockData(target));
        } else if (size == 2) {
            //size = 2
            for (int x = targetLocation.getBlockX(); x < targetLocation.getBlockX() + size; x++) {
                for (int y = targetLocation.getBlockY(); y < targetLocation.getBlockY() + size; y++) {
                    for (int z = targetLocation.getBlockZ(); z < targetLocation.getBlockZ() + size; z++) {
                        Location loc = new Location(target.getWorld(), (double)x, (double)y, (double)z);
                        if (target.getType().isBlock()) {
                            temp.put(loc, new BlockData(loc.getBlock()));
                        }
                    }
                }
            }
        } else {
            int radius = (int)(size/2) + 1;
            //GET CUBE OF BLOCKS AROUND TARGET BLOCK
            for (int x = targetLocation.getBlockX() - radius; x < targetLocation.getBlockX() + radius; x++) {
                for (int y = targetLocation.getBlockY() - radius; y < targetLocation.getBlockY() + radius; y++) {
                    for (int z = targetLocation.getBlockZ() - radius; z < targetLocation.getBlockZ() + radius; z++) {
                        Location loc = new Location(target.getWorld(), (double)x, (double)y, (double)z);
                        if (target.getType().isBlock() && targetLocation.distance(loc) <= (size/2) ) {
                            temp.put(loc, new BlockData(loc.getBlock()));
                        }
                    }
                }
            }
        }
        return temp;
    }

}
