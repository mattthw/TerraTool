package edu.gatech.mmccoy37.TerraTool;

import edu.gatech.mmccoy37.TerraTool.Commands.CommandCore;
import edu.gatech.mmccoy37.TerraTool.Data.PlayerStates;
import edu.gatech.mmccoy37.TerraTool.Listener.TerraEventListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by matt on 5/14/16.
 */
public class Main extends JavaPlugin {

    public static final String PLUGIN_NAME = "TerraTool";
    @Override
    public void onEnable() {
        super.onEnable();
        PlayerStates.getInstance();
//        VoidMaterials.
        this.getCommand("terratool").setExecutor(new CommandCore());
        getServer().getPluginManager().registerEvents(new TerraEventListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
