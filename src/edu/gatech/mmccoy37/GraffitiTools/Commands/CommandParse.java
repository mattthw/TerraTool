package edu.gatech.mmccoy37.GraffitiTools.Commands;

import edu.gatech.mmccoy37.GraffitiTools.Brushes.Brush;
import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import edu.gatech.mmccoy37.GraffitiTools.Tools.Tool;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.Material.STICK;

/**
 * Created by matt on 5/14/16.
 */
public class CommandParse implements CommandExecutor {

    private static final List<String> COLORS = Arrays.asList(
        "RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "WHITE", "BLACK", "GRAY"
    );
    private static final String TAG = (ChatColor.AQUA + "Graffiti Tools" + ChatColor.YELLOW + ":" + ChatColor.RESET);
    private static final String TAB = "    ";
    private static final List<String> DIST_ALIAS = Arrays.asList(
            "DIST", "DISTANCE", "D"
    );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player)sender);
            if (PlayerStates.hasPlayer(p)) {
                Log.info(PlayerStates.getString());
                Tool tool = PlayerStates.getTool(p);
                Brush brush = tool.getBrush();
    //STATE: ENABLED
                if (args.length == 0) {
                //status
                    String material = brush.getNewMaterial().toString();
                    p.sendMessage(TAG + ChatColor.ITALIC + " Status");
                    p.sendMessage(TAB + " color:    " + ChatColor.YELLOW + material);
                    p.sendMessage(TAB + " size:      " + ChatColor.YELLOW + brush.getSize());
                    p.sendMessage(TAB + " dist:       " + ChatColor.YELLOW + tool.getDistance());
                    p.sendMessage(TAB + " enabled: " + ChatColor.YELLOW + PlayerStates.hasPlayer(p));
                } else if (args.length == 1) {
                //disable
                    if (args[0].equalsIgnoreCase("on")) {
                        PlayerStates.add(p);
                        p.sendMessage(TAG + " spraying toggled " + ChatColor.GREEN + "on.");
                    } else if (args[0].equalsIgnoreCase("off")) {
                        PlayerStates.remove(p);
                        p.sendMessage(TAG + " spraying toggled " + ChatColor.RED + "off.");
                    } else {
                //alt actions
                        if (PlayerStates.hasPlayer(p)) {
                    //give wand
                            if (args[0].equalsIgnoreCase("can")) {
                                if (p.getInventory().getItemInMainHand().getTypeId() == 0) {
                                    p.getInventory().setItemInMainHand(new ItemStack(STICK, 1));
                                } else {
                                    p.sendMessage(TAG + " please empty your hand first!");
                                }
                            } else {
                    //change color
                                args[0] = args[0].toUpperCase();
                                if (COLORS.contains(args[0])) {
                                    //Wands.setColor(p, args[0]);
                                    p.sendMessage(TAG + " color set to: " + args[0]);
                                } else {
                                    p.sendMessage(TAG + " Please use one of the following colors:\n"
                                            + COLORS.toString().replace("[", "").replace("]", ""));
                                }
                            }
                        } else {
                    //error
                            p.sendMessage(TAG + " unknown action. Check spelling.");
                        }
                    }
                } else if (args.length == 2) {
                //distance
                    if (args[0].equalsIgnoreCase("dist")) {
                        try {
                            int num = Integer.parseInt(args[1]);
                            if (num < 100 && num > 0) {
                                tool.setDistance(num);
                            } else {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e) {
                            p.sendMessage(TAG + " enter a numer from 1 to 100.");
                            return false;
                        }
                        p.sendMessage(TAG + " ditance set to " + ChatColor.YELLOW + args[1]);
                    } else if (args[0].equalsIgnoreCase("size")) {
                //size
                        try {
                            int num = Integer.parseInt(args[1]);
                            if (num < brush.MAX_SIZE && num > brush.MIN_SIZE) {
                                brush.setSize(num);
                            } else {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e) {
                            p.sendMessage(TAG + " enter a numer from " + brush.MIN_SIZE + " to " + brush.MAX_SIZE);
                            return false;
                        }
                        p.sendMessage(TAG + " size set to " + ChatColor.YELLOW + args[1]);
                    } else {
                //spelling error
                        p.sendMessage(TAG + " unknown action. Check spelling.");
                    }
                } else if (args.length > 2) {
        //ARGS ERROR
                    p.sendMessage(TAG + " unknown action. too many arguments.");
                }
            } else {
    //STATE: DISABLED
                if (args.length < 1) {
            //alert disabled
                    p.sendMessage(TAG + ChatColor.ITALIC + " Status");
                    p.sendMessage(TAB + " enabled: " + ChatColor.YELLOW + PlayerStates.hasPlayer(p));
                } else if (args.length == 1) {
            //enable
                    if (args[0].equalsIgnoreCase("on")) {
                        PlayerStates.add(p);
                        p.sendMessage(TAG + " spraying toggled " + ChatColor.GREEN + "on.");
                    } else if (args[0].equalsIgnoreCase("off")) {
                        PlayerStates.remove(p);
                        p.sendMessage(TAG + " spraying toggled " + ChatColor.RED + "off.");
                    } else {
            //alert disabled
                        p.sendMessage(TAG + " your can must be " + ChatColor.GREEN + "enabled" + ChatColor.RESET + " to do that.");
                    }
                }
            }
        }
        return true;
    }
}
