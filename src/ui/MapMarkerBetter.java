package ui;

import filters.*;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import twitter4j.Status;

import java.awt.*;

public class MapMarkerBetter extends MapMarkerSimple{
    private Color markerColor = Color.gray;
    private double markerSize = 5.0;

    public MapMarkerBetter(Layer layer, Coordinate coord) {
        super(layer, coord);
        setFont(new Font("TimesRoman", Font.BOLD, 5));
        setColor(markerColor);
    }

    public void setMarkerColorAndFilter(Status s, AndFilter filter){
        if(filter.matches(s)){
            this.markerColor = Color.magenta;
        }
    }

    public void setMarkerColorOrFilter(Status s, OrFilter filter){
        if(filter.matches(s)){
            this.markerColor = Color.green;
        }
    }

    public void setMarkerColorNotFilter(Status s, BasicFilter filter){
        if(filter.matches(s)){
            this.markerColor = Color.yellow;
        }
    }

    public void setMarkerColorBasicFilter(Status s, NotFilter filter){
        if(filter.matches(s)){
            this.markerColor = Color.blue;
        }
    }
}
