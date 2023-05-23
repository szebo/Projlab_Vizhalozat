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
        if(rectangle.contains(e.getPoint())){

        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(rectangle.x, rectangle.y, 20, 20);
    }

    public void drawAtPosition(Point point){

    }

    @Override
    public Point getPosition() {
        return position;
    }
}
