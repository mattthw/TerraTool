package edu.gatech.mmccoy37.TerraTool.Data;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Created by matt on 6/26/16.
 */
public class BlockData {

    private Material type;
    private byte data;

    public BlockData(Material t, byte data) {
        setMaterial(t);
        setData(data);
    }

    public BlockData(Block b) {
        setData(b.getData());
        setMaterial(b.getType());
    }

    public void setMaterial(Material id) {
        this.type = id;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public Material getMaterial() {
        return this.type;
    }

    public byte getData() {
        return this.data;
    }
}
