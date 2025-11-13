//-- TANAS.java --
 
package com.mycompany.game;

class tanas implements CharacterStats {// Basta 6
    private int hp     = 26;
    private int energy = 6;
    //attacks
    private int whip  = 6;
    private int bite = 14;
    private boolean evade;//ka OP aning evade oy, 1 energy pagyud
//constructor
    public tanas(int hp, int energy, int whip, int bite) {
        this.hp     = hp;
        this.energy = energy;
        this.whip  = whip;
        this.bite  = bite;
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

//ATTACK METHODS
    public void whip(CharacterStats target){
        int energyCost = 1;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Tanas uses Whip, doing 6 damage!");
            target.takeDamage(whip);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void bite(CharacterStats target){
        int energyCost = 2;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Tanas uses Bite, doing 14 damage!");
            target.takeDamage(bite);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        skill.evade(this, evadeFlag, 1);
    }
}
