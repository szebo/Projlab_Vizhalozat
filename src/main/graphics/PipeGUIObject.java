package main.graphics;

import main.Controller;
import main.map.MapElement;
import main.map.Pipe;
import main.players.Player;

import javax.naming.ldap.Control;
import javax.swing.*;
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
             if(pipe.acceptPlayer(Controller.currentPlayer)){
             }
        }
    }

    @Override
    public void draw(Graphics g){
        if(pipe.isBroken()){
            drawBroken(this.position, g);
        }
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
        g.setColor(Color.BLACK);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

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
