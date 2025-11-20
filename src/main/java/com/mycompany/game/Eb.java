//----EB.JAVA----
package com.mycompany.game;

import java.util.Random;
 
class Eb implements CharacterStats {
    private int hp;
    private int energy;
//actions
    private int lowkick;
    private int dropkick;
    private boolean evadeFlag = false;
//constructor
    public Eb(int hp, int energy, int lowkick, int dropkick) {
        this.hp       = hp;
        this.energy   = energy;
        this.lowkick  = lowkick;
        this.dropkick = dropkick;
    }
//setter
    @Override
    public int getHP()     { return hp; }
    @Override
    public int getEnergy() { return energy; }
    @Override
    public void takeDamage(int amount) {
        //after gigamit ang dodge:/ gibalhin na nakos lain class ang dodge og defend
        if(evadeFlag){
            evadeFlag = false;
            System.out.println("Eb dodged the attack with skillful acrobatics! 0 damage.");
            return;
        }
        hp -= amount; 
        if (hp < 0) { hp = 0; }
    }
 
    @Override
    public void useEnergy(int amount) {
        energy -= amount;
        if (energy < 0) energy = 0;
    }
    @Override
    public void gainEnergy(int amount) {
    energy += amount;
    if (energy > 5) {
        energy = 5;
       }
    }
    
    @Override
    public void setEvadeFlag(boolean value){ evadeFlag = value; }
    @Override
    public boolean getEvadeFlag(){ return evadeFlag; }
    @Override
    public void setDefendFlag(boolean value){}
    @Override
    public boolean getDefendFlag(){ return false; }
    
//ATTACK METHODS
    public void lowkick(CharacterStats target){
        int energyCost = 1;
        Random random = new Random();  
        int LkickDMG = random.nextInt(3) + lowkick;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Eb uses Low Kick, doing "+LkickDMG+" damage!");
            target.takeDamage(LkickDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void dropkick(CharacterStats target){
        int energyCost = 2;
        Random random = new Random();  
        int dropKDMG = random.nextInt(5) + dropkick;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Eb uses Drop Kick, doing "+dropKDMG+" damage!");
            target.takeDamage(dropKDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        Skill.evade(this, 2);
    }
}