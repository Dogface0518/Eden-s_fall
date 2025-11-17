//----APPLE.JAVA----
package com.mycompany.game;
 
import java.util.Random;
 
class apple implements CharacterStats {
    private int hp     = 28;
    private int energy = 6;
    //attacks
    private int bump  = 7;
    private int squash = 11;//add stun?
    private boolean evade;
//constructor
    public apple(int hp, int energy, int bump, int squash) {
        this.hp     = hp;
        this.energy = energy;
        this.bump   = bump;
        this.squash = squash;
    }
//setter
    @Override
    public int getHP()      { return hp; }
    @Override
    public int getEnergy()  { return energy; }
    private boolean[] evadeFlag = new boolean[]{false};
 
    @Override
    public void takeDamage(int amount) {
        if(evadeFlag[0]){
            evadeFlag[0] = false;
            System.out.println("Apple rolled around the attack with surprising speed! 0 damage.");
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
    public void bump(CharacterStats target){
        int energyCost = 1;
        Random random = new Random();  
        int bumpDMG = random.nextInt(3) + 7;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Bump, doing "+bumpDMG+" damage!");
            target.takeDamage(bumpDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void squash(CharacterStats target){
        int energyCost = 3;
        Random random = new Random();  
        int squashDMG = random.nextInt(4) + 11;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Squash, doing "+squashDMG+" damage!");
            target.takeDamage(squashDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        skill.evade(this, evadeFlag, 2);
    }
}