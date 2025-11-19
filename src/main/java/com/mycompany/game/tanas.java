//----TANAS.JAVA----
package com.mycompany.game;

import java.util.Random;
 
class tanas implements CharacterStats {  // Basta 6
 
    private int hp;
    private int energy;
    private int whip;
    private int bite;
    private boolean[] evadeFlag = new boolean[]{false};
 
    // Constructor (if you want to allow dynamic initialization)
    public tanas(int hp, int energy, int whip, int bite) {
        this.hp = hp;
        this.energy = energy;
        this.whip = whip;
        this.bite = bite;
    }
 
    // Getter methods
    @Override
    public int getHP() {
        return hp;
    }
 
    @Override
    public int getEnergy() {
        return energy;
    }
 
    // Damage taking logic
    @Override
    public void takeDamage(int amount) {
        if (evadeFlag[0]) {
            evadeFlag[0] = false;
            System.out.println("Tanas dodged the attack with graceful flight! 0 damage.");
            return;
        }
        hp -= amount;
        if (hp < 0) {
            hp = 0;
        }
    }
 
    @Override
    public void useEnergy(int amount) {
        energy -= amount;
        if (energy < 0) energy = 0;
    }
 
    // Method to gain energy
    public void gainEnergy(int amount) {
        energy += amount;
        if (energy > 5) {
            energy = 5;
        }
    }
 
    // ATTACK METHODS
    public void whip(CharacterStats target) {
        int energyCost = 1;
        Random random = new Random();  
        int whipDMG = random.nextInt(3) + 6;
        if (energy >= energyCost) {
            useEnergy(energyCost);
            System.out.println("Tanas uses Whip, doing "+whipDMG+" damage!");
            target.takeDamage(whipDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
 
    public void bite(CharacterStats target) {
        int energyCost = 2; 
        Random random = new Random();  
        int biteDMG = random.nextInt(5) + 14;
 
        if (energy >= energyCost) {
            useEnergy(energyCost);  
            System.out.println("Tanas uses Bite, doing "+biteDMG+" damage!");
            target.takeDamage(biteDMG);  
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        Skill.evade(this, evadeFlag, 1);
    }
}