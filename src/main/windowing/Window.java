package main.windowing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements ActionListener, KeyListener {
    private JPanel mainPanel;
    private MainMenu mainMenu;
    private OptionsMenu optionsMenu;
    private NewGameMenu newGameMenu;
    private PauseMenu pauseMenu;
    private GameView gameView;
    private EndScreen endScreen;
    private CardLayout layout;

    public Window(){
        mainPanel = new JPanel();
        layout = new CardLayout();
        mainPanel.setLayout(layout);
        mainMenu = new MainMenu(this);
        optionsMenu = new OptionsMenu();
        newGameMenu = new NewGameMenu();
        pauseMenu = new PauseMenu(this);
        gameView = new GameView();
        endScreen = new EndScreen();
        add(mainPanel);
        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(optionsMenu, "Options");
        mainPanel.add(newGameMenu, "NewGame");
        mainPanel.add(pauseMenu, "Pause");
        mainPanel.add(gameView, "GameView");
        mainPanel.add(endScreen, "EndScreen");
        layout.show(mainPanel, "MainMenu");
        setSize(1280, 720);
        setTitle("Sivatagi vizhalozat");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void switchPanel(String panel){
        layout.show(mainPanel, panel);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(mainMenu.bContinue)){
            switchPanel("GameView");
        }
        else if(e.getSource().equals(mainMenu.bNewgame)){
            switchPanel("NewGame");
        }
        else if(e.getSource().equals(mainMenu.bOptions)){
            switchPanel("Options");
        }
        else if(e.getSource().equals(mainMenu.bExit)){
            System.exit(1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 27 && gameView.isShowing()){
            switchPanel("Pause");
        }
    }
}
