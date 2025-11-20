//----GUIDE.JAVA----
package com.mycompany.game;
 
import java.util.Random;

class Guide implements CharacterStats {
    private int hp;
    private int energy;
//actions
    private int bless;// add og heal?
    private int smite; // add og stun?
    private boolean defendFlag = false;
//constructor
    public Guide(int hp, int energy, int bless, int smite) {
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
    @Override
    public void gainEnergy(int amount) {
    energy += amount;
    if (energy > 5) { energy = 5; }
    }
    
    
    
    @Override
    public void takeDamage(int amount){
        if (defendFlag) {
            amount /= 2; // reduce dmg by half
            defendFlag = false;
            System.out.println("Guide's divine barrier blocked half the damage.");
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
    public void bless(CharacterStats target){
        int energyCost = 1;
        Random random = new Random();  
        int blessDMG = random.nextInt(4) + bless;
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
        int smiteDMG = random.nextInt(3) + smite;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Guide uses Smite, doing "+smiteDMG+" damage!");
            target.takeDamage(smiteDMG);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void defend(){
        Skill.defend(this, 1);
    }
}