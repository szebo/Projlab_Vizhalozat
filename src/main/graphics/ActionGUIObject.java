package main.graphics;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ActionGUIObject extends GUIObject{

    public String guiMessage;

    private Point position;

    public ActionGUIObject(){
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void draw(Graphics g){
    }
}
