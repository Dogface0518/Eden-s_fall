package com.mycompany.game;

import javax.swing.*;
import java.awt.*;

public class GAMEGUI extends JFrame {

    private CharacterStats player1, player2;
    private boolean player1Turn = true;

    private JLabel player1Status, player2Status;
    private JTextArea gameLog;
    private JPanel actionPanel;

    public GAMEGUI() {
        setTitle("Eden's Fall");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        player1Status = new JLabel();
        player2Status = new JLabel();
        player1Status.setForeground(Color.WHITE);
        player2Status.setForeground(Color.WHITE);

        gameLog = new JTextArea();
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
        gameLog.setWrapStyleWord(true);
        gameLog.setBackground(Color.BLACK);
        gameLog.setForeground(Color.WHITE);

        actionPanel = new JPanel();
        actionPanel.setBackground(Color.BLACK);

        showTitleScreen();
    }

    // ===================== TITLE SCREEN =====================
    private void showTitleScreen() {
        JButton titleButton = new JButton();
        titleButton.setBackground(Color.BLACK);
        titleButton.setBorderPainted(false);
        titleButton.setFocusPainted(false);
        titleButton.setLayout(new GridBagLayout());

        JPanel inner = new JPanel();
        inner.setBackground(Color.BLACK);
        inner.setLayout(new GridLayout(2, 1, 0, 20));

        JLabel title = new JLabel("Eden's Fall");
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        JLabel clickText = new JLabel("Click anywhere to start");
        clickText.setFont(new Font("Serif", Font.PLAIN, 20));
        clickText.setForeground(Color.GRAY);

        inner.add(title);
        inner.add(clickText);
        titleButton.add(inner);

        setContentPane(titleButton);
        revalidate();
        repaint();

        titleButton.addActionListener(e -> showStoryScreen());
    }

    // ===================== STORY SCREEN =====================
    private void showStoryScreen() {
        JButton storyButton = new JButton();
        storyButton.setBackground(Color.BLACK);
        storyButton.setBorderPainted(false);
        storyButton.setFocusPainted(false);
        storyButton.setLayout(new GridBagLayout());

        JPanel inner = new JPanel();
        inner.setBackground(Color.BLACK);
        inner.setLayout(new GridLayout(0, 1, 0, 20));

        String[] storyLines = {
            "In a reimagined Eden, everything is peaceful and harmonious, but that balance is about to be shattered.",
            "The Garden is not just a paradise - it is a place of ancient power, with forces beyond mortal comprehension keeping it in balance.",
            "The conflict arises when the Garden itself begins to deteriorate, corrupted by forces both external and internal.",
            "The players are drawn into this cosmic struggle, with each character representing different facets of this world's downfall or potential redemption.",
            "The fighting isn't just a physical battle - it's a fight for the very soul of the Garden, each character believing they have the right to shape its future in their image.",
            "What does Eden need: redemption, destruction, or a new beginning?"
        };

        for (String line : storyLines) {
            JLabel lbl = new JLabel(line);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Serif", Font.PLAIN, 18));
            inner.add(lbl);
        }

        storyButton.add(inner);
        setContentPane(storyButton);
        revalidate();
        repaint();

        storyButton.addActionListener(e -> showPlayerSelection(1));
    }
    // ===================== CHARACTER SELECTION =====================
    private void showPlayerSelection(int playerNumber) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel selectPanel = new JPanel(new BorderLayout());
        selectPanel.setBackground(Color.BLACK);
        selectPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel prompt = new JLabel("Player " + playerNumber + ": Choose your character");
        prompt.setForeground(Color.WHITE);
        prompt.setFont(new Font("Serif", Font.BOLD, 18));
        selectPanel.add(prompt, BorderLayout.NORTH);

        JPanel list = new JPanel();
        list.setBackground(Color.BLACK);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        String[] chars = {"Eb", "Adan", "Tanas", "Guide", "Apple"};
        for (String c : chars) {
            JButton charBtn = new JButton(c);
            charBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            charBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            charBtn.setBackground(Color.DARK_GRAY);
            charBtn.setForeground(Color.WHITE);
            charBtn.setFont(new Font("Serif", Font.PLAIN, 16));
            charBtn.setFocusPainted(false);

            charBtn.addActionListener(ev -> showLorePanel(c, playerNumber));

            JPanel wrap = new JPanel(new BorderLayout());
            wrap.setBackground(Color.BLACK);
            wrap.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
            wrap.add(charBtn, BorderLayout.CENTER);
            list.add(wrap);
        }

        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setBorder(null);
        selectPanel.add(listScroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setBackground(Color.BLACK);
        JButton backBtn = new JButton("Back to Story");
        backBtn.setBackground(Color.DARK_GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> showStoryScreen());
        bottom.add(backBtn);
        selectPanel.add(bottom, BorderLayout.SOUTH);

        setContentPane(selectPanel);
        revalidate();
        repaint();
    }

    // ===================== FULLSCREEN LORE PANEL =====================
    private void showLorePanel(String character, int playerNumber) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel lorePanel = new JPanel(new BorderLayout());
        lorePanel.setBackground(Color.BLACK);
        lorePanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel(character);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        lorePanel.add(title, BorderLayout.NORTH);

        JTextArea loreArea = new JTextArea(getCharacterLore(character));
        loreArea.setEditable(false);
        loreArea.setLineWrap(true);
        loreArea.setWrapStyleWord(true);
        loreArea.setFont(new Font("Serif", Font.PLAIN, 20));
        loreArea.setForeground(Color.WHITE);
        loreArea.setBackground(Color.BLACK);
        loreArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JScrollPane loreScroll = new JScrollPane(loreArea);
        loreScroll.setBorder(null);
        lorePanel.add(loreScroll, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        buttons.setBackground(Color.BLACK);

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.DARK_GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> showPlayerSelection(playerNumber));

        JButton confirmBtn = new JButton("Choose Character");
        confirmBtn.setBackground(new Color(30, 130, 76));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.addActionListener(e -> {
            if (playerNumber == 1) {
                player1 = createCharacter(character);
                showPlayerSelection(2);
            } else {
                player2 = createCharacter(character);
                setupGameUI();
                log("Game Started!");
                updateStatusLabels();
                nextTurn();
            }
        });

        buttons.add(backBtn);
        buttons.add(confirmBtn);
        lorePanel.add(buttons, BorderLayout.SOUTH);

        setContentPane(lorePanel);
        revalidate();
        repaint();
    }

    private String getCharacterLore(String name) {
        return switch (name) {
            case "Eb" ->
                "Eb felt the shift like an insult hurled at her from the heavens.\n" +
"For ages, she had lived with a story forced upon her — the temptress, the wrongdoer, the reason paradise had once fallen. Those whispers had clung to her like chains. But when the Mistake Pulse echoed through her bones, something changed.\n" +
"\n" +
"With the dishes washed and the roles she never chose finally cracking apart, she saw Eden falling and thought:\n" +
"Not again. Not without my say this time.\n" +
"\n" +
"She stepped forward not to be forgiven, but to be feared.\n" +
"If Eden was collapsing, she would rebuild it with her own hands — a Garden where no one could twist her story ever again.";
            case "Adan" ->
                "Adan awoke from the pulse with a hollow ache in his chest.\n" +
"He was made to be a companion, yes — but also something else. Something unfinished. Born from the discarded flesh of creation, he’d always felt like a leftover shaped into a man.\n" +
"\n" +
"As Eden decayed, Adan feared he would decay with it.\n" +
"If the Garden died, what was he?\n" +
"A mistake’s mistake?\n" +
"\n" +
"No.\n" +
"He refused to be an afterthought.\n" +
"With Eden unraveling, Adan found a new purpose: to reshape the world and prove to creation itself that he deserved to stand on his own. Even if he had to fight everyone — Eb included — to claim that destiny.";
            case "Tanas" ->
                "Far from the others, Tanas watched the Garden’s collapse with something close to delight.\n" +
"This was the moment he had waited for.\n" +
"\n" +
"Once a celestial servant, he had been cast out not for hatred, but for curiosity. He never wished for destruction for its own sake — only for change. For the freedom to choose. For the right to question a perfect world that smothered its inhabitants with its own flawlessness.\n" +
"\n" +
"Now Eden trembled, and Tanas saw an opportunity glowing at its core.\n" +
"The Fall, he believed, was not a tragedy at all — but a necessary evolution. If it took chaos to free the Garden from stagnation, he would gladly pull every root from the soil.\n" +
"\n" +
"But even he feared a truth he’d never admit:\n" +
"What if his freedom meant annihilation for everyone else?";
            case "Guide" ->
                "Guide alone understood the scale of the disaster.\n" +
"A silent watcher woven from divine breath, it drifted above the fraying world, its form flickering with indecision. Eden had been its charge — not to shape, not to rule, but to observe. Always the observer. Never the actor.\n" +
"\n" +
"But now the balance that Guide was meant to preserve was collapsing.\n" +
"Voices from above offered no answers.\n" +
"Every outcome led to ruin or rebirth, and every choice demanded intervention.\n" +
"\n" +
"For the first time, the observer wondered:\n" +
"Is neutrality just another form of abandonment?\n" +
"\n" +
"If Guide stepped into the conflict, it could save Eden… or destroy the very balance it was meant to protect.";
            case "Apple" ->
                "Deep in the heart of Eden, sealed away under roots older than the stars, something awakened.\n" +
"The Apple — Temptation incarnate — blinked open its formless eyes, knowledge dripping off it like nectar.\n" +
"\n" +
"It remembered everything: the first bite, the first doubt, the first spark of curiosity that split innocence in two. Knowledge had always been a blessing and a curse, but most of all, it had been irresistible.\n" +
"\n" +
"As Eden rotted, Apple felt the seal around its mind break.\n" +
"It rose into the world, flooding all who touched it with visions and truths no living being was meant to carry.\n" +
"To Apple, ignorance was the true prison.\n" +
"If Eden was dying, then it would drag every secret into the light before the Garden fell into darkness.";
            default -> "";
        };
    }

    private CharacterStats createCharacter(String name) {
        return switch (name) {
            case "Eb" -> new Eb(60, 4, 7, 15);
            case "Adan" -> new Adan(80, 4, 5, 8);
            case "Tanas" -> new Tanas(66, 4, 6, 14);
            case "Guide" -> new Guide(60, 4, 9, 5);
            case "Apple" -> new Apple(50, 4, 7, 11);
            default -> new Eb(60, 4, 7, 15);
        };
    }

    // ===================== GAME UI =====================
    private void setupGameUI() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        statusPanel.setBackground(Color.BLACK);
        statusPanel.add(player1Status);
        statusPanel.add(player2Status);

        JScrollPane scrollPane = new JScrollPane(gameLog);
        scrollPane.setBorder(null);

        add(statusPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    // ===================== TURN SYSTEM =====================
    private void nextTurn() {
        actionPanel.removeAll();

        CharacterStats current = player1Turn ? player1 : player2;
        CharacterStats enemy = player1Turn ? player2 : player1;
        String currentName = player1Turn ? "Player 1" : "Player 2";

        log("\n" + currentName + "'s turn.");

        String[] actions = {"Attack 1", "Attack 2", "Defend", "Skip"};
        for (int i = 0; i < actions.length; i++) {
            int idx = i;
            JButton btn = new JButton(actions[i]);
            btn.setBackground(Color.DARK_GRAY);
            btn.setForeground(Color.WHITE);

            btn.addActionListener(e -> {
                performAction(current, enemy, idx, currentName);
                updateStatusLabels();
                if (!checkEnd()) {
                    player1Turn = !player1Turn;
                    nextTurn();
                }
            });

            actionPanel.add(btn);
        }

        actionPanel.revalidate();
        actionPanel.repaint();
    }

    private void log(String text) {
        System.out.println(text);
        gameLog.append(text + "\n");
    }

    private void performAction(CharacterStats p, CharacterStats enemy, int action, String name) {
        int attackCost = 1;

        switch (p.getClass().getSimpleName()) {
            case "Adan" -> {
                Adan ad = (Adan) p;
                if (action == 0) { if (p.getEnergy() >= attackCost) { ad.punch(enemy); p.useEnergy(attackCost); log(name + " used Punch!"); } else log(name + " tried Punch but not enough energy!"); }
                else if (action == 1) { if (p.getEnergy() >= attackCost) { ad.tackle(enemy); p.useEnergy(attackCost); log(name + " used Tackle!"); } else log(name + " tried Tackle but not enough energy!"); }
                else if (action == 2) { ad.defend(); log(name + " is Defending!"); }
                else { p.gainEnergy(1); log(name + " skipped the turn and gained 1 Energy!"); }
            }
            case "Apple" -> {
                Apple ap = (Apple) p;
                if (action == 0) { if (p.getEnergy() >= attackCost) { ap.bump(enemy); p.useEnergy(attackCost); log(name + " used Bump!"); } else log(name + " tried Bump but not enough energy!"); }
                else if (action == 1) { if (p.getEnergy() >= attackCost) { ap.squash(enemy); p.useEnergy(attackCost); log(name + " used Squash!"); } else log(name + " tried Squash but not enough energy!"); }
                else if (action == 2) { ap.evade(); log(name + " is Evading!"); }
                else { p.gainEnergy(1); log(name + " skipped the turn and gained 1 Energy!"); }
            }
            case "Eb" -> {
                Eb eb = (Eb) p;
                if (action == 0) { if (p.getEnergy() >= attackCost) { eb.lowkick(enemy); p.useEnergy(attackCost); log(name + " used Lowkick!"); } else log(name + " tried Lowkick but not enough energy!"); }
                else if (action == 1) { if (p.getEnergy() >= attackCost) { eb.dropkick(enemy); p.useEnergy(attackCost); log(name + " used Dropkick!"); } else log(name + " tried Dropkick but not enough energy!"); }
                else if (action == 2) { eb.evade(); log(name + " is Evading!"); }
                else { p.gainEnergy(1); log(name + " skipped the turn and gained 1 Energy!"); }
            }
            case "Guide" -> {
                Guide g = (Guide) p;
                if (action == 0) { if (p.getEnergy() >= attackCost) { g.bless(enemy); p.useEnergy(attackCost); log(name + " used Bless!"); } else log(name + " tried Bless but not enough energy!"); }
                else if (action == 1) { if (p.getEnergy() >= attackCost) { g.smite(enemy); p.useEnergy(attackCost); log(name + " used Smite!"); } else log(name + " tried Smite but not enough energy!"); }
                else if (action == 2) { g.defend(); log(name + " is Defending!"); }
                else { p.gainEnergy(1); log(name + " skipped the turn and gained 1 Energy!"); }
            }
            case "Tanas" -> {
                Tanas t = (Tanas) p;
                if (action == 0) { if (p.getEnergy() >= attackCost) { t.whip(enemy); p.useEnergy(attackCost); log(name + " used Whip!"); } else log(name + " tried Whip but not enough energy!"); }
                else if (action == 1) { if (p.getEnergy() >= attackCost) { t.bite(enemy); p.useEnergy(attackCost); log(name + " used Bite!"); } else log(name + " tried Bite but not enough energy!"); }
                else if (action == 2) { t.evade(); log(name + " is Evading!"); }
                else { p.gainEnergy(1); log(name + " skipped the turn and gained 1 Energy!"); }
            }
        }
    }

    private void updateStatusLabels() {
        if (player1 == null || player2 == null) return;
        player1Status.setText("Player 1 - HP: " + player1.getHP() + ", Energy: " + player1.getEnergy());
        player2Status.setText("Player 2 - HP: " + player2.getHP() + ", Energy: " + player2.getEnergy());
    }

    private boolean checkEnd() {
        if (player1.getHP() <= 0 && player2.getHP() <= 0) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            System.exit(0);
            return true;
        }
        if (player1.getHP() <= 0) {
            JOptionPane.showMessageDialog(this, "Player 2 wins!");
            System.exit(0);
            return true;
        }
        if (player2.getHP() <= 0) {
            JOptionPane.showMessageDialog(this, "Player 1 wins!");
            System.exit(0);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GAMEGUI().setVisible(true));
    }
}
