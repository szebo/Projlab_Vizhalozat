package main.players;

import main.logging.Logger;
import main.map.Map;

import java.util.ArrayList;
import java.util.List;

public class MechanicTeam {
    private static MechanicTeam instance = null;

    private List<Mechanic> players = new ArrayList<>() ;
    private int counter = 0;
    private int points = 0;
    private MechanicTeam(){
        Logger.log("log.txt","Mechanic team created", false);
    }
    public static synchronized MechanicTeam getInstance()
    {
        if (instance == null)
            instance = new MechanicTeam();
        return instance;
    }
    public void addPlayer(Player player)
    {
        if(players.size() < 3) {
            players.add((Mechanic) player);
            Logger.log("log.txt","Player added to MechanicTeam", false);
        }
        else
            Logger.log("log.txt","Player could not be added to MechanicTeam, due to overpopulation", false);
    }

    public void addPoints(int point)
    {
        points+=point;
        Logger.log("log.txt","Points added, currently the Mechanics have "+ points +" points", false);
    }

    public Mechanic getMechanic()
    {
        return players.get(counter++ % players.size());
    }

    public boolean hasWon()
    {
        return points > 100; //mitoménmennnyilgyen
    }

    public List<Mechanic> getPlayers(){
        return players;
    }

    public void init()
    {
        for(int i = 0; i<2; i++)
        {
            addPlayer(new Mechanic());
            players.get(i).setMapElement(Map.getInstance().getStartPosition());
        }
    }
    public int getPoints()
    {
        Logger.log("console.txt", "[MechanicTeam] points: "+ points, true);
        return points;
    }
    public void reset(){
        players.clear();
        counter = 0;
        points = 0;
    }
}
