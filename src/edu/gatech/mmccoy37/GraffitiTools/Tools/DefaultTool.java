package edu.gatech.mmccoy37.GraffitiTools.Tools;

import edu.gatech.mmccoy37.GraffitiTools.Brushes.Brush;
import edu.gatech.mmccoy37.GraffitiTools.Brushes.DefaultBrush;
import org.bukkit.Material;

/**
 * Created by matt on 5/18/16.
 */
public class DefaultTool extends Tool{
    private int distance = 30;
    private Material item = Material.STICK;
    private Brush brush = new DefaultBrush();

    public void setWandMaterial(Material item) {
        this.item = item;
    }

    public Material getWandMaterial() {
        return this.item;
    }

    public void setDistance(int dist) {
        this.distance = dist;
    }

    public int getDistance() {
        return this.distance;
    }

    public Brush getBrush() {
        return brush;
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
    }
}
