package main.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GUIObject extends JComponent implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e){
        if(this.contains(e.getPoint())) {
            onClick();
        }
    }

    public abstract void onClick();
    public void draw(){ this.repaint(); }
    public abstract Point getPosition();

    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}
}
