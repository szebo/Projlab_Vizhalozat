package main.graphics;

import main.map.MapElement;
import main.players.Player;

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

    @Override
    public MapElement getElement() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
}
