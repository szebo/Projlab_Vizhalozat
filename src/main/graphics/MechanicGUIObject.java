package main.graphics;

import main.players.Mechanic;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MechanicGUIObject extends GUIObject{

    private Mechanic mechanic;
    private Point position;

    public MechanicGUIObject(Mechanic mechanic){
        this.mechanic = mechanic;
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(position.x, position.y, 10, 10);
        g.setColor(Color.BLACK);
        g.drawOval(position.x, position.y, 10, 10);

        //TODO Ha stuck a karakter, akkor meghívjuk a drawStuck függvényt, ami áthúzza X-el? Mert akkor alatta beírtam
    }

    //public void drawStuck(Point point, Graphics g){
    //        g.setColor(Color.black);
    //
    //        // Convert angle from degrees to radians
    //        double angleRadians = Math.toRadians(45);
    //
    //        //Getting the first line's two ends
    //        // Calculate the coordinate of the points of the first line
    //        int point1X = (int) (point.x + 10 * Math.cos(angleRadians));
    //        int point1Y = (int) (point.y + 10 * Math.sin(angleRadians));
    //
    //        int point2X = (int) (point.x - 10 * Math.cos(angleRadians));
    //        int point2Y = (int) (point.y - 10 * Math.sin(angleRadians));
    //
    //        //Getting the second line's two ends
    //        int point3X = (int) (point.x - 10 * Math.cos(angleRadians));
    //        int point3Y = (int) (point.y + 10 * Math.sin(angleRadians));
    //
    //        int point4X = (int) (point.x + 10 * Math.cos(angleRadians));
    //        int point4Y = (int) (point.y - 10 * Math.sin(angleRadians));
    //
    //
    //        g.drawLine(point1X, point1Y, point2X, point2Y);
    //        g.drawLine(point3X, point3Y, point4X, point4Y);
    //
    // }

    public void drawAtPosition(Point point){

    }

    @Override
    public Point getPosition() {
        return null;
    }
}
