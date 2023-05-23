package main.graphics;

import main.map.Pump;

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

        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(position.x, position.y, 20, 20);
    }

    private void drawWorking(Point point){

    }

    private void drawBroken(Point point){

    }

    @Override
    public Point getPosition() {
        return position;
    }
}
