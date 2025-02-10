package model;

// Represents a placeable entity that can be put on the
// game board. Ex Champions, Accessories, Additional Perks.
public abstract class Placeable {
    protected String name;
    protected double health;
    protected int locx;
    protected int locy;

    
    //EFFECTS: Constructs an placeable with health and name.
    public Placeable(String name, double health) {
        this.name = name;
        this.health = health;
    }

    protected abstract void setLocation(int x, int y);


}
