package main.graphics;

import main.Controller;
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
        position = GUIManager.getInstance().getGUIObjectByID(saboteur.getMapElement().getLogID()).getPosition();
        g.setColor(Color.ORANGE);
        g.fillOval(position.x-10, position.y-10, 20, 20);
        if(Controller.CURRENT_PLAYER != null && Controller.CURRENT_PLAYER.equals(getPlayer())){
            g.setColor(new Color(20, 125, 20));
        }else
            g.setColor(Color.BLACK);
        g.drawOval(position.x-10, position.y-10, 20, 20);

        if(saboteur.getStuck() > 0) drawStuck(position, g);
    }

    public void drawStuck(Point point, Graphics2D g){
        g.setColor(new Color(125, 125, 125));

        // Convert angle from degrees to radians
        double angleRadians = Math.toRadians(45);

        //Getting the first line's two ends
        // Calculate the coordinate of the points of the first line
        int point1X = (int) (point.x + 14 * Math.cos(angleRadians));
        int point1Y = (int) (point.y + 14 * Math.sin(angleRadians));

        int point2X = (int) (point.x - 14 * Math.cos(angleRadians));
        int point2Y = (int) (point.y - 14 * Math.sin(angleRadians));

        //Getting the second line's two ends
        int point3X = (int) (point.x - 14 * Math.cos(angleRadians));
        int point3Y = (int) (point.y + 14 * Math.sin(angleRadians));

        int point4X = (int) (point.x + 14 * Math.cos(angleRadians));
        int point4Y = (int) (point.y - 14 * Math.sin(angleRadians));

        Stroke tmp = g.getStroke();
        g.setStroke(new BasicStroke(2));
        g.drawLine(point1X, point1Y, point2X, point2Y);
        g.drawLine(point3X, point3Y, point4X, point4Y);
        g.setStroke(tmp);

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

    /**
     * Beállítja a GUI elem pozícióját
     * @param p A Beállítandó pont
     * */
    public void setPosition(Point p){position = p;}

    @Override
    public MapElement getElement() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Player getPlayer() {
       return saboteur;
    }
}
