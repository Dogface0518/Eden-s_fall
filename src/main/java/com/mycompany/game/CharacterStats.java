//----STATUS.JAVA----
 
package com.mycompany.game;
 
interface CharacterStats {
    int getHP();
    int getEnergy();
    void takeDamage(int amount);
    void useEnergy(int amount);
}
