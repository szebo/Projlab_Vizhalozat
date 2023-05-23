package main.graphics;

import main.map.Pipe;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PipeGUIObject extends GUIObject{

    private Pipe pipe;
    private Point position;

    private Rectangle rectangle;

    public PipeGUIObject(Pipe pipe){
        this.pipe = pipe;
        rectangle = new Rectangle();
    }

    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){

        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
    }

    private void drawWorking(Point point){

    }

    private void drawBroken(Point point){

    }

    @Override
    public Point getPosition() {
        return position;
    }
}
