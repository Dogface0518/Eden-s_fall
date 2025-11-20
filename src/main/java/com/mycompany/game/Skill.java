//----SKILL.JAVA----
package com.mycompany.game;

public class Skill {
    static void evade(CharacterStats user, int energyCost){
        if (user.getEnergy() >= energyCost) {
            user.useEnergy(energyCost);
            user.setEvadeFlag(true);
            System.out.println(getCharacterName(user) + " is prepared and ready to dodge any attacks!");
        } else {
            System.out.println("Not enough energy to use Evade. Turn Skipped.");
        } 
    }
    static void defend(CharacterStats user, int energyCost){
        if (user.getEnergy() >= energyCost) {
            user.useEnergy(energyCost);
            user.setDefendFlag(true);
            System.out.println(getCharacterName(user) + " is bracing for any attacks, reducing the damage taken!");
        } else {
            System.out.println("Not enough energy to Defend. Turn Skipped.");
        }
    }
    private static String getCharacterName(CharacterStats user){// Kailangan para ang ngan sa character ni use sa skill mogawas.
        return user.getClass().getSimpleName();
    }
}