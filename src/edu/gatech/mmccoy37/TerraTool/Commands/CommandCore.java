package edu.gatech.mmccoy37.TerraTool.Commands;

import edu.gatech.mmccoy37.TerraTool.Tools.Brushes.Brush;
import edu.gatech.mmccoy37.TerraTool.Data.BlockData;
import edu.gatech.mmccoy37.TerraTool.Data.PlayerData;
import edu.gatech.mmccoy37.TerraTool.Data.PlayerStates;
import edu.gatech.mmccoy37.TerraTool.Main;
import edu.gatech.mmccoy37.TerraTool.Tools.Modifiers.Modifier;
import edu.gatech.mmccoy37.TerraTool.Tools.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by matt on 5/14/16.
 */
public class CommandCore implements CommandExecutor {

    static final String TAG = (ChatColor.DARK_PURPLE + Main.PLUGIN_NAME + ChatColor.YELLOW + ":" + ChatColor.RESET);
    static final String TAB = "    ";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (sender instanceof Player &&
                sender.hasPermission(Main.PLUGIN_NAME.toLowerCase() + ".use")) {
            Player p = ((Player)sender);


            //STATE: ENABLED
            if (PlayerStates.hasPlayer(p)) {
                Log.info(PlayerStates.getString());
                Tool tool = PlayerStates.getData(p).getTool();
                Brush brush = tool.getBrush();
                Modifier mod = tool.getModifier();

                if (args.length == 0) {
                    //status
                    status(p);
                } else if (args.length == 1) {
                    //core comands
                    switch (args[0]) {
                        case ("on"):
                        case ("enable"):
                            break;
                        case ("off"):
                        case ("disable"):
                            PlayerStates.remove(p);
                            p.sendMessage(TAG + " spraying toggled " + ChatColor.RED + "off.");
                            break;
                        case ("t"):
                        case ("tool"):
                            p.sendMessage(TAG + " "
                                    + p.getInventory().getItemInMainHand().toString()
                                    + " is now tool # " + ChatColor.YELLOW + "2");
                            break;
                        case ("b"):
                        case ("brush"):
                            CommandBrush.setBrush(p, "help");
                            break;
                        case ("m"):
                        case ("mod"):
                            CommandMod.setMod(p, "help");
                            break;
                        case ("p"):
                        case ("place"):
                            CommandReplace.replace(p, tool, "help");
                            break;
                        case ("replace"):
                            CommandReplace.replace(p, tool, "help");
                            break;
                        case ("reload"):
                            break;
                        case ("undo"):
                            undo(p, 1);
                            p.sendMessage(TAG + " performed undo for last action");
                            break;
                        case ("status"):
                        default:
                            p.sendMessage(TAG + " unknown argument. check spelling.");
                            break;
                    }
                } else if (args.length == 2) {
                    //args 2 commands
                    switch(args[0]) {
                        case ("t"):
                        case ("tool"):
                            if (!CommandTool.setTool(p, args[1])) {
                                p.sendMessage(TAG + " could not find tool " + args[1]);
                            }
                            break;
                        case ("b"):
                        case ("brush"):
                            if (!CommandBrush.setBrush(p, args[1])) {
                                p.sendMessage(TAG + " could not find brush " + args[1]);
                            }
                            break;
                        case ("m"):
                        case ("mod"):
                            if (!CommandMod.setMod(p, args[1])) {
                                p.sendMessage(TAG + " could not find modifier " + args[1]);
                            }
                            break;
                        case ("p"):
                        case ("place"):
                            if (!CommandReplace.place(p, tool, args[1])) {
                                p.sendMessage(TAG + " could not find material " + args[1]);
                            }
                            break;
                        case ("r"):
                        case ("replace"):
                            if (!CommandReplace.replace(p, tool, args[1])) {
                                p.sendMessage(TAG + " could not find material " + args[1]);
                            }
                            break;
                        case ("s"):
                        case ("size"):
                            setSize(p, brush, args[1]);
                            break;
                        case ("undo"):
                            try {
                                int num = Integer.parseInt(args[1]);
                                if (num <= 10 && num > 0) {
                                    undo(p, num);
                                } else {
                                    throw new NumberFormatException();
                                }
                            } catch (NumberFormatException e) {
                                p.sendMessage(TAG + " enter a number from 1 to 10.");
                                return false;
                            }
                            p.sendMessage(TAG + " performed undo for past " + ChatColor.YELLOW + args[1] + ChatColor.RESET + " actions");
                            break;
                        default:
                            p.sendMessage(TAG + " unknown argument. check spelling.");
                            break;
                    }
                } else if (args.length == 3) {
                    switch (args[0]) {
                        case ("replace"):
                            if (!CommandReplace.replace(p, tool, args[1], args[2])) {
                                p.sendMessage(TAG + " could not find material " + args[1]);
                            }
                            break;
                        default:
                            p.sendMessage(TAG + " unknown action. too many arguments.");
                            break;
                    }
                }






                //STATE: DISABLED
            } else {
                //status
                if (args.length == 0 || args.length > 1) {
                    status(p);
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable")) {
                        PlayerStates.add(p);
                        p.sendMessage(TAG + " spraying toggled " + ChatColor.GREEN + "on.");
                    } else if (args[0].equalsIgnoreCase("off")) {
                        PlayerStates.remove(p);
                        p.sendMessage(TAG + " spraying toggled " + ChatColor.RED + "off.");
                    } else {
                        p.sendMessage(TAG + " your can must be " + ChatColor.GREEN + "enabled" + ChatColor.RESET + " to do that.");
                    }


                }
            }
        }
        return true;
    }

    private void setSize(Player p, Brush brush, String s) {
        //size
        try {
            int num = Integer.parseInt(s);
            if (num <= Brush.MAX_SIZE && num >= Brush.MIN_SIZE) {
                brush.setSize(num);
            } else {
                throw new NumberFormatException();
            }
            p.sendMessage(TAG + " size set to " + ChatColor.YELLOW + s);
        } catch (NumberFormatException e) {
            p.sendMessage(TAG + " enter a numer from " + Brush.MIN_SIZE + " to " + Brush.MAX_SIZE);
        }
    }

    public void status(Player p)  {

        p.sendMessage(TAG + ChatColor.ITALIC + " Status");
        if (PlayerStates.hasPlayer(p)) {
            p.sendMessage(TAB + " enabled:   " + ChatColor.GREEN + "yes");
        } else {
            p.sendMessage(TAB + " enabled:   " + ChatColor.RED + "no");
            return;
        }

        PlayerData pd = PlayerStates.getData(p);
        Tool tool = pd.getTool();
        Brush brush = pd.getTool().getBrush();
        Modifier mod = pd.getTool().getModifier();
        String newMat = tool.getBlockNew().getMaterial().name();

        p.sendMessage(TAB + " size:        " + ChatColor.GRAY + brush.getSize());
        p.sendMessage(TAB + " brush:     " + ChatColor.GRAY + brush.getName());
        p.sendMessage(TAB + " modifier:   " + ChatColor.GRAY + mod.getName());
        if (tool.getBlockOld() != null) {
            String oldMat = tool.getBlockOld().getMaterial().name();
            p.sendMessage(TAB + " block old: " + ChatColor.GRAY + oldMat.toLowerCase());
        }
        p.sendMessage(TAB + " block new: " + ChatColor.GRAY + newMat.toLowerCase());
    }

    private void undo(Player p, int count) {
        Stack<HashMap<Location, BlockData>> temp =
                PlayerStates.getData(p).changes;
        for (int i = 0; i < count && !temp.isEmpty(); i++) {
            HashMap<Location, BlockData> change = temp.pop();
            for (Location loc: change.keySet()) {
                if (loc != null) {
                    Block block = loc.getBlock();
                    block.setType(change.get(loc).getMaterial());
                    block.setData(change.get(loc).getData());
                }

            }
        }
    }
}
