package org.mp3.gui;

import org.mp3.Controllers.fileController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TenButtonEventListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, fileController.get10files(),"Last 10 Parsed Data",JOptionPane.PLAIN_MESSAGE);
    }
}
