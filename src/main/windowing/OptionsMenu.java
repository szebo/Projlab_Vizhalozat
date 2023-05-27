package main.windowing;

import main.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class OptionsMenu extends JPanel implements ChangeListener {

    JSlider playerNumber, stickyTurns, slipperyTurns;
    JLabel label1, label2, label3;

    JButton bSave, bBack;

    public OptionsMenu(Window window){
        playerNumber = new JSlider(4, 8);
        playerNumber.addChangeListener(this);
        label1 = new JLabel(String.valueOf(playerNumber.getValue()));

        stickyTurns = new JSlider(1, 4);
        stickyTurns.addChangeListener(this);
        label2 = new JLabel(String.valueOf(stickyTurns.getValue()));

        slipperyTurns = new JSlider(1, 4);
        slipperyTurns.addChangeListener(this);
        label3 = new JLabel(String.valueOf(slipperyTurns.getValue()));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Player Count:"));
        p1.add(playerNumber);
        p1.add(label1);
        add(p1, c);

        c.gridy = 1;
        JPanel p2 = new JPanel();
        p2.add(new JLabel("Pipes sticky for turns:"));
        p2.add(stickyTurns);
        p2.add(label2);
        add(p2, c);

        c.gridy = 2;
        JPanel p3 = new JPanel();
        p3.add(new JLabel("Pipe slippery for turns:"));
        p3.add(slipperyTurns);
        p3.add(label3);
        add(p3, c);

        c.gridy = 3;
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bSave = new JButton("Save");
        bSave.addActionListener(window);
        buttons.add(bSave);
        bBack = new JButton("Back");
        bBack.addActionListener(window);
        buttons.add(bBack);
        add(buttons, c);
    }

    public void saveOptions(){
        Controller.STICKY_FOR_OPTION = stickyTurns.getValue();
        Controller.SLIPPERY_FOR_OPTION = slipperyTurns.getValue();
        Controller.PLAYER_COUNT = playerNumber.getValue();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource().equals(playerNumber))
            label1.setText(String.valueOf(playerNumber.getValue()));
        else if(e.getSource().equals(stickyTurns))
            label2.setText(String.valueOf(stickyTurns.getValue()));
        else if(e.getSource().equals(slipperyTurns))
            label3.setText(String.valueOf(slipperyTurns.getValue()));
        repaint();
    }
}
