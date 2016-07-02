package edu.gatech.mmccoy37.TerraTool.Tools;

import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.Brush;
import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.BallBrush;
import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import edu.gatech.mmccoy37.TerraTool.Data.PlayerStates;
import edu.gatech.mmccoy37.TerraTool.Tools.Modifiers.PlaceMod;
import edu.gatech.mmccoy37.TerraTool.Tools.Modifiers.Modifier;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by matt on 5/18/16.
 */
public class DefaultTool extends Tool {
    private int distance = 100;
    private Material item = Material.STICK;
    private Brush brush = new BallBrush();
    private Modifier mod = new PlaceMod();
    private BlockData bOld;
    private BlockData bNew = new BlockData(Material.STONE, (byte)0);



    public Modifier getModifier() {
        return mod;
    }

    public void setModifier(Modifier mod) {
        this.mod = mod;
    }

    public void setBlockOld(BlockData b) {
        this.bOld = b;
    }
    public void setBlockNew(BlockData b) {
        this.bNew = b;
    }
    public BlockData getBlockOld() {
        return this.bOld;
    }
    public BlockData getBlockNew() {
        return this.bNew;
    }

    public void setWandMaterial(Material item) {
        this.item = item;
    }

    public Material getWandMaterial() {
        return this.item;
    }

    public void setDistance(int dist) {
        this.distance = dist;
    }

    public int getDistance() {
        return this.distance;
    }

    public Brush getBrush() {
        return brush;
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
    }

    public boolean action(Player player, Block target) {
        Stack<HashMap<Location, BlockData>> temp =
                PlayerStates.getData(player).changes;
        temp.push(getBrush().getTargetSet(target));
        if (!getBrush().modifyTargetSet(temp.peek())) {
            getModifier().modifyTargetSet(temp.peek(), this);
        }

        return true;
    }
}
