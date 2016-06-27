package edu.gatech.mmccoy37.GraffitiTools.Brushes;

import edu.gatech.mmccoy37.GraffitiTools.Data.BlockData;
import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matt on 5/18/16.
 */
public abstract class Brush {

    private int size;
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 30;
    public static final boolean ALLOW_MODIFIERS = true;

    public abstract void setSize(int s);


    public abstract int getSize();


    public abstract HashMap<Location, BlockData> getTargetSet(Block targetBlock);

    public abstract String getName();

}
