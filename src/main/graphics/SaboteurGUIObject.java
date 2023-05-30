package main.graphics;

import main.map.MapElement;
import main.players.Player;
import main.players.Saboteur;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SaboteurGUIObject extends GUIObject{

    private Saboteur saboteur;
    private Point position;

    public SaboteurGUIObject(Saboteur saboteur){
        this.saboteur = saboteur;
        position = GUIManager.getInstance().getGUIObjectByID(saboteur.getMapElement().getLogID()).getPosition();
    }
    /**
     * A GUI objektum kattintásának működését megvalósító függvény
     * @param e A kattintás jellemzői
     * */
    @Override
    public void onClick(MouseEvent e) {

    }
    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.ORANGE);
        g.fillOval(position.x-10, position.y-10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(position.x-10, position.y-10, 20, 20);

        if(saboteur.getStuck() > 0) drawStuck(position, g);
    }

    public void drawStuck(Point point, Graphics g){
            g.setColor(Color.black);

            // Convert angle from degrees to radians
            double angleRadians = Math.toRadians(45);

            //Getting the first line's two ends
            // Calculate the coordinate of the points of the first line
            int point1X = (int) (point.x-10 + 20 * Math.cos(angleRadians));
            int point1Y = (int) (point.y-10 + 20 * Math.sin(angleRadians));

            int point2X = (int) (point.x-10 - 20 * Math.cos(angleRadians));
            int point2Y = (int) (point.y-10 - 20 * Math.sin(angleRadians));

            //Getting the second line's two ends
            int point3X = (int) (point.x-10 - 20 * Math.cos(angleRadians));
            int point3Y = (int) (point.y-10 + 20 * Math.sin(angleRadians));

            int point4X = (int) (point.x-10 + 20 * Math.cos(angleRadians));
            int point4Y = (int) (point.y-10 - 20 * Math.sin(angleRadians));


            g.drawLine(point1X, point1Y, point2X, point2Y);
            g.drawLine(point3X, point3Y, point4X, point4Y);

     }


    public void drawAtPosition(Point point){
        position = point;
        //invalidate vagy draw
    }

    /**
     * Visszaadja a GUI elem pozícióját
     * @return poisiton attribútum
     * */
    @Override
    public Point getPosition() {
        return null;
    }

    public void setPosition(Point p){position = p;}

    @Override
    public MapElement getElement() {
        return saboteur.getMapElement();
    }

    /**
     * @return
     */
    @Override
    public Player getPlayer() {
       return saboteur;
    }
}
