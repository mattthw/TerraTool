package edu.gatech.mmccoy37.GraffitiTools;

import edu.gatech.mmccoy37.GraffitiTools.Commands.BasicCommand;
import edu.gatech.mmccoy37.GraffitiTools.Data.PlayerStates;
import edu.gatech.mmccoy37.GraffitiTools.Data.Wands;
import edu.gatech.mmccoy37.GraffitiTools.Listener.Draw;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by matt on 5/14/16.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        PlayerStates.getInstance();
        Wands.getInstance();
        this.getCommand("graffititools").setExecutor(new BasicCommand());
        getServer().getPluginManager().registerEvents(new Draw(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
