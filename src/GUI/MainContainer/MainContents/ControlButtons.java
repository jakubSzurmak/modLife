package GUI.MainContainer.MainContents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtons extends JButton implements ActionListener {
    public ControlButtons(String command){
        super(command);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

    }
}
