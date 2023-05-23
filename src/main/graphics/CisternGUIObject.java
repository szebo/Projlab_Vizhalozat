package main.graphics;

import main.map.Cistern;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CisternGUIObject extends GUIObject{

    private Cistern cistern;
    private Point position;

    private Rectangle rectangle;

    public CisternGUIObject(Cistern cistern){
        this.cistern = cistern;
        rectangle = new Rectangle();
    }

    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){

        }
    }

    @Override
    public void draw(Graphics g){

    }

    public void drawAtPosition(Point point){

    }

    @Override
    public Point getPosition() {
        return position;
    }
}
