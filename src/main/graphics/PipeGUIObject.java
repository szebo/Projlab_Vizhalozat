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
            Controller.SELECTED_ELEMENT = pipe;
            System.out.println("Pipe selected: " + Controller.SELECTED_ELEMENT.getLogID());
            if(pipe.getPlayers().contains(Controller.CURRENT_PLAYER)){
                String actionsString = "<html><br>configure</html>";
                if(pipe.isBroken()){actionsString = actionsString.concat("<html><br>heal</html>");}
                else{actionsString = actionsString.concat("<html><br>break</html>");}
                GameView.actions.setText(actionsString);
            }
        }


    }

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

    public double getClickDistance(MouseEvent e) {
        Point clickPoint = e.getPoint();
        Point p1 = GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition();
        //Ez nem száll el, ha egy olyan csőre kattintunk, aminek egyik vége már kézben van, hogy felvegyük a másikat?
        Point p2 = GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition();
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

        Point p1 = new Point(GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition());
        Point p2 = new Point(GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition());

        //Kirajzolás a megfelelő színnel és pontra
        g.setStroke(new BasicStroke(10));
        g.drawLine(p1.x, p1.y, p2.x, p2.y);

        /*int[] x = {p1.x - 5, p1.x + 5, p2.x + 5, p2.x - 5};
        int[] y = {p1.y - 2, p1.y + 2, p2.y + 2, p2.y - 2};
        g.fillPolygon(x, y, 4);*/


        /*int width = Math.abs(p1.x-p2.x);        //a téglalap szélessége
        int heigth = Math.abs(p1.y-p2.y);       //a téglalap magassága

        /* Kiválasztjuk a baloldali pontot a kettő közül
        if(p1.x <= p2.x){ //Ha a p1 van balra
            /* Ha a bal felső pont nem a p1 pont, akkor p1 a bal alsó lesz, ki kell számolni a bal felsőt
            if(p1.y > p2.y) {
                rectangle = new Rectangle(p1.x, p1.y - heigth, width, heigth);   //a kattintás felületének beállítása negatív meredekségű csövekre
            } else {
                rectangle = new Rectangle(p1.x, p1.y, width, heigth);           //a kattintás felületének beállítása pozitív meredekségű csövekre
            }
        } else { //Ha a p2 van balra
            /* Ha a bal felső pont nem a p2 pont, akkor az a bal alsó lesz, ki kell számolni a bal felsőt
            if(p2.y > p1.y) {
                rectangle = new Rectangle(p2.x, p2.y - heigth, width, heigth);   //a kattintás felületének beállítása negatív meredekségű csövekre
            } else {
                rectangle = new Rectangle(p2.x, p2.y, width, heigth);           //a kattintás felületének beállítása pozitív meredekségű csövekre
            }
        }

        g.fill(rectangle); //debug
        */

        //Státusz értékek a cső felett:
        if (pipe.checkSlippery()){
            g.setColor(Color.ORANGE);
            g.drawString(Integer.toString(pipe.getSlipperyFor()), getPosition().x, getPosition().y);
        }
        if (pipe.checkSticky()){
            g.setColor(Color.GREEN);
            g.drawString(Integer.toString(pipe.getStickyFor()), getPosition().x, getPosition().y);
        }
        if (pipe.checkUnbreakable()){
            g.setColor(new Color(102,51, 0));
            g.drawString(Integer.toString(pipe.getUnbreakableFor()), getPosition().x, getPosition().y);
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
        Point end2 = GUIManager.getInstance().getGUIObjectByID(neighbours[1].getLogID()).getPosition();
        return new Point((end1.x+end2.x)/2, (end1.y+end2.y)/2);
    }

    /**
     * @param p
     */
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
