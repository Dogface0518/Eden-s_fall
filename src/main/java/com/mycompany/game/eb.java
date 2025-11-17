//----EB.JAVA----
package com.mycompany.game;

import java.util.Random;
 
class eb implements CharacterStats {
    private int hp       = 30;
    private int energy   = 7;
//attacks
    private int lowkick  = 7;
    private int dropkick = 15;
    private boolean evade;
//constructor
    public eb(int hp, int energy, int lowkick, int dropkick) {
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
    private boolean[] evadeFlag = new boolean[]{false};
 
    @Override
    public void takeDamage(int amount) {
        //after gigamit ang dodge:/ gibalhin na nakos lain class ang dodge og defend
        if(evadeFlag[0]){
            evadeFlag[0] = false;
            System.out.println("Eb dodged the attack with skillful acrobatics! 0 damage.");
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
    public void gainEnergy(int amount) {
    energy += amount;
    if (energy > 5) {
        energy = 5;
       }
    }
 
//ATTACK METHODS
    public void lowkick(CharacterStats target){
        int energyCost = 1;
        Random random = new Random();  
        int LkickDMG = random.nextInt(3) + 7;
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
        int dropKDMG = random.nextInt(5) + 15;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Eb uses Drop Kick, doing "+dropKDMG+" damage!");
            target.takeDamage(dropKDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        skill.evade(this, evadeFlag, 2);
    }
}