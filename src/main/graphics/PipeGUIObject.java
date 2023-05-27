package main.graphics;

import main.Controller;
import main.map.MapElement;
import main.map.Pipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PipeGUIObject extends GUIObject{

    private Pipe pipe;
    private Point position;

    private Rectangle rectangle;
    private JLabel[] numbers = new JLabel[3];       //TODO biztos lehet elegánsabban is

    public PipeGUIObject(Pipe pipe){
        this.pipe = pipe;
        rectangle = new Rectangle();
        for(int i = 0; i < 3; i++){
            numbers[i] = new JLabel();
        }
    }

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
            numbers[0].setText(Integer.toString(pipe.getSlipperyFor()));
            numbers[0].setForeground(Color.ORANGE);
        }
        if (pipe.checkSticky()){
            numbers[1].setText(Integer.toString(pipe.getStickyFor()));
            numbers[1].setForeground(Color.GREEN);
        }
        if (pipe.checkUnbreakable()){
            numbers[2].setText(Integer.toString(pipe.getUnbreakableFor()));
            numbers[2].setForeground(Color.ORANGE);
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
}
