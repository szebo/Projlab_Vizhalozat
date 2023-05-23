package main.graphics;

import main.map.Spring;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SpringGUIObject extends GUIObject{

    private Spring spring;
    private Point position;

    private Polygon triangle;

    public SpringGUIObject(Spring spring){
        this.spring = spring;
        triangle = new Polygon();
        triangle.addPoint(10, 0);
        triangle.addPoint(0, 10);
        triangle.addPoint(14, 10);
    }

    @Override
    public void onClick(MouseEvent e) {
        if(triangle.contains(e.getPoint())){

        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillPolygon(triangle);
    }

    public void drawAtPosition(Point point){

    }

    @Override
    public Point getPosition() {
        return position;
    }
}
