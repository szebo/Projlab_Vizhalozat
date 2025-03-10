package main.windowing;

import main.Controller;
import main.graphics.GUIManager;
import main.map.Map;

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
        setResizable(false); //Optimized to 720p
        mainPanel = new JPanel();
        layout = new CardLayout();

        mainPanel.setLayout(layout);

        mainMenu = new MainMenu(this);
        optionsMenu = new OptionsMenu(this);
        newGameMenu = new NewGameMenu(this);
        pauseMenu = new PauseMenu(this);
        gameView = new GameView(this);
        endScreen = new EndScreen(this);
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
        addKeyListener(Controller.key);
    }

    private void switchPanel(String panel){
        layout.show(mainPanel, panel);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(mainMenu.bNewgame)){
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
            gameView.repaint();
            gameView.start();
        }
        /*else if(e.getSource().equals(pauseMenu.bOptions)){
            switchPanel("Options"); Options menüből mindenféle képpen a főmenübe lép vissza.
        }*/
        else if(e.getSource().equals(pauseMenu.bExitToMain)){
            switchPanel("MainMenu");
            GUIManager.getInstance().clearMap();
            Map.getInstance().resetAfterTest();
        }
        else if(e.getSource().equals(optionsMenu.bBack)){
            switchPanel("MainMenu");
        }
        else if(e.getSource().equals(optionsMenu.bSave)){
            optionsMenu.saveOptions();
            switchPanel("MainMenu");
        }
        else if(e.getSource().equals(gameView.menu)){
            switchPanel("Pause");
            gameView.stop();
        }
        else if(e.getSource().equals(newGameMenu.bBack)){
            switchPanel("MainMenu");
        }
        else if(e.getSource().equals(endScreen.bBack)){
            switchPanel("MainMenu");
        }
        else if(e.getSource().equals("win")){
            gameView.stop();
            switchPanel("EndScreen");
        }
        else if(e.getSource().equals(newGameMenu.bStart)){
            System.out.println(newGameMenu.lMapsList.getSelectedValue().toString());
            Map.getInstance().loadMap(newGameMenu.lMapsList.getSelectedValue().toString());
            switchPanel("GameView");
            gameView.repaint();
            Controller.init();
            gameView.start();
        }
    }

    public void start(){
        gameView.start();
    }

    public void stop(){
        gameView.stop();
    }

    public void playerActed(){
        gameView.playerActed();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && gameView.isVisible()){
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
