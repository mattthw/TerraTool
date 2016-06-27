package edu.gatech.mmccoy37.TerraTool.Tools.Modifiers;

import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import edu.gatech.mmccoy37.TerraTool.Tools.Tool;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;

/**
 * Created by matt on 6/26/16.
 */
public class PlaceMod extends Modifier {

    public static final String NAME = "place";

    @Override
    public HashMap<Location, BlockData>
        modifyTargetSet(HashMap<Location, BlockData> blocks, Tool tool) {

        BlockData bNew = tool.getBlockNew();
        for (Location loc: blocks.keySet()) {
            if (loc != null && bNew != null) {
                Block block = loc.getBlock();
                block.setType(bNew.getMaterial());
                block.setData(bNew.getData());
            }

        }
        return blocks;
    }

    public String getName() {
        return this.NAME;
    }
}
