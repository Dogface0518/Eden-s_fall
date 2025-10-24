//---- ADAN.JAVA ----
  
package com.mycompany.game;
 
class adan implements CharacterStats {
    private int hp     = 35;
    private int energy = 6;
//attacks
    private int punch  = 5;
    private int tackle = 8; // stun
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
    @Override
    public void takeDamage(int amount) {
    if (defend) {
        amount /= 2; // reduce dmg by half
        defend = false;
        System.out.println("Adan blocked half of the damage.");
    }
    hp -= amount; 
    if (hp < 0) {
        hp = 0; 
    }
}
 
    
//ATTACK METHODS
    public void punch(CharacterStats target){
        int energyCost = 1;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Adan uses Punch Combo, doing 5 damage!");
            target.takeDamage(punch);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void tackle(CharacterStats target){
        int energyCost = 3;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Adan uses Tackle, doing 8 damage!");
            target.takeDamage(tackle);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void defend(){
        int energyCost = 2;
        if (energy >= energyCost) {
            useEnergy(energyCost);
            defend = true;
            System.out.println("Eb is preparing to defend himself.");
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        } 
    }
 
 
}