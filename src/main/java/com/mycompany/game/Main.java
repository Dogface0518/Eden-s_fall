//----MAIN.JAVA----
package com.mycompany.game;

import java.util.Scanner;
import java.util.Random;
 
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Set player 2 name: ");
        String player2Name = scanner.nextLine();
 
        // Character Selection
        CharacterStats player1 = chooseCharacter(scanner, player1Name);
        CharacterStats player2 = chooseCharacter(scanner, player2Name);
        boolean player1Turn = true;
 
        System.out.println();
        System.out.println("-O==========================================O-");
        System.out.println("     PVP Turn-Based with Touch Move Rule.\n   -Once you press enter you can't go back-");
        System.out.println("-O==========================================O-");
 
        // Game Loop
        while (player1.getHP() > 0 && player2.getHP() > 0) {
            if (player1Turn) {
                System.out.println("\n" + player1Name + "'s turn");
                playerTurn(scanner, player1, player2, player1Name);
            } else {
                System.out.println("\n" + player2Name + "'s turn");
                playerTurn(scanner, player2, player1, player2Name);
            }
 
            // Status Display
            System.out.println("\n-----Status-----");
            System.out.println(player1Name + " - HP: " + player1.getHP() + ", Energy: " + player1.getEnergy());
            System.out.println(player2Name + " - HP: " + player2.getHP() + ", Energy: " + player2.getEnergy());
            System.out.println("----------------");
 
            // Switch Turns
            player1Turn = !player1Turn;
        }
 
        // Determine Winner
        System.out.println("\n--- Game Over ---");
        if (player1.getHP() <= 0 && player2.getHP() <= 0) {
            System.out.println("It's a tie!");
        } else if (player1.getHP() <= 0) {
            System.out.println(player2Name + " wins!");
        } else {
            System.out.println(player1Name + " wins!");
        }
 
        scanner.close();
    }
 
    public static CharacterStats chooseCharacter(Scanner scanner, String playerName) {
        while (true) {
            System.out.println();
            System.out.println(playerName + ", choose your character:");
            System.out.println("1. Eb   - The Reckless Striker");
            System.out.println("2. Adan - The Iron Resolve");
            System.out.println("3. Tanas - The Devil");
            System.out.println("4. Guide - The Guardian");
            System.out.println("5. Apple - The Temptor");
            String choice = scanner.nextLine();
            System.out.println();
            if (choice.equals("1")) {
                System.out.println("-----Lore bits-----");
                System.out.println("Woman, She came to wash dishes and Kick ass. And she's all out of dishes.");// OP
                return new eb(60, 4, 7, 15);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("2")) {
                System.out.println("-----Lore bits-----");
                System.out.println("Man. Born from the discarded flesh of a newly birthed world.");// 
                return new adan(80, 4, 5, 8);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("3")) {
                System.out.println("-----Lore bits-----");
                System.out.println("Devil ,666, A fallen celestial, A deciever, A misguided son");// 
                return new tanas(66, 4, 6, 14);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("4")) {
                System.out.println("-----Lore bits-----");
                System.out.println("Angel, An Observer, It works in mysterious ways, Its motives unknown.");//
                return new guide(60, 4, 9, 5);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("5")) {
                System.out.println("-----Lore bits-----");
                System.out.println("Temptation, Your head starts aching as visions and knowledge incomprohensible floods into your mind. \"%%$ #@$!@ ^@$^#$ !$$@@ !$@!) ");//
                return new apple(50, 4, 7, 11);//HP, ENERGY, BASIC ATTACK, SKILL
            }
        }
    }
    public static void playerTurn(Scanner scanner, CharacterStats player, CharacterStats opponent, String playerName) {
        Random random = new Random(); //for dice roll||damage range
        System.out.println(playerName + ", choose an action:");
        if (player instanceof eb) {
            int LKickDMG = random.nextInt(3) + 7;  System.out.println("1. Low Kick  - (1)  energy | (7) damage");
            int dropKDMG = random.nextInt(5) + 15;  System.out.println("2. Drop Kick - (3)  energy | (15) damage");
            System.out.println("3. Evade     - (2)  energy");
            System.out.println("4. Skip      - (+1) energy ");
        } else if (player instanceof adan) {
            int punchDMG  = random.nextInt(3) + 5;  System.out.println("1. Punch Combo - (1)  energy | (5) damage");
            int tackleDMG = random.nextInt(4) + 8;  System.out.println("2. Tackle      - (3)  energy | (8) damage");
            System.out.println("3. Defend      - (2)  energy");
            System.out.println("4. Skip        - (+1) energy");
        } else if (player instanceof tanas) {
            int whipeDMG   = random.nextInt(3) + 6;  System.out.println("1. Whip        - (1)  energy | (6) damage");
            int biteDamage = random.nextInt(5) + 14;  System.out.println("2. Bite        - (3)  energy | (14) damage");
            System.out.println("3. Evade       - (1)  energy");
            System.out.println("4. Skip        - (+1) energy");
        }  else if (player instanceof guide) {
            int blessDMG = random.nextInt(4) + 9;  System.out.println("1. Bless       - (1)  energy | (9) damage");
            int smiteDMG = random.nextInt(3) + 5;  System.out.println("2. Smite       - (3)  energy | (5) damage");
            System.out.println("3. Defend      - (1)  energy");
            System.out.println("4. Skip        - (+1) energy");
        }  else if (player instanceof apple) {
            int bumpDMG   = random.nextInt(3) + 7;  System.out.println("1. Bump         - (1)  energy | (7) damage");//
            int squashDMG = random.nextInt(4) + 11;  System.out.println("2. Squash       - (3)  energy | (11) damage");//
            System.out.println("3. Defend       - (2)  energy");//
            System.out.println("4. Skip         - (+1) energy");
        }
 
        String action = scanner.nextLine();
        System.out.println();
        System.out.println("-O-------------O-");
 
        if (player instanceof eb ebPlayer) {
            switch (action) {
                case "1" -> ebPlayer.lowkick(opponent);
                case "2" -> ebPlayer.dropkick(opponent);
                case "3" -> ebPlayer.evade();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    ebPlayer.gainEnergy(1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        } else if (player instanceof adan adanPlayer) {
            switch (action) {
                case "1" -> adanPlayer.punch(opponent);
                case "2" -> adanPlayer.tackle(opponent);
                case "3" -> adanPlayer.defend();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    adanPlayer.gainEnergy(1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        } else if (player instanceof tanas tanasPlayer) {
            switch (action) {
                case "1" -> tanasPlayer.whip(opponent);//
                case "2" -> tanasPlayer.bite(opponent);//
                case "3" -> tanasPlayer.evade();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    tanasPlayer.gainEnergy(1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        } else if (player instanceof guide guidePlayer) {
            switch (action) {
                case "1" -> guidePlayer.bless(opponent);// 
                case "2" -> guidePlayer.smite(opponent);// 
                case "3" -> guidePlayer.defend();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    guidePlayer.gainEnergy(1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        } else if (player instanceof apple applePlayer) {
            switch (action) {
                case "1" -> applePlayer.bump(opponent);// 
                case "2" -> applePlayer.squash(opponent);// 
                case "3" -> applePlayer.evade();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    applePlayer.gainEnergy(1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        }
    }
}