//----STATUS.JAVA----
package com.mycompany.game;

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
}