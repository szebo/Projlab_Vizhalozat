package main.graphics;

import main.Controller;
import main.map.Cistern;
import main.map.MapElement;
import main.players.Mechanic;
import main.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A GUI megvalósítása a Ciszternának
 * */
public class CisternGUIObject extends GUIObject{

    /**
     * A GUI objektum kapcsolata a modell-beli ciszternával
     * */
    private Cistern cistern;


    /**
     * A GUI ciszterna pozícióját leíró attribútum, így tudunk köré helyezni más entitásokat
     * */
    private Point position;

    /**
     * A ciszternát reprezentáló téglalap
     * */
    private Rectangle rectangle;

    public CisternGUIObject(Cistern cistern, Point position){
        this.cistern = cistern;
        this.position = position;
        rectangle = new Rectangle(position.x-RECTANGLE_MID_OFFSET, position.y-RECTANGLE_MID_OFFSET, RECTANGLE_SIZE, RECTANGLE_SIZE);
    }

    /**
     * A GUI objektum kattintásának működését megvalósító függvény
     * @param e A kattintás jellemzői
     * */
    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())) {
            Controller.SELECTED_ELEMENT = cistern;


        }
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(new Color(0, 102, 0));
        g.fillRect(rectangle.x, rectangle.y, RECTANGLE_SIZE, RECTANGLE_SIZE);

    }

    public void drawAtPosition(Point point, Graphics g){
        g.setColor(new Color(0, 102, 0));
        g.fillRect(point.x, point.y, 20, 20);

    }

    /**
     * Visszaadja a GUI elem pozícióját
     * @return poisiton attribútum
     * */
    @Override
    public Point getPosition() {
        return position;
    }

    /**
     * Beállítja a GUI elem pozícióját
     * @param p A Beállítandó pont
     * */
    @Override
    public void setPosition(Point p) {}

    @Override
    public MapElement getElement() {
        return cistern;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
}
