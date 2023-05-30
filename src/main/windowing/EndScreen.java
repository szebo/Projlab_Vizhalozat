package main.windowing;

import main.players.MechanicTeam;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {

    JButton bBack;
    JLabel winnerTeam;


    public EndScreen(Window window){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        bBack = new JButton("Back To Menu");
        bBack.addActionListener(window);

        c.gridx = 0;
        c.gridy = 0;

        winnerTeam = new JLabel();
        if(MechanicTeam.getInstance().hasWon()) {
            winnerTeam.setText("Mechanic Team won!");
            setBackground(Color.CYAN);
        }
        else {
            winnerTeam.setText("Saboteur Team Won!");
            setBackground(Color.ORANGE);
        }
        add(winnerTeam, c);
        c.gridy = 1;
        add(bBack, c);

    }
}
