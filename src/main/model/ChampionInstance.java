package model;

import exceptions.InvalidLocationException;


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
    //          Givees the ChampionInstance a location
    public ChampionInstance(ChampionTemplate template, int x, int y) {
        super(template.getName(), template.getHealth(), template.getArmour(),
                template.getMagicResist(), template.getAttackDamage(), template.getAbilityPower(), 
                template.getCritChance(), template.getCritMultiplier(), template.getAttackSpeed(),
                template.getRange(), template.getCost()
        );
        setLocation(x, y);
        this.instanceId = nextId;
        nextId++;
    }


    //Getters
    public int getInstanceId () {
        return this.instanceId;
    }

    public int getNextId() {
        return nextId;
    }
}
