package main.graphics;

import main.map.Pipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PipeGUIObject extends GUIObject{

    private Pipe pipe;
    private Point position;

    private Rectangle rectangle;
    private JLabel numbers[] = new JLabel[3];       //TODO biztos lehet elegánsabban is

    public PipeGUIObject(Pipe pipe){
        this.pipe = pipe;
        rectangle = new Rectangle();
    }

    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){
             if(pipe.acceptPlayer(/*hívó játékos*/)){
                 pipe.getPlayers().get(0).
             }
        }
    }

    @Override
    public void draw(Graphics g){
        if(pipe.isBroken()){
            drawBroken(this.position, g);
        }
        if (pipe.checkSlippery()){
            numbers[1].setText(Integer.toString(pipe.getSlipperyFor()));
            numbers[1].setForeground(Color.ORANGE);
        }
        if (pipe.checkSticky()){
            numbers[0].setText(Integer.toString(pipe.getStickyFor()));
            numbers[0].setForeground(Color.GREEN);
        }
        if (pipe.checkUnbreakable()){
            numbers[3].setText(Integer.toString(pipe.getUnbreakableFor()));
            numbers[3].setForeground(Color.ORANGE);
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
}
