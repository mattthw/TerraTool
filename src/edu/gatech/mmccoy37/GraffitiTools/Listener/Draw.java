package edu.gatech.mmccoy37.GraffitiTools.Listener;

import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import edu.gatech.mmccoy37.GraffitiTools.Data.Wands;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

/**
 * Created by matt on 5/14/16.
 */
public class Draw implements Listener {

    private static Map<String, DyeColor> CONVERSION;
    private static Set<Material> VOID_BLOCKS;

    public Draw() {
        super();
        createColors();
        createVoidBlocks();
    }

    private static void createColors() {
        CONVERSION = new HashMap<>();
        CONVERSION.put("RED", DyeColor.RED);
        CONVERSION.put("ORANGE", DyeColor.ORANGE);
        CONVERSION.put("YELLOW", DyeColor.YELLOW);
        CONVERSION.put("GREEN", DyeColor.GREEN);
        CONVERSION.put("BLUE", DyeColor.BLUE);
        CONVERSION.put("PURPLE", DyeColor.PURPLE);
        CONVERSION.put("WHITE", DyeColor.WHITE);
        CONVERSION.put("BLACK", DyeColor.BLACK);
        CONVERSION.put("GRAY", DyeColor.GRAY);
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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (PlayerStates.isEnabled(player)) {
            if (player.getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
                String color = Wands.getColor(player);
                Location loc = player.getTargetBlock(VOID_BLOCKS, Wands.getDist(player)).getLocation();
                if (loc.distance(player.getLocation()) > Wands.getDist(player)) return;
                Block targetBlock = player.getTargetBlock(VOID_BLOCKS, Wands.getDist(player));
                double size = Wands.getSize(player);
                ArrayList<Location> cube = new ArrayList<>();

                if (size == 1) {
                    //size = 1
                    cube.add(loc);
                } else if (size == 2) {
                    //size = 2
                    for (int x = loc.getBlockX(); x < loc.getBlockX() + size; x++) {
                        for (int y = loc.getBlockY(); y < loc.getBlockY() + size; y++) {
                            for (int z = loc.getBlockZ(); z < loc.getBlockZ() + size; z++) {
                                Location l = new Location(targetBlock.getWorld(), (double)x, (double)y, (double)z);
                                if (!VOID_BLOCKS.contains(l.getBlock().getType())) {
                                    cube.add(l);
                                }
                            }
                        }
                    }
                } else {
                    //size > 2
                    size--;
                    int radius = (int)(size/2) + 1;
                    //GET CUBE OF BLOCKS AROUND TARGET BLOCK
                    for (int x = loc.getBlockX() - radius; x < loc.getBlockX() + radius; x++) {
                        for (int y = loc.getBlockY() - radius; y < loc.getBlockY() + radius; y++) {
                            for (int z = loc.getBlockZ() - radius; z < loc.getBlockZ() + radius; z++) {
                                Location l = new Location(targetBlock.getWorld(), (double)x, (double)y, (double)z);
                                if (!VOID_BLOCKS.contains(l.getBlock().getType()) && loc.distance(l) <= (size/2) ) {
                                    cube.add(l);
                                }
                            }
                        }
                    }
                }


                //PAINT BLOCKS
                for (Location l: cube) {
                    if (l != null) {
                        Block block = l.getBlock();
                        block.setType(Material.WOOL);
                        try {
                            block.setData(CONVERSION.get(color).getData());
                        } catch (NullPointerException e) {
                            //this is bad, but im too lazy to lookup byte codes for wool
                            //colors to use proper method block.getStatus.getData(...)
                        }
                    }

                }
            }
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (PlayerStates.isEnabled(player)) {
            if (player.getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
                event.setCancelled(true);
            }
        }
    }

}
