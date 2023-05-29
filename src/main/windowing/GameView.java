package main.windowing;

import main.Controller;
import main.graphics.GUIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameView extends JPanel implements MouseListener {

    JButton menu;
    public static JLabel actions;

    public GameView(Window window){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        menu = new JButton("Menu");
        menu.addActionListener(window);
        setBackground(new Color(230, 230, 150));
        actions = new JLabel("test_action_label", JLabel.CENTER);   //TODO A actions labelt jobb helyre tenni
        add(menu);
        GUIManager.getInstance().setGraphics(this);
        addMouseListener(this);
        add(actions);
        /*
         Demó, hogy itt is legyen valami
        GUIManager.getInstance().createCisternGUIObject(new Cistern(), new Point(100,100));
        Pump pu = new Pump();
        GUIManager.getInstance().createPumpGUIObject(pu, new Point(200, 200));
        Pipe p = new Pipe();
        pu.attachPipe(p);
        pu.setInput(p);
        * komplexebb matek kellene, hogy a cső közepét számoljuk a pontba,
        * Jelenleg a baloldalát adjuk át, de így a input cső számítás nem lesz jó
        * *
        GUIManager.getInstance().createPipeGUIObject(p, new Point(210, 210));
        */
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
