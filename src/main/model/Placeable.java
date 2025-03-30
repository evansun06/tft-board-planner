package model;

import exceptions.InvalidLocationException;

// Represents a placeable entity that can be put on the
// game board. Ex Champions, Accessories, Additional Perks.
public abstract class Placeable {

    protected String name;
    protected int locx;
    protected int locy;

    protected Boolean readilyPlaceable;
    
    //EFFECTS: Constructs an placeable object with no
    //         location determined.
    public Placeable(Boolean readilyPlaceable, String name) {
        locx = -1;
        locy = -1;
        this.name = name;
        this.readilyPlaceable = readilyPlaceable;
    }

    // REQUIRES: 0 <= x <= 6, 0 <= y <= 3.
    // MODIFIES: this
    // EFFECTS: Sets locx and locy of a  Placeable to the desired location.
    public void setLocation(int x, int y) {
        EventLog.getInstance().logEvent(new Event("Moved" + name + " from (" + locx + ", " + locy + ")"
                + " to (" + x + ", " + y + ")"));
        locx = x;
        locy = y;
    }

    // EFFECT: set readilyPlaceable
    public void setReadilyPlaceable(Boolean b) {
        this.readilyPlaceable = b;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public int getX() {
        return this.locx;
    }

    public int getY() {
        return this.locy;
    }


}
