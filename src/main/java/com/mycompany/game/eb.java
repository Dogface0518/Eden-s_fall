//----- EB.JAVA -----
  
package com.mycompany.game;
 
class eb implements CharacterStats {
    private int hp       = 25;
    private int energy   = 6;
//attacks
    private int lowkick  = 7;
    private int dropkick = 11;
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
 

    @Override
    public void takeDamage(int amount) {
        //after gigamit ang dodge:
        if(evade){
        evade = false;
        System.out.println("Ib dodged! 0 damage.");
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
            System.out.println("Eb uses Low Kick, doing 7 damage!");
            target.takeDamage(lowkick);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void dropkick(CharacterStats target){
        int energyCost = 3;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Eb uses Drop Kick, doing 9 damage!");
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
            System.out.println("Eb is prepared and ready to dodge any attack.");
        } else {
            System.out.println("Not enough energy to use this.");
        } 
    }
 
}