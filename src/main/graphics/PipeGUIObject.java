package main.graphics;

import main.map.Pipe;

import java.awt.*;

public class PipeGUIObject extends GUIObject{

    private Pipe pipe;
    private Point position;

    public PipeGUIObject(Pipe pipe){
        this.pipe = pipe;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void draw() {

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
