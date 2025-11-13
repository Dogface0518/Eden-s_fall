//----- EB.JAVA -----
  
package com.mycompany.game;
 
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
        int energyCost = 2;
        if (energy >= energyCost){
            useEnergy(energyCost);
            System.out.println("Eb uses Drop Kick, doing 9 damage!");
            target.takeDamage(dropkick);
        } else {
            System.out.println("Not enough energy to use this.");
        }
    }
    public void evade(){
        skill.evade(this, evadeFlag, 2);
    }
}