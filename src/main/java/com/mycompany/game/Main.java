//---MAIN.JAVA---
 
package com.mycompany.game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String player1Name = "Player 1";
        String player2Name = "Player 2";

        // character choosing
        CharacterStats player1 = chooseCharacter(scanner, player1Name);
        CharacterStats player2 = chooseCharacter(scanner, player2Name);
        boolean player1Turn = true;

        System.out.println();
        System.out.println("-O==========================================O-");
        System.out.println("     PVP Turn-Based with Touch Move Rule.\n   -Once you press enter you can't go back-");
        System.out.println("-O==========================================O-");

        // GAME LOOP
        while (player1.getHP() > 0 && player2.getHP() > 0) {
            if (player1Turn) {
                System.out.println("\n" + player1Name + "'s turn");
                playerTurn(scanner, player1, player2, player1Name);
            } else {
                System.out.println("\n" + player2Name + "'s turn");
                playerTurn(scanner, player2, player1, player2Name);
            }

            // status display
            System.out.println("\n-----Status-----");
            System.out.println(player1Name + " - HP: " + player1.getHP() + ", Energy: " + player1.getEnergy());
            System.out.println(player2Name + " - HP: " + player2.getHP() + ", Energy: " + player2.getEnergy());
            System.out.println("----------------");

            // switch turns
            player1Turn = !player1Turn;
        }

        // determine winner
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
                System.out.println("A WOMAN! She came to wash dishes and Kick ass. And she's all out of dishes.");// OP
                return new eb(25, 6, 7, 11);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("2")) {
                System.out.println("-----Lore bits-----");
                System.out.println("MAN. Born from the discarded flesh of a newly birthed world.");// Ilisdi nig tarong oy.
                return new adan(35, 6, 5, 8);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("3")) {
                System.out.println("-----Lore bits-----");
                System.out.println("666, A fallen celestial, A deciever, A misguided son");// Chuya, unsay lore ani
                return new tanas(35, 6, 5, 9);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("4")) {
                System.out.println("-----Lore bits-----");
                System.out.println("An Observer, It works in mysterious ways, Its motives unknown.");// Kinsa ni? Holy Spirit?
                return new guide(25, 6, 4, 7);//HP, ENERGY, BASIC ATTACK, SKILL
            } else if (choice.equals("5")) {
                System.out.println("-----Lore bits-----");
                System.out.println("Your head starts aching as visions and knowledge incomprohensible floods into your mind. \"%%$ #@$!@ ^@$^#$ !$$@@ !$@!) ");//
                return new apple(30, 6, 2, 10);//HP, ENERGY, BASIC ATTACK, SKILL
            }
        }
    }

    public static void playerTurn(Scanner scanner, CharacterStats player, CharacterStats opponent, String playerName) {
        System.out.println(playerName + ", choose an action:");
        if (player instanceof eb) {
            System.out.println("1. Low Kick  - (1) energy");
            System.out.println("2. Drop Kick - (3) energy");
            System.out.println("3. Evade     - (2) energy");
            System.out.println("4. Skip      - (+1) energy");
        } else if (player instanceof adan) {
            System.out.println("1. Punch Combo - (1) energy");
            System.out.println("2. Tackle      - (3) energy");
            System.out.println("3. Defend      - (2) energy");
            System.out.println("4. Skip        - (+1) energy");
        } else if (player instanceof tanas) {
            System.out.println("1. Whip        - (1) energy");
            System.out.println("2. Bite        - (3) energy");
            System.out.println("3. Evade       - (2) energy");
            System.out.println("4. Skip        - (+1) energy");
        }  else if (player instanceof guide) {
            System.out.println("1. Bless       - (1) energy");
            System.out.println("2. Smite       - (3) energy");
            System.out.println("3. Defend      - (2) energy");
            System.out.println("4. Skip        - (+1) energy");
        }  else if (player instanceof apple) {
            System.out.println("1. Knowledge     - (1) energy");// TEMP, change to player friendly skill
            System.out.println("2. Hollow Purple - (3) energy");// TEMP, change to player friendly skill
            System.out.println("3. Defend        - (2) energy");// TEMP, change to player friendly skill
            System.out.println("4. Skip          - (+1) energy");
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
                    ebPlayer.useEnergy(-1);
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
                    adanPlayer.useEnergy(-1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        } else if (player instanceof tanas tanasPlayer) {
            switch (action) {
                case "1" -> tanasPlayer.lowkick(opponent);// temp change to whip
                case "2" -> tanasPlayer.dropkick(opponent);// temp change to bite
                case "3" -> tanasPlayer.evade();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    tanasPlayer.useEnergy(-1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        } else if (player instanceof guide guidePlayer) {
            switch (action) {
                case "1" -> guidePlayer.punch(opponent);// 
                case "2" -> guidePlayer.tackle(opponent);// 
                case "3" -> guidePlayer.defend();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    guidePlayer.useEnergy(-1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        } else if (player instanceof apple applePlayer) {
            switch (action) {
                case "1" -> applePlayer.lowkick(opponent);// 
                case "2" -> applePlayer.dropkick(opponent);// 
                case "3" -> applePlayer.evade();
                case "4" -> {
                    System.out.println(playerName + " chooses to skip the turn and gains 1 energy.");
                    applePlayer.useEnergy(-1);
                }
                default -> System.out.println("Invalid action. Turn skipped.");
            }
            System.out.println("-O-------------O-");
        }
    }
}