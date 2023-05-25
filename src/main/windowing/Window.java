package main.windowing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener, KeyListener, ComponentListener {
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
        setFocusable(true);
        addKeyListener(this);
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
        else if(e.getSource().equals(mainMenu.bExit) || e.getSource().equals(pauseMenu.bExit)){
            System.exit(1);
        }
        else if(e.getSource().equals(pauseMenu.bContinue)){
            switchPanel("GameView");
        }
        else if(e.getSource().equals(pauseMenu.bExitToMain)){
            switchPanel("MainMenu");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            switchPanel("Pause");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {
        mainMenu.setVisible(false);
        pauseMenu.setVisible(false);
        optionsMenu.setVisible(false);
        newGameMenu.setVisible(false);
        endScreen.setVisible(false);
        gameView.setVisible(false);
        e.getComponent().setVisible(true);
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
