package main.graphics;

import main.Controller;
import main.map.MapElement;
import main.map.Pipe;
import main.players.Player;
import main.windowing.GameView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PipeGUIObject extends GUIObject{

    /**
     * A GUI cső modellbeli megfelelője
     * */
    private Pipe pipe;

    /**
     * A GUI cső pozícióját leíró attribútum, így tudunk köré helyezni más entitásokat
     * */
    private Point position;

    /**
     * A GUI cső konstruktora
     * @param pipe A modellbeli megfelelője az éppen létrejövő GUI csőnek
     * */
    public PipeGUIObject(Pipe pipe){
        this.pipe = pipe;
        /* Itt kellene beállítani, hogy milyen messze vannak a szélei a becsatolandó pumpáktól*/

    }

    /**
     * A Csőre való kattintás viselkedését megvalósító függvény
     * @param e A kattintás tulajdonságai
     * */
    @Override
    public void onClick(MouseEvent e) {
        if((getClickDistance(e) < 10) && isBetweenEndPoints(e) ){
            if(Controller.SELECTED_ELEMENT == null)
                Controller.SELECTED_ELEMENT = pipe;
            else
                Controller.SECOND_SELECTED_ELEMENT = pipe;

            System.out.println("Pipe selected: " + Controller.SELECTED_ELEMENT.getLogID());
            if(pipe.getPlayers().contains(Controller.CURRENT_PLAYER)){
                String actionsString = "<html><br>make slippery";
                if(pipe.isBroken()){actionsString = actionsString.concat("<br>heal");}
                else{actionsString = actionsString.concat("<br>break");}
                actionsString.concat("</html>");
                System.out.println(actionsString);
                GameView.actions.setText(actionsString);
            }
        }
    }

    /**
     * Ellenőrzi, hogy a kattintott pont egy cső két végei között van-e
     * @param e A kattintás tulajdonságai
     * @return Igaz, ha a két pont között van, hamis ha nem
     */
    public boolean isBetweenEndPoints(MouseEvent e) {
        Point p1 = GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition();
        Point p2 = GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition();
        double cpX = e.getX();
        double cpY = e.getY();

        double l1x = p1.x - p2.x;
        double l1y = p1.y - p2.y;

        // l1 = p1 - p2
        // l2 = -l1
        // r1 = p1 - cP
        // r2 = p2 - cP    MARADNAK!!
        // dot(l1,r1) > 0
        // dot(l2,r2) > 0

        double l2x = -l1x;
        double l2y = -l1y;

        double r1x = p1.x - cpX;
        double r1y = p1.y - cpY;

        double r2x = p2.x - cpX;
        double r2y = p2.y - cpY;

        if ((r1x * l1x) + (r1y * l1y) < 0) return false;
        if ((r2x * l2x) + (r2y * l2y) < 0) return false;

        return true;
    }

    /**
     * Visszadja a kattintott pont és egy pumpa közti távolságot
     * @param e A kattintás tulajdonságai
     * @return A távolság a két pont között
     */
    public double getClickDistance(MouseEvent e) {
        Point clickPoint = e.getPoint();
        MapElement[] neighbours = pipe.getNeighbours();
        Point p1 = GUIManager.getInstance().getGUIObjectByID(neighbours[0].getLogID()).getPosition();
        //Ez nem száll el, ha egy olyan csőre kattintunk, aminek egyik vége már kézben van, hogy felvegyük a másikat?
        Point p2 = new Point(p1.x+20, p1.y+20);
        if(neighbours.length>1 && neighbours[1] != null)
            p2 = GUIManager.getInstance().getGUIObjectByID(neighbours[1].getLogID()).getPosition();
        double p1Distance = p1.distance(clickPoint);
        double p2Distance = p2.distance(clickPoint);

        //Pumpa click resolve
        if(p1Distance <= 19 || p2Distance <= 19) return 100;

        double p1p2Distance = p2.distance(p1);
        double m = (Math.pow(p1Distance, 2) - Math.pow(p2Distance, 2) + Math.pow(p1p2Distance, 2)) / (2 * p1p2Distance);
        return Math.sqrt(Math.pow(p1Distance, 2) - Math.pow(m, 2));
    }

    /**
     * A cső kirajzolásáért felelős függvény, amely minden lehetőséget megvizsgálva
     * rajzolja ki a csövet a megfelelő állapotúra
     * @param g Grafika
     * */
    @Override
    public void draw(Graphics2D g){

        //Ha van benne víz, de nincs tele, akkor fekete teglalap, kis körvvonallal
        if(pipe.getWater() != 0){
            g.setColor(Color.BLUE);
            g.setStroke(new BasicStroke(5.0f));
        }

        //Ha törött
        else if(pipe.isBroken()){
        g.setColor(Color.RED);
        }

        //Minden más esetben üres és fekete
        else{
            g.setColor(Color.BLACK);
        }

        Point p2;
        Point p1 = new Point(GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition());
        if(pipe.getNeighbours().length > 1 && pipe.getNeighbours()[1] != null)
            p2 = new Point(GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition());
        else
            p2 = new Point(p1.x+20, p1.y+20);

        //Kirajzolás a megfelelő színnel és pontra
        g.setStroke(new BasicStroke(10));
        g.drawLine(p1.x, p1.y, p2.x, p2.y);

        //Státusz értékek a cső felett:
        if (pipe.checkSlippery()){
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Arial", Font.PLAIN,20));
            g.drawString(String.valueOf(pipe.getSlipperyFor()), getPosition().x, getPosition().y - 20);
        }
        if (pipe.checkSticky()){
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.PLAIN,20));
            g.drawString(String.valueOf(pipe.getStickyFor()), getPosition().x - 5, getPosition().y - 20);
        }
        if (pipe.checkUnbreakable()){
            g.setColor(new Color(102,51, 0));
            g.setFont(new Font("Arial", Font.PLAIN,20));
            g.drawString(String.valueOf(pipe.getUnbreakableFor()), getPosition().x + 5, getPosition().y - 20);
        }

    }

    private double differenceFromHorizontal(Point p1, Point p2){
        Point vector = new Point(p1.x-p2.x, p1.y-p2.y);
        return vector.x;
    }

    private void drawWorking(Point point){
    }

    /**
     * Pozíció getter
     * @return Pozíció
     * */
    @Override
    public Point getPosition() {
        MapElement[] neighbours = pipe.getNeighbours();
        Point end1 = GUIManager.getInstance().getGUIObjectByID(neighbours[0].getLogID()).getPosition();
        Point end2 = new Point(end1.x+20, end1.y+20);
        if(neighbours.length > 1 && neighbours[1] != null)
            end2 = GUIManager.getInstance().getGUIObjectByID(neighbours[1].getLogID()).getPosition();
        return new Point((end1.x+end2.x)/2, (end1.y+end2.y)/2);
    }

    /**
     * Beállítja a GUI elem pozícióját
     * @param p A Beállítandó pont
     * */
    @Override
    public void setPosition(Point p) {}

    /**
     * A GUI elem modellbeli megfelelőjét visszaadó függvény
     * @return Modellbeli cső
     * */
    @Override
    public MapElement getElement() {
        return pipe;
    }
    @Override
    public Player getPlayer() {
        return null;
    }
}
