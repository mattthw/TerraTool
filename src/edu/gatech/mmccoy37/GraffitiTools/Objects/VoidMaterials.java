package edu.gatech.mmccoy37.GraffitiTools.Objects;



import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by matt on 5/18/16.
 * materials the brush should ignore
 */
public class VoidMaterials {
    public static Set<Material> VOID_BLOCKS;


    private static  Set<Material> getInstance() {
        if (VOID_BLOCKS != null) {
            return VOID_BLOCKS;
        } else {
            createVoidBlocks();
        }
        return VOID_BLOCKS;
    }
    private VoidMaterials() {
    }

    private static void createVoidBlocks() {
        VOID_BLOCKS = new HashSet<>();
        VOID_BLOCKS.add(Material.AIR);
        VOID_BLOCKS.add(Material.LONG_GRASS);
        VOID_BLOCKS.add(Material.YELLOW_FLOWER);
        VOID_BLOCKS.add(Material.CHORUS_FLOWER);
        VOID_BLOCKS.add(Material.SNOW);
        VOID_BLOCKS.add(Material.WATER);
        VOID_BLOCKS.add(Material.PAINTING);
    }
}
