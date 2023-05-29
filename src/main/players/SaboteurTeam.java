package main.players;

import main.graphics.GUIManager;
import main.logging.Logger;
import main.map.Map;

import java.util.ArrayList;
import java.util.List;

public class SaboteurTeam {
    private static SaboteurTeam instance = null;
    public List<Saboteur> players = new ArrayList<>() ;
    private int counter = 0;
    private int points = 0;
    private SaboteurTeam(){
        Logger.log("log.txt","SaboteurTeam created", false);
    }
    public static synchronized SaboteurTeam getInstance()
    {
        if (instance == null)
            instance = new SaboteurTeam();
        return instance;
    }
    public void addPlayer(Player player)
    {
        if(players.size() < 5){
            players.add((Saboteur)player);
            Logger.log("log.txt","Player added to SaboteurTeam", false);
        }
        else
            Logger.log("log.txt","Player could not be added to SaboteurTeam, due to overpopulation", false);
    }

    public void addPoints(int point)
    {
        points+=point;
        Logger.log("log.txt","Points added the Saboteurs currently have "+ points+" points", false);
    }

    public Saboteur getSaboteur()
    {
        return players.get(counter++ % players.size());
    }

    public boolean hasWon()
    {
        return points > 50; //mitoménmennnyilgyen
    }

    public List<Saboteur> getPlayers(){ return players; }
    public void init()
    {
        for(int i = 0; i < 2; i++)
        {
            Saboteur saboteur = new Saboteur();
            addPlayer(saboteur);
            saboteur.setMapElement(Map.getInstance().getStartPosition());
            GUIManager.getInstance().createSaboteurGUIObject(saboteur);
        }
    }

    public int getPoints()
    {
        Logger.log("console.txt", "[SaboteurTeam] points: "+ points, true);
        return points;
    }

    public void reset(){
        players.clear();
        counter = 0;
        points = 0;

    }
}
