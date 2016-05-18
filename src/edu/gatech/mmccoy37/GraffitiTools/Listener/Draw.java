package edu.gatech.mmccoy37.GraffitiTools.Listener;

import edu.gatech.mmccoy37.GraffitiTools.Brushes.Brush;
import edu.gatech.mmccoy37.GraffitiTools.Brushes.DefaultBrush;
import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import edu.gatech.mmccoy37.GraffitiTools.Tools.DefaultTool;
import edu.gatech.mmccoy37.GraffitiTools.Tools.Tool;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

import static edu.gatech.mmccoy37.GraffitiTools.Objects.VoidMaterials.VOID_BLOCKS;


/**
 * Created by matt on 5/14/16.
 */
public class Draw implements Listener {

    private static Map<String, DyeColor> CONVERSION;

    public Draw() {
        super();
        createColors();
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


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (PlayerStates.hasPlayer(player)) {

            //create tool abd brush to use
            Tool myTool = PlayerStates.getTool(player);
            Brush myBrush = myTool.getBrush();
            Block targetBlock = player.getTargetBlock(VOID_BLOCKS, myTool.getDistance());

            if (player.getInventory().getItemInMainHand().getType().equals(myTool.getWandMaterial())) {
                //return if targetblock too far away
                if (targetBlock.getLocation().distance(player.getLocation()) > myTool.getDistance()) {
                    return;
                }
                //get area being altered
                myBrush.gather(targetBlock);
                //change area
                myBrush.replace();

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
