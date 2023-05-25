package main.windowing;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends JPanel {
    JButton bContinue, bExitToMain, bOptions, bExit;
    private JPanel pCon, pETM, pOpt, pExit;
    public PauseMenu(Window window){
        bContinue = new JButton("Continue");
        bContinue.addActionListener(window);
        bExitToMain = new JButton("New Game");
        bExitToMain.addActionListener(window);
        bOptions = new JButton("Options");
        bOptions.addActionListener(window);
        bExit = new JButton("Exit");
        bExit.addActionListener(window);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pCon = new JPanel();
        pCon.add(bContinue);
        add(pCon, gbc);
        gbc.gridy = 1;
        pETM = new JPanel();
        pETM.add(bExitToMain);
        add(pETM, gbc);
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
