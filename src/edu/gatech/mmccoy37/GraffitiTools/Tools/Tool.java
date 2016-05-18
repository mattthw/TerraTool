package edu.gatech.mmccoy37.GraffitiTools.Tools;

import edu.gatech.mmccoy37.GraffitiTools.Brushes.Brush;
import edu.gatech.mmccoy37.GraffitiTools.Brushes.DefaultBrush;
import org.bukkit.Material;

/**
 * Created by matt on 5/18/16.
 */
public abstract class Tool {


    public abstract void setWandMaterial(Material item);

    public abstract Material getWandMaterial();

    public abstract void setDistance(int dist);

    public abstract int getDistance();

    public abstract Brush getBrush();

    public abstract void setBrush(Brush brush);
}
