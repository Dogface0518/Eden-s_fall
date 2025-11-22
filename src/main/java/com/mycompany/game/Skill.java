//----SKILL.JAVA----
package com.mycompany.game;

public class Skill {

    // Defend: consumes energy, sets defend flag
    public static String defend(CharacterStats user, int cost, String userName) {
        if (user.getEnergy() >= cost) {
            user.useEnergy(cost);
            user.setDefendFlag(true);
            return userName + " is bracing for attacks! (Defend activated, energy -" + cost + ")";
        } else {
            return userName + " tried to Defend but lacked energy!";
        }
    }

    // Evade: consumes energy, sets evade flag
    public static String evade(CharacterStats user, int cost, String userName) {
        if (user.getEnergy() >= cost) {
            user.useEnergy(cost);
            user.setEvadeFlag(true);
            return userName + " is prepared to dodge! (Evade activated, energy -" + cost + ")";
        } else {
            return userName + " tried to Evade but lacked energy!";
        }
    }

    // Handle incoming damage: checks evade/defend flags on the target and either cancels/reduces damage or applies it.
    public static String handleIncomingDamage(CharacterStats target, int dmg, String attackerName) {
        String targetName = target.getClass().getSimpleName();

        if (target.getEvadeFlag()) {
            target.setEvadeFlag(false);
            return attackerName + " attacked " + targetName + " but it evaded! (0 dmg)";
        }

        if (target.getDefendFlag()) {
            target.setDefendFlag(false);
            // We'll make defend fully block the incoming attack (as per earlier convo). If you'd prefer half, change below:
            return attackerName + " attacked " + targetName + " but it blocked the attack! (0 dmg)";
        }

        // No evade/defend -> apply damage
        target.takeDamage(dmg);
        return attackerName + " dealt " + dmg + " damage to " + targetName + "!";
    }
}


/*package com.mycompany.game;

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
}*/