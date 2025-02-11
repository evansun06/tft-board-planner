package model;

import exceptions.invalidLocationException;

// Represents a placeable entity that can be put on the
// game board. Ex Champions, Accessories, Additional Perks.
public abstract class Placeable {

    protected int locx;
    protected int locy;

    protected Boolean readilyPlaceable;
    
    //EFFECTS: Constructs an placeable object with no
    //         location determined.
    public Placeable(Boolean readilyPlaceable) {
        this.readilyPlaceable = readilyPlaceable;
    }

    protected void setLocation(int x, int y) {
        locx = x;
        locy = y;
    }


}
