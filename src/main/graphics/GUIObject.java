package main.graphics;

import main.map.MapElement;
import main.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GUIObject implements MouseListener {

    public static final int RECTANGLE_SIZE = 30;
    public static final int RECTANGLE_MID_OFFSET = 15;

    @Override
    public void mouseClicked(MouseEvent e){
            onClick(e);
    }

    public abstract void onClick(MouseEvent e);
    public abstract void draw(Graphics2D g);

    /**
     * Visszaadja a GUI elem pozícióját
     * @return poisiton attribútum
     * */
    public abstract Point getPosition();
    public abstract void setPosition(Point p);

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
