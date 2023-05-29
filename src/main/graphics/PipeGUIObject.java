package main.graphics;

import main.Controller;
import main.map.MapElement;
import main.map.Pipe;
import main.players.Player;

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
     * A GUI csövön lévő téglalap, amely a kattintási felületet reprezentálja
     * */
    private Rectangle rectangle;

    /**
     * A GUI cső konstruktora
     * @param pipe A modellbeli megfelelője az éppen létrejövő GUI csőnek
     * */
    public PipeGUIObject(Pipe pipe){
        this.pipe = pipe;
        /* Itt kellene beállítani, hogy milyen messze vannak a szélei a becsatolandó pumpáktól*/
        rectangle = new Rectangle(0, 0, 100, 3);      //TODO megfelelő szélesség beállítása
    }

    /**
     * A Csőre való kattintás viselkedését megvalósító függvény
     * @param e A kattintás tulajdonságai
     * */
    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){
             if(pipe.acceptPlayer(Controller.CURRENT_PLAYER)){
             }
        }
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

        //Kirajzolás a megfelelő színnel
        g.drawLine
                (
                        GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition().x,
                        GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition().y,
                        GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition().x,
                        GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition().y
                );

        //Státusz értékek a cső felett:
        if (pipe.checkSlippery()){
            g.setColor(Color.ORANGE);
            g.drawString(Integer.toString(pipe.getSlipperyFor()), this.position.x, position.y);
        }
        if (pipe.checkSticky()){
            g.setColor(Color.GREEN);
            g.drawString(Integer.toString(pipe.getStickyFor()), this.position.x, position.y);
        }
        if (pipe.checkUnbreakable()){
            g.setColor(new Color(102,51, 0));
            g.drawString(Integer.toString(pipe.getUnbreakableFor()), this.position.x, position.y);
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
        return position;
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
