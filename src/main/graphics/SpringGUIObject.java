package main.graphics;

import main.map.MapElement;
import main.map.Spring;
import main.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SpringGUIObject extends GUIObject{

    private Spring spring;
    private Point position;

    private Polygon triangle;

    public SpringGUIObject(Spring spring){
        this.spring = spring;
        triangle = new Polygon();
        triangle.addPoint(7, 0);
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
        // Felső csúcs koordinátái
        int sideLength = 20;
        int bottomX = point.x;
        int bottomY = point.y - sideLength;
        Point bottomVertex = new Point(bottomX, bottomY);

        // Bal alsó csúcs koordinátái
        int leftX = point.x - sideLength/2;
        int leftY = (int) (point.y + (sideLength/2) * Math.sqrt(3));
        Point leftVertex = new Point(leftX, leftY);

        // Jobb alsó csúcs koordinátái
        int rightX = point.x + sideLength/2;
        int rightY = (int) ( point.y + (sideLength/2) * Math.sqrt(3));
        Point rightVertex = new Point(rightX, rightY);

        //TODO Ezt nem tudom mikor hívjuk meg extrába, kell e neki paraméterként a Graphics objektum, meg stb,
        //TODO de a három pontja megvan a háromszögnek, csak ki kell tölteni.
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public MapElement getElement() {
        return spring;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
}
