//----ADAN.JAVA----
package com.mycompany.game;

import java.util.Random;
 
class Adan implements CharacterStats {
    Random random = new Random();  
    private int hp;
    private int energy;
//attacks
    private int punch;
    private int tackle; // add stun?
    private boolean defend; // dmg reduction skill
//constructor
    public Adan(int hp, int energy, int punch, int tackle) {
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
        int energyCost = 2;
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
        Skill.defend(this, defendFlag, 2);
    }
}