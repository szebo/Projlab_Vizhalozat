package main.graphics;

import main.map.MapElement;
import main.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GUIObject implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e){
            onClick(e);
    }

    public abstract void onClick(MouseEvent e);
    public abstract void draw(Graphics g);
    public abstract Point getPosition();
    public abstract MapElement getElement();
    public abstract Player getPlayer();

    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}
}
