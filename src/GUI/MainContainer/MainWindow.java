package GUI.MainContainer;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Animals.Human;
import Organisms.Organism;
import World.World;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyEvent;
import java.awt.*;

public class MainWindow {
    private final JFrame mainFrame;
    private final World currWorld;
    private JButton[] boardButtons;
    private JLabel roundCounterLabel;
    private JTextArea comments;

    private final Commentator mainCommentator;
    private final JPanel top = new JPanel();
    private final JPanel side = new JPanel();
    private final KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    private final MyDispatcher arrowDispatcher = new MyDispatcher();
    private int newOrganismIndex = -1;
    JButton humanAdd, foxAdd, wolfAdd, antelopeAdd, sheepAdd, turtleAdd,
            belladonnaAdd, grassAdd, guaranaAdd, sosnowskyAdd, sowThistleAdd;
    private final JButton abilityBut = new JButton("Human Ability");
    private final JButton nextBut = new JButton("Next Turn");
    private final JButton saveBut = new JButton("Save");
    private final JButton loadBut = new JButton("Load");
    private final JButton exitBut = new JButton("Exit");

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP -> {
                    currWorld.getBoardBeginning()[currWorld.getHumanIndex()].setArrowPressed(KeyEvent.VK_UP);
                    nextBut.setEnabled(true);
                    mainFrame.setFocusable(true);
                    mainFrame.requestFocusInWindow();
                }
                case KeyEvent.VK_DOWN -> {
                    currWorld.getBoardBeginning()[currWorld.getHumanIndex()].setArrowPressed(KeyEvent.VK_DOWN);
                    nextBut.setEnabled(true);
                    mainFrame.setFocusable(true);
                    mainFrame.requestFocusInWindow();
                }
                case KeyEvent.VK_LEFT -> {
                    currWorld.getBoardBeginning()[currWorld.getHumanIndex()].setArrowPressed(KeyEvent.VK_LEFT);
                    nextBut.setEnabled(true);
                    mainFrame.setFocusable(true);
                    mainFrame.requestFocusInWindow();
                }
                case KeyEvent.VK_RIGHT -> {
                    currWorld.getBoardBeginning()[currWorld.getHumanIndex()].setArrowPressed(KeyEvent.VK_RIGHT);
                    nextBut.setEnabled(true);
                    mainFrame.setFocusable(true);
                    mainFrame.requestFocusInWindow();
                }
            }
            return false;
        }
    }

    private void switchAddingButtons(boolean newState) {
        if (!newState) {
            humanAdd.setEnabled(false);
        } else {
            humanAdd.setEnabled(!currWorld.isHumanAlive());
        }
        foxAdd.setEnabled(newState);
        wolfAdd.setEnabled(newState);
        antelopeAdd.setEnabled(newState);
        sheepAdd.setEnabled(newState);
        turtleAdd.setEnabled(newState);
        belladonnaAdd.setEnabled(newState);
        grassAdd.setEnabled(newState);
        guaranaAdd.setEnabled(newState);
        sosnowskyAdd.setEnabled(newState);
        sowThistleAdd.setEnabled(newState);
    }

    private void addGuiBoard(JPanel section) {
        JButton btn;
        section.removeAll();
        boardButtons = new JButton[currWorld.getN() * currWorld.getM()];
        for (int i = 0; i < currWorld.getN() * currWorld.getM(); i++) {
            if (currWorld.getBoardBeginning()[i] != null) {
                btn = new JButton(Character.toString(currWorld.getBoardBeginning()[i].getSpecies()));
            } else {
                btn = new JButton("");
            }
            boardButtons[i] = btn;
            btn.setName(Integer.toString(i));
            JButton finalBtn = btn;
            btn.addActionListener(e -> {
                int potentialNewIndex = Integer.parseInt(String.valueOf(finalBtn.getName()));
                if (currWorld.getBoardBeginning()[potentialNewIndex] == null) {
                    newOrganismIndex = potentialNewIndex;
                    switchAddingButtons(true);
                }
            });
            section.add(btn);
        }
    }

    private void updateBoardButtons() {
        Organism[] board = currWorld.getBoardBeginning();
        for (int i = 0; i < currWorld.getM() * currWorld.getN(); i++) {
            if (board[i] != null) {
                this.boardButtons[i].setText(Character.toString(board[i].getSpecies()));
            } else {
                this.boardButtons[i].setText("");
            }
        }
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();
    }

    private void fetchFileNameForOp(char flag) {
        JFrame fetchingWindow = new JFrame();
        fetchingWindow.setTitle("FileName input prompt. 193095");
        fetchingWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("Desired FileName: ");
        panel.add(label1);

        JTextField textField1 = new JTextField(20);
        panel.add(textField1);

        JButton button = new JButton("Submit");
        panel.add(button);

        JButton button1 = new JButton("Cancel");
        panel.add(button1);

        button.addActionListener(e -> {
            if (textField1.getText().length() <= 1) {
                JOptionPane.showMessageDialog(null, "Invalid file name",
                        "Error message", JOptionPane.ERROR_MESSAGE);
            } else {
                if (flag == 's') {
                    currWorld.saveGameState(textField1.getText());
                    fetchingWindow.dispose();
                } else if (flag == 'l') {
                    currWorld.loadGameState(textField1.getText());
                    mainFrame.repaint();
                    addGuiBoard(top);
                    addControlLabels(side);
                    updateBoardButtons();
                    manager.addKeyEventDispatcher(arrowDispatcher);
                    fetchingWindow.dispose();
                    top.setLayout(new GridLayout(currWorld.getM(), currWorld.getN()));
                    mainFrame.requestFocusInWindow();
                }
            }
        });

        button1.addActionListener(e -> fetchingWindow.dispose());

        panel.setSize(300, 400);
        fetchingWindow.getContentPane().add(panel);
        fetchingWindow.pack();
        fetchingWindow.setLocationRelativeTo(null);
        fetchingWindow.setVisible(true);
    }

    private void addControlButtons(JPanel section) {
        manager.addKeyEventDispatcher(arrowDispatcher);

        abilityBut.setName("abilityBut");
        abilityBut.addActionListener(e -> {
            int hInd = currWorld.getHumanIndex();
            currWorld.getBoardBeginning()[hInd].activateAbility();
            abilityBut.setEnabled(false);
            currWorld.getBoardBeginning()[currWorld.getHumanIndex()].setArrowPressed(0);
            currWorld.makeTurn();
            updateBoardButtons();
            switchAddingButtons(false);
            roundCounterLabel.setText("Round: " + currWorld.getRoundCounter());
            newOrganismIndex = -1;
            if (!currWorld.isHumanAlive()) {
                abilityBut.setEnabled(false);
                nextBut.setEnabled(true);
                manager.removeKeyEventDispatcher(arrowDispatcher);
            }
            abilityBut.setEnabled(false);
            nextBut.setEnabled(false);
        });

        abilityBut.setName("nextBut");
        nextBut.addActionListener(e -> {
            currWorld.makeTurn();
            updateBoardButtons();
            switchAddingButtons(false);
            roundCounterLabel.setText("Round: " + currWorld.getRoundCounter());
            newOrganismIndex = -1;
            if (!currWorld.isHumanAlive()) {
                abilityBut.setEnabled(false);
                nextBut.setEnabled(true);
                manager.removeKeyEventDispatcher(arrowDispatcher);
            } else if (!currWorld.getBoardBeginning()[currWorld.getHumanIndex()].abilityActive()
                    && currWorld.getBoardBeginning()[currWorld.getHumanIndex()].getCooldownReps() == 0
                    && currWorld.isHumanAlive()) {
                nextBut.setEnabled(false);
                abilityBut.setEnabled(true);
            } else {
                nextBut.setEnabled(false);
            }

        });

        abilityBut.setName("saveBut");
        saveBut.addActionListener(e -> fetchFileNameForOp('s'));

        abilityBut.setName("loadBut");
        loadBut.addActionListener(e -> fetchFileNameForOp('l'));

        abilityBut.setName("exitBut");
        exitBut.addActionListener(e -> {
            mainFrame.dispose();
            System.exit(0);
        });

        section.add(abilityBut);
        section.add(nextBut);
        section.add(saveBut);
        section.add(loadBut);
        section.add(exitBut);
        nextBut.setEnabled(false);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();
    }

    private void addControlLabels(JPanel section) {
        section.removeAll();
        roundCounterLabel = new JLabel("Round: " + currWorld.getRoundCounter());
        JLabel infoLabel = new JLabel("Adding Buttons: ");
        humanAdd = new JButton("Human");
        foxAdd = new JButton("Fox");
        wolfAdd = new JButton("Wolf");
        antelopeAdd = new JButton("Antelope");
        sheepAdd = new JButton("Sheep");
        turtleAdd = new JButton("Turtle");
        belladonnaAdd = new JButton("Belladonna");
        grassAdd = new JButton("Grass");
        guaranaAdd = new JButton("Guarana");
        sosnowskyAdd = new JButton("Sosnowsky");
        sowThistleAdd = new JButton("Sowthistle");
        switchAddingButtons(false);

        humanAdd.addActionListener(e -> {
            currWorld.getBoardBeginning()[newOrganismIndex]
                    = new Human(currWorld.getBoardBeginning(), currWorld.getN(), currWorld.getM(), mainCommentator);
            currWorld.getBoardBeginning()[newOrganismIndex].setPosition
                    ((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.setHumanX((newOrganismIndex % currWorld.getN()) + 1);
            currWorld.setHumanY((newOrganismIndex / currWorld.getN()) + 1);
            newOrganismIndex = -1;
            updateBoardButtons();
            manager.addKeyEventDispatcher(arrowDispatcher);
            switchAddingButtons(false);
            nextBut.setEnabled(false);
            abilityBut.setEnabled(true);
        });

        foxAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnAnimal(2);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        wolfAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnAnimal(0);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        antelopeAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnAnimal(4);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        sheepAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnAnimal(1);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        turtleAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnAnimal(3);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        belladonnaAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnPlant(8);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        grassAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnPlant(5);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        guaranaAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnPlant(7);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        sosnowskyAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnPlant(9);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });

        sowThistleAdd.addActionListener(e -> {
            Organism temp = currWorld.spawnPlant(6);
            temp.setPosition((newOrganismIndex % currWorld.getN()) + 1, (newOrganismIndex / currWorld.getN()) + 1);
            currWorld.getBoardBeginning()[newOrganismIndex] = temp;
            newOrganismIndex = -1;
            updateBoardButtons();
            switchAddingButtons(false);
        });
        section.add(roundCounterLabel);
        section.add(infoLabel);
        section.add(humanAdd);
        section.add(foxAdd);
        section.add(wolfAdd);
        section.add(antelopeAdd);
        section.add(sheepAdd);
        section.add(turtleAdd);
        section.add(belladonnaAdd);
        section.add(grassAdd);
        section.add(guaranaAdd);
        section.add(sosnowskyAdd);
        section.add(sowThistleAdd);
    }

    private void addComentator(JPanel section) {
        comments = new JTextArea();
        comments.setEditable(false);
        JScrollPane commentBox = new JScrollPane(comments);
        commentBox.setBorder(new TitledBorder(new EtchedBorder(), "Game Action: "));
        commentBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        comments.setLineWrap(true);

        section.add(commentBox);
    }

    public MainWindow(World mainWorld) {

        this.currWorld = mainWorld;
        mainFrame = new JFrame();
        mainFrame.setTitle("193095 Jakub Szurmak");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize((int) (screenSize.getWidth() * 0.8), (int) (screenSize.getHeight() * 0.7));
        mainFrame.setLayout(new GridBagLayout());

        top.setLayout(new GridLayout(mainWorld.getM(), mainWorld.getN()));
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(0, 2));

        side.setLayout(new GridLayout(0,1));

        GridBagConstraints constrs = new GridBagConstraints();
        constrs.fill = GridBagConstraints.BOTH;

        constrs.gridx = 0;
        constrs.gridy = 0;
        constrs.weighty = 0.7;
        constrs.weightx = 0.7;
        mainFrame.add(top, constrs);
        this.addGuiBoard(top);

        constrs.gridx = 0;
        constrs.gridy = 1;
        constrs.weighty = 0.3;
        constrs.weightx = 0.7;
        mainFrame.add(bottom, constrs);
        this.addControlButtons(bottom);

        constrs.gridx = 1;
        constrs.gridy = 0;
        constrs.weighty = 1;
        constrs.weightx = 0.3;
        mainFrame.add(side, constrs);
        this.addControlLabels(side);
        this.addComentator(side);

        mainCommentator = new Commentator(comments);
        currWorld.mainCommentator = mainCommentator;
        currWorld.updateCommentator();

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();
    }
}
