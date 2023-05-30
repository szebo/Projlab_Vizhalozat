package main.windowing;

import main.Controller;
import main.graphics.GUIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A játék közbeni nézetet valósítja meg
 * */
public class GameView extends JPanel implements MouseListener, ActionListener {

    /**
     * A Pause menü-be való lépés gombja
     * */
    JButton menu;

    /**
     * A pumpán végezhető akciókat kijelző felület
     * */
    public static JLabel actions;

    private Timer timer;

    public GameView(Window window){
        setLayout(new BorderLayout());
        menu = new JButton("Menu");
        menu.addActionListener(window);
        timer = new Timer(20, this);
        setBackground(new Color(230, 230, 150));
        actions = new JLabel();
        JPanel pMenu = new JPanel();
        pMenu.add(menu);
        pMenu.setBackground(new Color(230, 230, 150));
        pMenu.setOpaque(false);
        JPanel pActions = new JPanel();
        pActions.add(actions);
        pActions.setOpaque(false);
        pActions.setBackground(new Color(230, 230, 150));
        GUIManager.getInstance().setGraphics(this);
        addMouseListener(this);
        add(pMenu, BorderLayout.WEST);
        add(pActions, BorderLayout.EAST);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        GUIManager.getInstance().draw(g2);
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    public void playerActed(){
        timer.restart();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller.run();
        repaint();
    }
}
