package main.graphics;

import main.Controller;
import main.map.MapElement;
import main.players.Mechanic;
import main.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MechanicGUIObject extends GUIObject{

    /**
     * A GUI objektum kapcsolata a modell-beli ciszternával
     * */
    private Mechanic mechanic;

    /**
     * A GUI Mechanic pozícióját leíró attribútum, így tudunk köré helyezni más entitásokat
     * */
    private Point position;

    public MechanicGUIObject(Mechanic mechanic){
        this.mechanic = mechanic;
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
        position = GUIManager.getInstance().getGUIObjectByID(mechanic.getMapElement().getLogID()).getPosition();
        g.setColor(Color.CYAN);
        g.fillOval(position.x-10, position.y-10, 20, 20);
        if(Controller.CURRENT_PLAYER != null && Controller.CURRENT_PLAYER.equals(getPlayer())){
            g.setColor(new Color(20, 125, 20));
        }else
            g.setColor(Color.BLACK);
        g.drawOval(position.x-10, position.y-10, 20, 20);

        if(mechanic.getStuck() > 0) drawStuck(position, g);
        if(mechanic.getPipeInHand() != null) drawPipeInHand(position, g);
    }

    /**
     * A Szerelő beragadt állapotának kirajzolása
     */
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

    /**
     * A szerelő kirajzolása egy csővel a kezében
     * @param point Pont
     * @param g Grafika
     * */
    public void drawPipeInHand(Point point, Graphics g){
        //Ilyenkor csak egy ferde vonallal van áthúzva
        if(mechanic.getPipeInHand() == null){return;}

        double angleRadians = Math.toRadians(45);
        //Getting the second line's two ends
        int point1X = (int) (point.x-10 - 20 * Math.cos(angleRadians));
        int point1Y = (int) (point.y-10 + 20 * Math.sin(angleRadians));

        int point2X = (int) (point.x-10 + 20 * Math.cos(angleRadians));
        int point2Y = (int) (point.y-10 - 20 * Math.sin(angleRadians));

        //Hogy látszódjon akkor is a kezében a cső, ha stuck
        if(mechanic.getStuck() == 0) g.setColor(Color.black);
        else g.setColor(Color.red);
        g.drawLine(point1X, point1Y, point2X, point2Y);

    }

    /**
     * Visszaadja a GUI elem pozícióját
     * @return poisiton attribútum
     * */
    @Override
    public Point getPosition() {
        return position;
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
     * @return A GUI szerelő modellbeli megfelelője
     */
    @Override
    public Player getPlayer() {
        return mechanic;
    }
}
