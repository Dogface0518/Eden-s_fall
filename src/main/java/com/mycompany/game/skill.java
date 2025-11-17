//----SKILL.JAVA----
package com.mycompany.game;

public class skill {
    public static void evade(CharacterStats user, boolean[] evadeFlag, int energyCost){
        if (user.getEnergy() >= energyCost) {
            user.useEnergy(energyCost);
            evadeFlag[0] = true;
            System.out.println(getCharacterName(user) + " is prepared and ready to dodge any attacks!");
        } else {
            System.out.println("Not enough energy to use Evade. Turn Skipped.");
        } 
    }
    public static void defend(CharacterStats user, boolean[] defendFlag, int energyCost){
        if (user.getEnergy() >= energyCost) {
            user.useEnergy(energyCost);
            defendFlag[0] = true;
            System.out.println(getCharacterName(user) + " is bracing for any attacks, reducing the damage taken!");
        } else {
            System.out.println("Not enough energy to Defend. Turn Skipped.");
        }
    }
    private static String getCharacterName(CharacterStats user){// Kailangan para ang ngan sa character ni use sa skill mogawas.
        return user.getClass().getSimpleName();
    }
}