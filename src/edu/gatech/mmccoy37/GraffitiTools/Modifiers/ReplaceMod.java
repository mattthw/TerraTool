package edu.gatech.mmccoy37.GraffitiTools.Modifiers;

import edu.gatech.mmccoy37.GraffitiTools.Data.BlockData;
import edu.gatech.mmccoy37.GraffitiTools.Tools.Tool;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;

/**
 * Created by matt on 6/26/16.
 */
public class ReplaceMod extends Modifier {

    public static final String NAME = "replace";

    @Override
    public HashMap<Location, BlockData>
        modifyTargetSet(HashMap<Location, BlockData> blocks, Tool tool) {

        BlockData bNew = tool.getBlockNew();
        BlockData bOld = tool.getBlockOld();
        for (Location loc: blocks.keySet()) {

            if (loc != null && bOld != null && bNew != null) {
                Block block = loc.getBlock();
                if (block.getType() == bOld.getMaterial()) {
                    block.setType(bNew.getMaterial());
                    block.setData(bNew.getData());
                }

            }

        }
        return blocks;
    }

    public String getName() {
        return this.NAME;
    }
}
