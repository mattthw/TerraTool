package edu.gatech.mmccoy37.TerraTool.Data;

import edu.gatech.mmccoy37.TerraTool.Tools.DefaultTool;
import edu.gatech.mmccoy37.TerraTool.Tools.Tool;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by matt on 6/26/16.
 */
public class PlayerData {

    private Tool t = new DefaultTool();
    public Stack<HashMap<Location, BlockData>> changes = new Stack<>();

    public Tool getTool() {
        return this.t;
    }
    public void setTool(Tool t) {
        this.t = t;
    }
}
