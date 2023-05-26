package main.graphics;

import main.map.Cistern;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CisternGUIObject extends GUIObject{

    private Cistern cistern;
    private Point position;

    private Rectangle rectangle;

    public CisternGUIObject(Cistern cistern, Point position){
        this.cistern = cistern;
        this.position = position;
        rectangle = new Rectangle(position.x, position.y, 20, 20);
    }

    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())) {
            if (cistern.getPlayers().contains(/*hívó játékos*/)) {
                cistern.givePump(/*hívó játékos*/);     //Honnan tudjuk, melyik játékos kattintotta/van soron? Global lehetne a körön lévő játékos
            }
        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(new Color(0, 102, 0));
        g.fillRect(rectangle.x, rectangle.y, 20, 20);

    }

    public void drawAtPosition(Point point, Graphics g){
        g.setColor(new Color(0, 102, 0));
        g.fillRect(point.x, point.y, 20, 20);

    }

    @Override
    public Point getPosition() {
        return position;
    }
}
