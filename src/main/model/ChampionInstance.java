package model;

import exceptions.invalidLocationException;


// ChampionInstance is an extension of Champion that will represent a champion
// "placed" on the board. 
public class ChampionInstance extends ChampionTemplate {
    private int instanceId;
    private static int nextId = 1;

    // REQUIRES: Valid champion template within a set.
    // MODIFIES: this
    // EFFECTS: Creats a new base instance of a champion according to its template.
    //          Gives the champion a unique instance so multiple of the same champion
    //          can be added to the board.
    //          Increments nextId by 1.
    public ChampionInstance(ChampionTemplate template, int x, int y) {
        super(template.getName(), template.getHealth(), template.getArmour(),
                template.getMagicResist(),template.getAttackDamage(), template.getAbilityPower(),
                template.getCritChance(),template.getCritMultiplier(),
                template.getRange(),template.getCost()
        );
        setLocation(x, y);
        this.instanceId = nextId;
        nextId++;
    }

    @Override
    // REQUIRES: 0 <= x <= 6, 0 <= y <= 3.
    // MODIFIES: this
    // EFFECTS: Fufills implementation of Placeable and Champion. 
    //          Sets locx and locy in Placeable to the desired location.
    protected void setLocation(int x, int y) {
        super.locx = x;
        super.locy = y;
    }

    //Getters
    public int getInstanceId () {
        return this.instanceId;
    }

    public int getNextId() {
        return nextId;
    }
}
