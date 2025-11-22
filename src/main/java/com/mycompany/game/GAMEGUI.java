package com.mycompany.game;

import com.mycompany.game.Characters.Guide;
import com.mycompany.game.Characters.Eb;
import com.mycompany.game.Characters.Apple;
import com.mycompany.game.Characters.Tanas;
import com.mycompany.game.Characters.Adan;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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

        JLabel title = new JLabel("Eden's Fall", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        JLabel clickText = new JLabel("Click anywhere to start", SwingConstants.CENTER);
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
        getContentPane().removeAll();

        JPanel storyPanel = new JPanel(new BorderLayout());
        storyPanel.setBackground(Color.BLACK);

        String storyText =
                "In a reimagined Eden, everything is peaceful and harmonious, but that balance is about to be shattered.\n\n" +
                        "The Garden is not just a paradise – it is a place of ancient power, with forces beyond mortal comprehension keeping it in balance.\n\n" +
                        "The conflict arises when the Garden itself begins to deteriorate, corrupted by forces both external and internal.\n\n" +
                        "The players are drawn into this cosmic struggle, each representing a different facet of Eden’s downfall or possible redemption.\n\n" +
                        "The fighting isn't just physical – it's a battle for the very soul of Eden.\n\n" +
                        "What does the Garden need: redemption, destruction, or a new beginning?";

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.WHITE);
        textPane.setFont(new Font("Serif", Font.PLAIN, 22));
        textPane.setMargin(new Insets(40, 60, 40, 60));

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        textPane.setText(storyText);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.BLACK);

        storyPanel.add(scrollPane, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Next");
        nextBtn.setBackground(Color.DARK_GRAY);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);
        nextBtn.setFont(new Font("Serif", Font.BOLD, 16));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(nextBtn);

        nextBtn.addActionListener(e -> showPlayerSelection(1));

        storyPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(storyPanel);
        revalidate();
        repaint();
    }

    // ===================== CHARACTER SELECTION =====================
    private void showPlayerSelection(int playerNumber) {
        getContentPane().removeAll();

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
            case "Eb" -> "Eb felt the shift like an insult hurled at her...";
            case "Adan" -> "Adan awoke from the pulse with a hollow ache...";
            case "Tanas" -> "Far from the others, Tanas watched the Garden’s collapse...";
            case "Guide" -> "Guide alone understood the scale of the disaster...";
            case "Apple" -> "Deep in the heart of Eden, sealed away under roots...";
            default -> "";
        };
    }

    // ===================== CREATE CHARACTERS =====================
    private CharacterStats createCharacter(String name) {
        return switch (name) {
            case "Eb" -> new Eb(60, 4);
            case "Adan" -> new Adan(80, 4);
            case "Tanas" -> new Tanas(66, 4);
            case "Guide" -> new Guide(60, 4);
            case "Apple" -> new Apple(50, 4);
            default -> new Eb(60, 4);
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

        log("\n" + current.getName() + "'s turn.");

        String[] actions = getActions(current);

        for (int i = 0; i < actions.length; i++) {
            int idx = i;
            JButton btn = new JButton(actions[i]);
            btn.setBackground(Color.DARK_GRAY);
            btn.setForeground(Color.WHITE);

            btn.addActionListener(e -> {
                String actionLog = performAction(current, enemy, idx);
                log(actionLog);
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

    // ===================== ACTION LOGIC =====================
    private String[] getActions(CharacterStats c) {
        return switch (c.getClass().getSimpleName()) {
            case "Adan" -> new String[]{"Punch", "Tackle", "Defend", "Skip"};
            case "Apple" -> new String[]{"Bump", "Squash", "Evade", "Skip"};
            case "Eb" -> new String[]{"Lowkick", "Dropkick", "Evade", "Skip"};
            case "Guide" -> new String[]{"Bless", "Smite", "Defend", "Skip"};
            case "Tanas" -> new String[]{"Whip", "Bite", "Evade", "Skip"};
            default -> new String[]{"Attack1", "Attack2", "Skill", "Skip"};
        };
    }

    private String performAction(CharacterStats p, CharacterStats enemy, int action) {
    String logMsg = "";

    switch (p.getClass().getSimpleName()) {

        case "Adan" -> {
            Adan a = (Adan) p;
            if (action == 0) logMsg = a.punch(enemy);
            else if (action == 1) logMsg = a.tackle(enemy);
            else if (action == 2) logMsg = Skill.defend(a, 2, a.getName());
            else { 
                a.gainEnergy(1); 
                logMsg = a.getName() + " skipped turn (+1 Energy)";
            }
        }

        case "Apple" -> {
            Apple a = (Apple) p;
            if (action == 0) logMsg = a.bump(enemy);
            else if (action == 1) logMsg = a.squash(enemy);
            else if (action == 2) logMsg = Skill.evade(a, 2, a.getName());
            else { 
                a.gainEnergy(1); 
                logMsg = a.getName() + " skipped turn (+1 Energy)";
            }
        }

        case "Eb" -> {
            Eb a = (Eb) p;
            if (action == 0) logMsg = a.lowkick(enemy);
            else if (action == 1) logMsg = a.dropkick(enemy);
            else if (action == 2) logMsg = Skill.evade(a, 2, a.getName());
            else { 
                a.gainEnergy(1); 
                logMsg = a.getName() + " skipped turn (+1 Energy)";
            }
        }

        case "Guide" -> {
            Guide a = (Guide) p;
            if (action == 0) logMsg = a.bless(enemy);
            else if (action == 1) logMsg = a.smite(enemy);
            else if (action == 2) logMsg = Skill.defend(a, 1, a.getName());
            else { 
                a.gainEnergy(1); 
                logMsg = a.getName() + " skipped turn (+1 Energy)";
            }
        }

        case "Tanas" -> {
            Tanas a = (Tanas) p;
            if (action == 0) logMsg = a.whip(enemy);
            else if (action == 1) logMsg = a.bite(enemy);
            else if (action == 2) logMsg = Skill.evade(a, 1, a.getName());
            else { 
                a.gainEnergy(1); 
                logMsg = a.getName() + " skipped turn (+1 Energy)";
            }
        }
    }

    return logMsg;
}



    // ===================== STATUS & END CHECK =====================
    private void updateStatusLabels() {
        if (player1 == null || player2 == null) return;
        player1Status.setText(player1.getName() + " - HP: " + player1.getHP() + ", Energy: " + player1.getEnergy());
        player2Status.setText(player2.getName() + " - HP: " + player2.getHP() + ", Energy: " + player2.getEnergy());
    }

    private boolean checkEnd() {
        if (player1.getHP() <= 0 && player2.getHP() <= 0) {
            JOptionPane.showMessageDialog(this, "It's a Tie!");
            System.exit(0);
            return true;
        }
        if (player1.getHP() <= 0) {
            JOptionPane.showMessageDialog(this, player2.getName() + " Wins!");
            System.exit(0);
            return true;
        }
        if (player2.getHP() <= 0) {
            JOptionPane.showMessageDialog(this, player1.getName() + " Wins!");
            System.exit(0);
            return true;
        }
        return false;
    }

    private void log(String msg) {
        gameLog.append(msg + "\n");
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GAMEGUI().setVisible(true));
    }
}
