package main.graphics;

import main.Controller;
import main.map.Cistern;
import main.map.MapElement;
import main.players.Mechanic;
import main.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CisternGUIObject extends GUIObject{

    private Cistern cistern;
    private Point position;

    private Rectangle rectangle;

    public CisternGUIObject(Cistern cistern, Point position){
        this.cistern = cistern;
        this.position = position;
        rectangle = new Rectangle(position.x-RECTANGLE_MID_OFFSET, position.y-RECTANGLE_MID_OFFSET, RECTANGLE_SIZE, RECTANGLE_SIZE);
    }

    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())) {
            System.out.println("Cistern clicked");
            if (cistern.getPlayers().contains(Controller.CURRENT_PLAYER)) {
                cistern.givePump((Mechanic) Controller.CURRENT_PLAYER);    //TODO Kurva castolásra ki kell találni valamit, csak már baszta a szemem a piros aláhúzás
            }
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

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public MapElement getElement() {
        return cistern;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
}
