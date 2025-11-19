//----GUIDE.JAVA----
package com.mycompany.game;
 
import java.util.Random;

class guide implements CharacterStats {
    private int hp;
    private int energy;
//attacks
    private int bless;// add og heal?
    private int smite; // add og stun?
    private boolean defend; // dmg reduction skill
//constructor
    public guide(int hp, int energy, int punch, int tackle) {
        this.hp       = hp;
        this.energy   = energy;
        this.bless    = bless;
        this.smite   = smite;
    }
 
    @Override
    public int getHP()     { return hp; }
    @Override
    public int getEnergy() { return energy; }
    @Override
    public void useEnergy(int amount) {
        energy -= amount;
        if (energy < 0) energy = 0;
    }
    public void gainEnergy(int amount) {
    energy += amount;
    if (energy > 5) {
        energy = 5;
       }
    }
    private boolean[] defendFlag = new boolean[]{false};
    @Override
    public void takeDamage(int amount) {
    if (defendFlag[0]) {
        amount /= 2; // reduce dmg by half
        defendFlag[0] = false;
        System.out.println("Guide's divine barrier blocked half the damage.");
    }
    hp -= amount; 
    if (hp < 0) {
        hp = 0; 
    }
}
//ATTACK METHODS
    public void bless(CharacterStats target){
        int energyCost = 1;
        Random random = new Random();  
        int blessDMG = random.nextInt(4) + 9;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Guide uses Bless, doing "+blessDMG+" damage!");
            target.takeDamage(blessDMG);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void smite(CharacterStats target){
        int energyCost = 2;
        Random random = new Random();  
        int smiteDMG = random.nextInt(3) + 5;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Guide uses Smite, doing "+smiteDMG+" damage!");
            target.takeDamage(smiteDMG);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void defend(){
        Skill.defend(this, defendFlag, 1);
    }
}