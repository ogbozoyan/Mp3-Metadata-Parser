package org.mp3.gui;

import org.mp3.Controllers.fileController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateButtonEventListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( fileController.getFilenames());
        JOptionPane.showMessageDialog(null, "Updated file list!","Update",JOptionPane.PLAIN_MESSAGE);
    }
}
