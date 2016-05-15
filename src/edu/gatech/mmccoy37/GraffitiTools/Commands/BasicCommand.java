package edu.gatech.mmccoy37.GraffitiTools.Commands;

import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import edu.gatech.mmccoy37.GraffitiTools.Data.Wands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.Material.STICK;

/**
 * Created by matt on 5/14/16.
 */
public class BasicCommand implements CommandExecutor {

    private static final List<String> COLORS = Arrays.asList(
        "RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "WHITE", "BLACK", "GRAY"
    );
    private static final String TAG = (ChatColor.AQUA + "Graffiti Tools" + ChatColor.YELLOW + ":" + ChatColor.RESET);
    private static final String TAB = "    ";
    private static final List<String> DIST_ALIAS = Arrays.asList(
            "DIST", "DISTANCE", "D"
    );

    @Override
    public boolean onCommand(CommandSender p, Command command, String cmd, String[] args) {
        if (p instanceof Player) {
            if (args.length < 1) {
                //STATUS
                String col = (Wands.getColor((Player) p) == null)? "WHITE":Wands.getColor((Player)p);
                p.sendMessage(TAG + ChatColor.ITALIC + " Status");
                p.sendMessage(TAB + " color:    " + ChatColor.YELLOW + col);
                p.sendMessage(TAB + " size:      " + ChatColor.YELLOW + Wands.getSize((Player)p));
                p.sendMessage(TAB + " dist:       " + ChatColor.YELLOW + Wands.getDist((Player)p));
                p.sendMessage(TAB + " enabled: " + ChatColor.YELLOW + PlayerStates.isEnabled((Player) p));
            } else if (args.length == 1) {
                //ENABLE & DISABLE
                if (args[0].equalsIgnoreCase("on") && !PlayerStates.isEnabled((Player) p)) {
                    PlayerStates.enable(((Player) p));
                    p.sendMessage(TAG + " spraying toggled " + ChatColor.GREEN + "on.");
                } else if (args[0].equalsIgnoreCase("off")) {
                    PlayerStates.disable(((Player) p));
                    p.sendMessage(TAG + " spraying toggled " + ChatColor.RED + "off.");
                } else {
                    //ACTIONS
                    if (PlayerStates.isEnabled((Player) p)) {

                        //GIVE WAND
                        if (args[0].equalsIgnoreCase("can")) {
                            if (((Player) p).getInventory().getItemInMainHand().getTypeId() == 0) {
                                ((Player) p).getInventory().setItemInMainHand(new ItemStack(STICK, 1));
                            } else {
                                p.sendMessage(TAG + " please empty your hand first!");
                            }
                        } else {
                            //CHANGE COLOR
                            args[0] = args[0].toUpperCase();
                            if (COLORS.contains(args[0])) {
                                Wands.setColor((Player) p, args[0]);
                                p.sendMessage(TAG + " color set to: " + args[0]);
                            } else {
                                p.sendMessage(TAG + " Please use one of the following colors:\n"
                                        + COLORS.toString().replace("[", "").replace("]", ""));
                            }
                        }
                    } else {
                        p.sendMessage(TAG + " your can must be " + ChatColor.GREEN + "enabled" + ChatColor.RESET + " to do that.");
                    }
                }
            } else if (args.length == 2) {
                if (PlayerStates.isEnabled((Player) p) && args[0].equalsIgnoreCase("dist")) {
                    try {
                        int num = Integer.parseInt(args[1]);
                        if (num > 100 || num < 1) {
                            num = 15;
                            throw new NumberFormatException();
                        }
                        Wands.setDist((Player)p, num);
                    } catch (NumberFormatException e) {
                        p.sendMessage(TAG + " enter a numer from 1 to 100.");
                        return false;
                    }
                    p.sendMessage(TAG + " ditance set to " + ChatColor.YELLOW + args[1]);
                } else if (PlayerStates.isEnabled((Player) p) && args[0].equalsIgnoreCase("size")) {
                    try {
                        int num = Integer.parseInt(args[1]);
                        if (num > 10 || num < 1) {
                            num = 1;
                            throw new NumberFormatException();
                        }
                        Wands.setSize((Player)p, num);
                    } catch (NumberFormatException e) {
                        p.sendMessage(TAG + " enter a numer from 1 to 10.");
                        return false;
                    }
                    p.sendMessage(TAG + " size set to " + ChatColor.YELLOW + args[1]);
                }

            } else if (args.length > 2) {
                return false;
            }
        }
        return true;
    }
}
