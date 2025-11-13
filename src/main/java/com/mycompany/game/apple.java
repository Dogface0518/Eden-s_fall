//-- APPLE.java --
 
package com.mycompany.game;

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

//ATTACK METHODS
    public void bump(CharacterStats target){
        int energyCost = 1;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Bump, doing 7 damage!");
            target.takeDamage(bump);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void squash(CharacterStats target){
        int energyCost = 3;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Apple uses Squash, doing 9 damage!");
            target.takeDamage(squash);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        skill.evade(this, evadeFlag, 2);
    }
}
