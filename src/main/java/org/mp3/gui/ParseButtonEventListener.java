package org.mp3.gui;

import org.apache.tika.exception.TikaException;
import org.mp3.Controllers.Mp3Controller;
import org.mp3.Controllers.fileController;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ParseButtonEventListener implements ActionListener {
    static String path = "files/raw/";
    private Mp3Controller mp3;
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList files = fileController.getFilenames();
        for (Object i : files) {
            try {
                mp3 = new Mp3Controller(path + (String) i);
            } catch (TikaException | IOException | SAXException ex) {
                throw new RuntimeException(ex);
            }
            try {
                JOptionPane.showMessageDialog(null, mp3.data2js(),"Parsed Data",JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
