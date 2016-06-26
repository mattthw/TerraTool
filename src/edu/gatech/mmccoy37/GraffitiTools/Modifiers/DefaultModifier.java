package edu.gatech.mmccoy37.GraffitiTools.Modifiers;

import edu.gatech.mmccoy37.GraffitiTools.Data.BlockData;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;

/**
 * Created by matt on 6/26/16.
 */
public class DefaultModifier extends Modifier {

    @Override
    public HashMap<Location, BlockData>
        modifyTargetSet(HashMap<Location, BlockData> blocks, BlockData b) {
        for (Location loc: blocks.keySet()) {
            if (loc != null && b != null) {
                Block block = loc.getBlock();
                block.setType(b.getMaterial());
                block.setData(b.getData());
            }

        }
        return blocks;
    }
}
