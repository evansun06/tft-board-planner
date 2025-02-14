package model;

// Champion is a placeable template with dynamic stats such as damage
// but with static stats such as its cost. ChampionTemplates wil
public class ChampionTemplate extends Placeable {
    
    protected int health;
    protected int armour;
    protected int magicResist;
    protected int attackDamage;
    protected double attackSpeed;
    protected int abilityPower;
    protected int critChance;
    protected int critMultiplier;
    protected int range;

    protected final int cost;

    // EFFECTS: Constructs a ChampionTemplate with base stats and
    //          no location.
    //          Sets readilyPlaceble to true (superclass field)
    public ChampionTemplate(String name, int health, int armour, int mr, int ad, int ap, int critChance, 
                    int critX, double attackSpeed, int range, int cost) {

        super(true, name);
        setLocation(-1, -1);

        //Set Base Stats
        this.name = name;
        this.health = health;
        this.armour = armour;
        this.magicResist = mr;
        this.attackDamage = ad;
        this.abilityPower = ap;
        this.critChance = critChance;
        this.critMultiplier = critX;
        this.range = range;
        this.cost = cost;
    }

    // Getters

    public int getHealth() {
        return health;
    }

    public int getArmour() {
        return armour;
    }

    public int getMagicResist() {
        return magicResist;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getCritMultiplier() {
        return critMultiplier;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return this.cost;
    }
    
}
