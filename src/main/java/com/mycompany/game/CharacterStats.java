package com.mycompany.game;

public abstract class CharacterStats {

    private int hp;
    private int energy;
    private boolean evadeFlag;
    private boolean defendFlag;

    public CharacterStats(int hp, int energy) {
        this.hp = hp;
        this.energy = Math.min(energy, 5); // cap energy
        this.evadeFlag = false;
        this.defendFlag = false;
    }

    // HP
    public int getHP() { return hp; }
    public void takeDamage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }

    // Energy
    public int getEnergy() { return energy; }
    public void useEnergy(int amount) {
        energy -= amount;
        if (energy < 0) energy = 0;
    }
    public void gainEnergy(int amount) {
        energy += amount;
        if (energy > 5) energy = 5; // max energy cap
    }

    // Flags
    public void setEvadeFlag(boolean value) { evadeFlag = value; }
    public boolean getEvadeFlag() { return evadeFlag; }

    public void setDefendFlag(boolean value) { defendFlag = value; }
    public boolean getDefendFlag() { return defendFlag; }

    // Abstract: every character must implement
    public abstract String getName();
}


/*package com.mycompany.game;

interface CharacterStats {
    int getHP();
    int getEnergy();
    void takeDamage(int amount);
    void useEnergy(int amount);
    void gainEnergy(int amount);
    void setEvadeFlag(boolean value);
    boolean getEvadeFlag();
    void setDefendFlag(boolean value);
    boolean getDefendFlag();
}*/