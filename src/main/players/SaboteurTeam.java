package main.players;

import main.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class SaboteurTeam {
    private static SaboteurTeam instance = null;
    public List<Saboteur> players = new ArrayList<>() ;
    private int counter = 0;
    private int points = 0;
    private SaboteurTeam(){
        Logger.log("log.txt","SaboteurTeam created");
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
        Logger.log("log.txt","Player added to SaboteurTeam");
        }
        Logger.log("log.txt","Player could not be added to SaboteurTeam, due to overpopulation");
    }

    public void addPoints(int point)
    {
        points+=point;
        Logger.log("log.txt","Points added the Saboteurs currently have "+ points+" points");
    }

    public Saboteur getSabotuer()
    {
        counter++;
        return players.get(counter%2);
    }

    public boolean hasWon()
    {
        return points > 50; //mitoménmennnyilgyen
    }
}
