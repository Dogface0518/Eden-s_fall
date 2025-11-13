//---- GUIDE.JAVA ----
  
package com.mycompany.game;
 
class guide implements CharacterStats {
    private int hp     = 30;
    private int energy = 5;
//attacks
    private int bless  = 9;// add og heal oy
    private int smite = 5; // add og stun oy
    private boolean defend; // dmg reduction skill
 
//constructor
    public guide(int hp, int energy, int punch, int tackle) {
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
    
    private boolean[] defendFlag = new boolean[]{false};
    
    @Override
    public void takeDamage(int amount) {
    if (defendFlag[0]) {
        amount /= 2; // reduce dmg by half
        defendFlag[0] = false;
        System.out.println("Guide's divine barrier blocked half the damage.");
    }
    hp -= amount; 
    if (hp < 0) {
        hp = 0; 
    }
}
    
//ATTACK METHODS
    public void bless(CharacterStats target){
        int energyCost = 1;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Guide uses Bless, doing 9 damage!");
            target.takeDamage(bless);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void smite(CharacterStats target){
        int energyCost = 3;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Guide uses Smite, doing 5 damage!");
            target.takeDamage(smite);
        } else {
            System.out.println("Not enough energy to use this. Turn Skipped.");
        }
    }
    public void defend(){
        skill.defend(this, defendFlag, 1);
    }
}