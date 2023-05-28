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

    public PipeGUIObject(Pipe pipe, Point point){
        position = point;
        this.pipe = pipe;
        /* Itt kellene beállítani, hogy milyen messze vannak a szélei a becsatolandó pumpáktól*/
        rectangle = new Rectangle(position.x, position.y, 100, 1);      //TODO megfelelő szélesség beállítása
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
        Graphics2D g2 = (Graphics2D) g.create();
        if(pipe.isBroken()){
            drawBroken(this.position, g2);
        }
        if (pipe.checkSlippery()){
            g2.setColor(Color.ORANGE);
            g2.drawString(Integer.toString(pipe.getSlipperyFor()), this.position.x, position.y);
        }
        if (pipe.checkSticky()){
            g2.setColor(Color.GREEN);
            g2.drawString(Integer.toString(pipe.getStickyFor()), this.position.x, position.y);
        }
        if (pipe.checkUnbreakable()){
            g2.setColor(new Color(102,51, 0));
            g2.drawString(Integer.toString(pipe.getUnbreakableFor()), this.position.x, position.y);
        }
        g2.setColor(Color.BLACK);
        g2.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        //TODO Ez elméletileg elforgatja a téglalapot, de nem vagyok benne biztos, hogy jó
        MapElement[] neighbours = pipe.getNeighbours();
        Point end1 = GUIManager.getInstance().getGUIObjectByID(neighbours[0].getLogID()).getPosition();
        Point end2 = GUIManager.getInstance().getGUIObjectByID(neighbours[1].getLogID()).getPosition();
        double theta = Math.acos(differenceFromHorizontal(end1, end2));
        g2.rotate(theta);
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
