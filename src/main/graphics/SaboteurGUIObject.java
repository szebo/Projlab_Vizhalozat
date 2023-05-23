package main.graphics;

import main.players.Saboteur;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SaboteurGUIObject extends GUIObject{

    private Saboteur saboteur;
    private Point position;

    public SaboteurGUIObject(Saboteur saboteur){
        this.saboteur = saboteur;
    }
    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.CYAN);
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
