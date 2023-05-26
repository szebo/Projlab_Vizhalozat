package main.graphics;

import main.map.Pump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PumpGUIObject extends GUIObject{

    private Pump pump;
    private Point position;

    //Ez a négyzet kell a kattintás helyének ellenőrzésére.
    private Rectangle rectangle;

    public PumpGUIObject(Pump pump){
        this.pump = pump;
        rectangle = new Rectangle();
    }

    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){
            if(pump.getPlayers().contains(/*hívó játékos*/)){
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(new JButton("Configure"));
                buttonGroup.
            }
            else {
                if (pump.acceptPlayer(/*hívó játékos*/)){
                    player.drawPosition();
                }
            }

        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(position.x, position.y, 20, 20);
        if(this.pump.isBroken()) drawBroken(position, g);
        else drawWorking(position, g);
    }

    private void drawWorking(Point point, Graphics g){
        g.setColor(Color.green);
        g.fillOval(position.x, position.y, 5, 5);
    }

    private void drawBroken(Point point, Graphics g){
        g.setColor(Color.red);

        // Convert angle from degrees to radians
        double angleRadians = Math.toRadians(45);

        //Getting the first line's two ends
        // Calculate the coordinate of the points of the first line
        int point1X = (int) (point.x + 10 * Math.cos(angleRadians));
        int point1Y = (int) (point.y + 10 * Math.sin(angleRadians));

        int point2X = (int) (point.x - 10 * Math.cos(angleRadians));
        int point2Y = (int) (point.y - 10 * Math.sin(angleRadians));

        //Getting the second line's two ends
        int point3X = (int) (point.x - 10 * Math.cos(angleRadians));
        int point3Y = (int) (point.y + 10 * Math.sin(angleRadians));

        int point4X = (int) (point.x + 10 * Math.cos(angleRadians));
        int point4Y = (int) (point.y - 10 * Math.sin(angleRadians));


        g.drawLine(point1X, point1Y, point2X, point2Y);
        g.drawLine(point3X, point3Y, point4X, point4Y);
    }



    @Override
    public Point getPosition() {
        return position;
    }
}
