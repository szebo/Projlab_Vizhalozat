package main.windowing;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
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
        mainMenu = new MainMenu();
        optionsMenu = new OptionsMenu();
        newGameMenu = new NewGameMenu();
        pauseMenu = new PauseMenu();
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
        setVisible(true);
    }
}
