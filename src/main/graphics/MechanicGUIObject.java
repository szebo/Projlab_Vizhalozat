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
    }

    public void drawAtPosition(Point point){

    }

    @Override
    public Point getPosition() {
        return null;
    }
}
