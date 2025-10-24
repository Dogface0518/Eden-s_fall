//-- APPLE.java --
 
package com.mycompany.game;

class apple implements CharacterStats {
    private int hp     = 25;
    private int energy = 6;
    //attacks
    private int lowkick  = 7;//temp
    private int dropkick = 11;//temp
    private boolean evade;
//constructor
    public apple(int hp, int energy, int whip, int bite) {//temp change to  int whip, int bite
        this.hp     = hp;
        this.energy = energy;
        this.lowkick  = lowkick;//temp change to this.whip   = whip;
        this.dropkick  = dropkick;//temp change to this.bite   = bite;
    }
 //setter
    @Override
    public int getHP()      { return hp; }
    @Override
    public int getEnergy()  { return energy; }
 
    @Override
    public void takeDamage(int amount) {
        //after gigamit ang dodge:
        if(evade){
        evade = false;
        System.out.println("Apple dodged! 0 damage.");
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
    public void lowkick(CharacterStats target){
        int energyCost = 1;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Bump, doing 7 damage!");
            target.takeDamage(lowkick);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void dropkick(CharacterStats target){
        int energyCost = 3;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Squash, doing 9 damage!");
            target.takeDamage(dropkick);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        int energyCost = 2;
        if (energy >= energyCost) {
            useEnergy(energyCost);
            evade = true;
            System.out.println("Apple is rolling around aimlessly with incredible speed.");
        } else {
            System.out.println("Not enough energy to use this.");
        } 
    }
}
