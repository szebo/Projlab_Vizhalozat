package main.graphics;

import main.Controller;
import main.map.MapElement;
import main.map.Pipe;
import main.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PipeGUIObject extends GUIObject{

    private Pipe pipe;
    private Point position;

    private Rectangle rectangle;

    public PipeGUIObject(Pipe pipe){
        this.pipe = pipe;
        /* Itt kellene beállítani, hogy milyen messze vannak a szélei a becsatolandó pumpáktól*/
        rectangle = new Rectangle(0, 0, 100, 3);      //TODO megfelelő szélesség beállítása
    }

    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){
             if(pipe.acceptPlayer(Controller.CURRENT_PLAYER)){
             }
        }
    }

    @Override
    public void draw(Graphics g){

        //Ha van benne víz, de nincs tele, akkor fekete teglalap, kis körvvonallal
        if(pipe.getWater() != 0){
            g.setColor(Color.blue);
            g.drawLine
                (
                    GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition().x,
                    GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition().y,
                    GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition().x,
                    GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition().y
                );

        }

        //Ha törött
        else if(pipe.isBroken()){
            drawBroken(this.position, g);
        }

        //Minden más esetben üres, és fekete
        else{
            g.setColor(Color.BLACK);
            g.drawLine
                    (
                            GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition().x,
                            GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[0].getLogID()).getPosition().y,
                            GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition().x,
                            GUIManager.getInstance().getGUIObjectByID(pipe.getNeighbours()[1].getLogID()).getPosition().y
                    );
        }

        //Státusz értékek a cső felett.
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

    private void drawBroken(Point point, Graphics g){
        g.setColor(Color.RED);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public MapElement getElement() {
        return pipe;
    }
    @Override
    public Player getPlayer() {
        return null;
    }
}
