package GUI.GetSizePrompt;
import GUI.Organizer;

import javax.swing.*;

public class SizePrompt extends Organizer {
    private final JTextField textField1;
    private final JTextField textField2;
    private int x, y;

    public SizePrompt() {
        setTitle("Board Size input prompt. 193095");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("X size of board:");
        panel.add(label1);

        textField1 = new JTextField(10);
        panel.add(textField1);

        JLabel label2 = new JLabel("Y size of board:");
        panel.add(label2);

        textField2 = new JTextField(10);
        panel.add(textField2);

        JButton button = new JButton("Create Game");

        button.addActionListener(e -> {
            try {
                x = Integer.parseInt(textField1.getText());
                y = Integer.parseInt(textField2.getText());
                this.createWorld(x, y);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid board Size",
                        "Error message", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(button);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
}