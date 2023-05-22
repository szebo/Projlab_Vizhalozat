package main.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GUIObject extends AbstractButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        onClick();
    }
    public abstract void onClick();
    public void draw(){ this.invalidate(); }
    public abstract Point getPosition();

}
