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
            System.out.println("2. Adan - Iron Resolve");
            String choice = scanner.nextLine();
            System.out.println();
            if (choice.equals("1")) {
                return new eb(25, 6, 7, 11);
            } else if (choice.equals("2")) {
                return new adan(35, 6, 5, 8);
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
        }
    }
}