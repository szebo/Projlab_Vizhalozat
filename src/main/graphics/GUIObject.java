package main.graphics;

import main.map.MapElement;
import main.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GUIObject{

    public static final int RECTANGLE_SIZE = 30;
    public static final int RECTANGLE_MID_OFFSET = 15;

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
}
