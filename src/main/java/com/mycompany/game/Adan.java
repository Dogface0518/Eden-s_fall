//----ADAN.JAVA----
package com.mycompany.game;

import java.util.Random;
 
class Adan implements CharacterStats {
    private int hp;
    private int energy;
//actions
    private int punch;
    private int tackle; // add stun?
    private boolean defendFlag = false; // dmg reduction skill
//constructor
    public Adan(int hp, int energy, int punch, int tackle) {
        this.hp       = hp;
        this.energy   = energy;
        this.punch    = punch;
        this.tackle   = tackle;
    }
 //setter
    @Override
    public int getHP()     { return hp; }
    @Override
    public int getEnergy() { return energy; }
    @Override
    public void useEnergy(int amount) {
        energy -= amount;
        if (energy < 0) energy = 0;
    }
    @Override
    public void gainEnergy(int amount) {
    energy += amount;
    if (energy > 5) { energy = 5; }
    }
    
    @Override
    public void takeDamage(int amount) {
        if (defendFlag) {
            amount /= 2; // reduce dmg by half
            defendFlag = false;
            System.out.println("Adan blocked half of the damage with sheer willpower and strength.");
        }
        hp -= amount; 
        if (hp < 0) { hp = 0; }
    }
    
    @Override
    public void setDefendFlag(boolean value){ defendFlag = value; }
    @Override
    public boolean getDefendFlag(){ return defendFlag; }
    @Override
    public void setEvadeFlag(boolean value){}
    @Override
    public boolean getEvadeFlag(){ return false; }
    
//ATTACK METHODS
    public void punch(CharacterStats target){
        int energyCost = 1;
        Random random = new Random(); 
        int punchDMG = random.nextInt(3) + punch;
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
        Random random = new Random(); 
        int tackleDMG = random.nextInt(4) + tackle;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Adan uses Tackle, doing "+tackleDMG+" damage!");
            target.takeDamage(tackleDMG);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void defend(){
        Skill.defend(this, 2);
    }
}