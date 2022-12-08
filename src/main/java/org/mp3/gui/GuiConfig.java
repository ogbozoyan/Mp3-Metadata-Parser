package org.mp3.gui;

import javax.swing.*;
import java.awt.*;

public class GuiConfig extends JFrame{
    public GuiConfig() {
        super("MP3 Parser");
        this.setBounds(200, 200, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        ButtonGroup group = new ButtonGroup();
        JButton parseButton = new JButton("Parse");
        group.add(parseButton);
        JButton updateButton = new JButton("Update");
        group.add(updateButton);
        JButton getTenButton = new JButton("Get 10");
        group.add(getTenButton);
        parseButton.addActionListener(new ParseButtonEventListener());
        updateButton.addActionListener(new UpdateButtonEventListener());
        getTenButton.addActionListener(new TenButtonEventListener());
        container.add(parseButton);
        container.add(updateButton);
        container.add(getTenButton);
    }
}
