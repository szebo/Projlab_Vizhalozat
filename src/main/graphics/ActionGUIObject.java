package main.graphics;

import java.awt.*;

public class ActionGUIObject extends GUIObject{

    public String guiMessage;

    @Override
    public void onClick() {

    }

    @Override
    public Point getPosition() {
        return getLocation();
    }

    @Override
    public void paintComponent(Graphics g){

    }
}
