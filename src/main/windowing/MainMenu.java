package main.windowing;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{
    JButton bNewgame, bOptions, bExit;
    private JPanel pNew, pOpt, pExit;
    public MainMenu(Window window){
        bNewgame = new JButton("New Game");
        bNewgame.addActionListener(window);
        bOptions = new JButton("Options");
        bOptions.addActionListener(window);
        bExit = new JButton("Exit");
        bExit.addActionListener(window);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        pNew = new JPanel();
        pNew.add(bNewgame);
        add(pNew, gbc);
        gbc.gridy = 1;

        pOpt = new JPanel();
        pOpt.add(bOptions);
        add(pOpt, gbc);
        gbc.gridy = 2;

        pExit = new JPanel();
        pExit.add(bExit);
        add(pExit, gbc);
        setBackground(Color.ORANGE);
    }
}
