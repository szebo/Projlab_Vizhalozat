package main.windowing;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private JButton bContinue, bNewgame, bOptions, bExit;
    private JPanel pCon, pNew, pOpt, pExit;
    public MainMenu(){
        bContinue = new JButton("Continue");
        bNewgame = new JButton("New Game");
        bOptions = new JButton("Options");
        bExit = new JButton("Exit");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pCon = new JPanel();
        pCon.add(bContinue);
        add(pCon, gbc);
        gbc.gridy = 1;
        pNew = new JPanel();
        pNew.add(bNewgame);
        add(pNew, gbc);
        gbc.gridy = 2;
        pOpt = new JPanel();
        pOpt.add(bOptions);
        add(pOpt, gbc);
        gbc.gridy = 3;
        pExit = new JPanel();
        pExit.add(bExit);
        add(pExit, gbc);
        setBackground(Color.ORANGE);
    }
}
