package main.windowing;

import main.graphics.GUIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameView extends JPanel implements MouseListener {

    JButton menu;
    public GameView(Window window){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        menu = new JButton("Menu");
        menu.addActionListener(window);
        setBackground(new Color(230, 230, 150));
        add(menu);
        GUIManager.getInstance().setGraphics(this);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        GUIManager.getInstance().draw(g2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GUIManager.getInstance().clickObjects(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
