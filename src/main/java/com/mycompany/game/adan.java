//----ADAN.JAVA----
package com.mycompany.game;

import java.util.Random;
 
class adan implements CharacterStats {
    Random random = new Random();  
    private int hp     = 40;
    private int energy = 5;
//attacks
    private int punch  = 5;
    private int tackle = 8; // add stun?
    private boolean defend; // dmg reduction skill
//constructor
    public adan(int hp, int energy, int punch, int tackle) {
        this.hp       = hp;
        this.energy   = energy;
        this.punch    = punch;
        this.tackle   = tackle;
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
        System.out.println("Adan blocked half of the damage with sheer willpower and strength.");
    }
    hp -= amount; 
    if (hp < 0) {
        hp = 0; 
    }
}
//ATTACK METHODS
    public void punch(CharacterStats target){
        int energyCost = 1;
        int punchDMG = random.nextInt(3) + 5;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Adan uses Punch Combo, doing "+punchDMG+" damage!");
            target.takeDamage(punchDMG);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void tackle(CharacterStats target){
        int energyCost = 3;
        int tackleDMG = random.nextInt(4) + 8;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Adan uses Tackle, doing "+tackleDMG+" damage!");
            target.takeDamage(tackleDMG);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void defend(){
        skill.defend(this, defendFlag, 2);
    }
}