package edu.gatech.mmccoy37.TerraTool.Tools.Modifiers;

import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import edu.gatech.mmccoy37.TerraTool.Tools.Tool;
import org.bukkit.Location;

import java.util.HashMap;

/**
 * Created by matt on 6/26/16.
 */
public abstract class Modifier {

    private static final String NAME = "Modifier";

    public abstract HashMap<Location, BlockData>
        modifyTargetSet(HashMap<Location, BlockData> blocks, Tool tool);

    public abstract String getName();

}
