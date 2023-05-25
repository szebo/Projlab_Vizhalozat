package main.windowing;

import main.graphics.GUIManager;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    JButton menu;
    public GameView(Window window){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        menu = new JButton("Menu");
        menu.addActionListener(window);
        add(menu);
        setBackground(Color.CYAN);
    }

    /*public void paintComponent(Graphics g){
        GUIManager.getInstance().draw(this);
    }*/
}
