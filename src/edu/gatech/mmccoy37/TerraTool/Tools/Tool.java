package edu.gatech.mmccoy37.TerraTool.Tools;

import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.Brush;
import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import edu.gatech.mmccoy37.TerraTool.Tools.Modifiers.Modifier;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by matt on 5/18/16.
 */
public abstract class Tool {


    public abstract void setWandMaterial(Material item);

    public abstract Material getWandMaterial();

    public abstract void setDistance(int dist);

    public abstract int getDistance();

    public abstract Brush getBrush();

    public abstract void setBrush(Brush brush);

    public abstract void setModifier(Modifier mod);

    public abstract Modifier getModifier();

    public abstract void setBlockOld(BlockData b);

    public abstract void setBlockNew(BlockData b);

    public abstract BlockData getBlockOld();

    public abstract BlockData getBlockNew();

    public abstract boolean action(Player player, Block target);

}
