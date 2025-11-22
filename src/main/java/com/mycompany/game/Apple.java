//----APPLE.JAVA----
package com.mycompany.game;

public class Apple extends CharacterStats {

    public Apple(int hp, int energy) {
        super(hp, energy);
    }

    @Override
    public String getName() {
        return "Apple";
    }

    public String bump(CharacterStats target) {
        int cost = 1;
        if (getEnergy() < cost) return getName() + " tried to Bump but lacked energy!";
        useEnergy(cost);
        return Skill.handleIncomingDamage(target, 4, getName());
    }

    public String squash(CharacterStats target) {
        int cost = 2;
        if (getEnergy() < cost) return getName() + " tried to Squash but lacked energy!";
        useEnergy(cost);
        return Skill.handleIncomingDamage(target, 8, getName());
    }
}


/*package com.mycompany.game;
 
import java.util.Random;
 
class Apple implements CharacterStats {
    private int hp;
    private int energy;
//actions
    private int bump;
    private int squash;//add stun?
    private boolean evadeFlag = false;
//constructor
    public Apple(int hp, int energy, int bump, int squash) {
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
 
    @Override
    public void takeDamage(int amount) {
        if(evadeFlag){
            evadeFlag = false;
            System.out.println("Apple rolled around the attack with surprising speed! 0 damage.");
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
    public void bump(CharacterStats target){
        int energyCost = 1;
        Random random = new Random();  
        int bumpDMG = random.nextInt(3) + bump;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Bump, doing "+bumpDMG+" damage!");
            target.takeDamage(bumpDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void squash(CharacterStats target){
        int energyCost = 2;
        Random random = new Random();  
        int squashDMG = random.nextInt(4) + squash;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Squash, doing "+squashDMG+" damage!");
            target.takeDamage(squashDMG);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        Skill.evade(this, 2);
    }
}*/